package io.simplifier.captchaplugin.pluginSlots

import io.simplifier.captchaplugin.models.CaptchaStore.testCaptcha
import io.simplifier.captchaplugin.pluginSlots.TestCaptcha.ChallengeRequest
import io.simplifier.pluginapi.slots.HttpSlot
import io.simplifier.pluginapi.{HttpPostResponse, UserSession}
import org.json4s._


class TestCaptcha() extends HttpSlot with SlotResult {

  implicit val Formats:Formats = DefaultFormats.lossless

  def slot(param:JValue)(implicit userSession: UserSession): HttpPostResponse = {
    try {
      val ChallengeRequest(session, test) = param.extract[ChallengeRequest]
      HttpPostResponse("application/json", anyToJsonBytes(new Result(testCaptcha(session, test))))
    }
    catch {
      case e:Exception =>  HttpPostResponse("application/json", anyToJsonBytes(new Error(e.getMessage)))
    }
  }

}

object TestCaptcha {
  case class ChallengeRequest(session: String, test: String)
}