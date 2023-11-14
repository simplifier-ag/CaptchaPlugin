package io.simplifier.captchaplugin.pluginSlots

import io.simplifier.captchaplugin.pluginFramework.Globals
import io.simplifier.pluginapi.slots.HttpSlot
import io.simplifier.pluginapi.{HttpPostResponse, UserSession}
import org.json4s.JValue

class GracefullyShutdownHttp extends HttpSlot with SlotResult {

  def slot(param: JValue)(implicit userSession: UserSession): HttpPostResponse = {
    Globals.shutdown
    HttpPostResponse("application/json", anyToJsonBytes(new Result("ByeBye")))
  }

}
