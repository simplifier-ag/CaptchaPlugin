package io.simplifier.captchaplugin.pluginSlots

import io.simplifier.captchaplugin.pluginFramework.Globals
import io.simplifier.pluginapi.slots.Slot
import io.simplifier.pluginapi.{AKN, UserSession}
import org.json4s._

class GracefullyShutdown extends Slot with SlotResult {

  def slot(param:JValue)(implicit userSession: UserSession): Unit = {
    Globals.shutdown
    sender ! AKN
  }

}


