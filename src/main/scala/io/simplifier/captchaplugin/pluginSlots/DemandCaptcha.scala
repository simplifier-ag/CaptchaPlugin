package io.simplifier.captchaplugin.pluginSlots

import io.simplifier.captchaplugin.models
import io.simplifier.captchaplugin.pluginSlots.DemandCaptcha.DemandCaptchaResponse
import io.simplifier.pluginapi.{HttpPostResponse, UserSession}
import io.simplifier.pluginapi.slots.HttpSlot
import org.json4s._



class DemandCaptcha extends HttpSlot with SlotResult {

  def slot(param:JValue)(implicit userSession: UserSession): HttpPostResponse = {
    val image = models.CaptchaStore.demandCaptcha()
    HttpPostResponse("application/json",
      anyToJsonBytes(new Result(DemandCaptchaResponse(image.session, encodeB64(image.challenge))))
    )
  }

}

object DemandCaptcha  {
  case class DemandCaptchaResponse(session:String, jpegImage:String)
}