package io.simplifier.captchaplugin.pluginSlots

import io.simplifier.captchaplugin.pluginFramework.Globals
import io.simplifier.pluginapi.slots.HttpSlot
import io.simplifier.pluginapi.{HttpPostResponse, JSON, UserSession}
import org.json4s._


class GetServerVersion extends HttpSlot with SlotResult {

  def slot(param: JValue)(implicit userSession: UserSession): HttpPostResponse = {
    Globals.requestServerSync(JSON(JNothing, userSession), "version") match {
      case Some(res: JValue) =>
        HttpPostResponse("application/json", anyToJsonBytes(new Result(res)))
      case item =>
        println(item)
        HttpPostResponse("application/json", anyToJsonBytes(new Error("Bad Response")))
    }
  }

}

