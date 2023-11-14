package io.simplifier.captchaplugin.plugin

import io.simplifier.pluginapi.PluginApp
import io.simplifier.captchaplugin.pluginFramework.{GlobalSettings, Globals}

/**
  * Main class for the plugin.
  */
object Main extends PluginApp("captcha") {

  lazy val baseSlot = PluginSlots

  lazy val globalSettings = GlobalSettings

  lazy val globals = Globals

  /**
    * Startup Plugin.
    */
  override def init(): Unit = {
    log.info("Starting up Captcha Plugin ...")
  }

  /**
    * Shutdown plugin.
    */
  override def shutdown(): Unit = {
    log.info("Shutdown Captcha Plugin ...")
  }

}

