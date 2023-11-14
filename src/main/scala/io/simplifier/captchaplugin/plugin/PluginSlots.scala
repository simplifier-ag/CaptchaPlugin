package io.simplifier.captchaplugin.plugin

import akka.actor.Props
import io.simplifier.pluginapi.BaseProps
import io.simplifier.captchaplugin.pluginSlots

object PluginSlots extends BaseProps {

  val slots: Map[String, Props] = Map (
    "gracefullyShutdown"  -> Props[pluginSlots.GracefullyShutdown]
  )

  val httpSlots: Map[String, Props] = Map (
    "gracefullyShutdownhttp"  -> Props[pluginSlots.GracefullyShutdownHttp],
    "test"                    -> Props[pluginSlots.GetServerVersion],
    "demandCaptcha"           -> Props[pluginSlots.DemandCaptcha],
    "testCaptcha"             -> Props[pluginSlots.TestCaptcha]
  )

}
