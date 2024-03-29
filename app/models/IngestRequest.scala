package models

import com.rallyhealth.weepickle.v1.WeePickle.{macroTo, To}

final case class IngestRequest(
  startPokemonId: Int,
  endPokemonId: Option[Int],
  operation: Option[IngestOperation]
)

object IngestRequest {

  implicit val decoder: To[IngestRequest] = macroTo[IngestRequest]

}
