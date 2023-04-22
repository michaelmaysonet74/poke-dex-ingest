package models

import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.MongoClient.DEFAULT_CODEC_REGISTRY
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import org.bson.codecs.configuration.CodecRegistry
import org.mongodb.scala.bson.ObjectId

final case class PokemonRecord(
  _id: ObjectId,
  id: Int,
  name: Option[String],
  entry: Option[String],
  types: Option[List[String]],
  measurement: Option[Measurement],
  abilities: Option[List[Ability]],
  sprite: Option[String],
  evolution: Option[EvolutionChain],
  isMonoType: Boolean,
  weaknesses: Option[List[String]],
  generation: Option[Int],
  baseStats: Option[BaseStats]
)

object PokemonRecord {

  def apply(
    id: Int,
    name: Option[String],
    entry: Option[String],
    types: Option[List[String]],
    measurement: Option[Measurement],
    abilities: Option[List[Ability]],
    sprite: Option[String],
    evolution: Option[EvolutionChain],
    isMonoType: Option[Boolean],
    weaknesses: Option[List[String]],
    baseStats: Option[BaseStats]
  ): PokemonRecord =
    PokemonRecord(
      _id = new ObjectId(),
      id = id,
      name = name,
      entry = entry,
      types = types,
      measurement = measurement,
      abilities = abilities,
      sprite = sprite,
      evolution = evolution,
      isMonoType = isMonoType.getOrElse(false),
      weaknesses = weaknesses,
      generation = getGeneration(id),
      baseStats = baseStats
    )

  private def getGeneration(pokemonId: Int): Option[Int] =
    pokemonId match {
      case 1 to 151    => Some(1)
      case 152 to 251  => Some(2)
      case 252 to 386  => Some(3)
      case 387 to 493  => Some(4)
      case 494 to 649  => Some(5)
      case 650 to 721  => Some(6)
      case 722 to 809  => Some(7)
      case 810 to 905  => Some(8)
      case 906 to 1010 => Some(9)
      case _           => None
    }

  val codecRegistry: CodecRegistry =
    fromRegistries(
      fromProviders(
        classOf[PokemonRecord],
        classOf[PokemonType],
        classOf[Measurement],
        classOf[Ability],
        classOf[EvolutionChain],
        classOf[Evolution],
        classOf[BaseStats]
      ),
      DEFAULT_CODEC_REGISTRY
    )

}
