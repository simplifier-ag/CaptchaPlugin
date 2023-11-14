package io.simplifier.captchaplugin.pluginFramework

import io.simplifier.pluginapi.{PluginGlobalSettings, PluginGlobals}


object GlobalSettings extends PluginGlobalSettings

object Globals extends PluginGlobals(GlobalSettings) {
  val cacheFolderPath: String = "plugin.fileSystemRepository.cacheFolder"
  val cacheFolder: String = if (settings.hasPath(cacheFolderPath)) {
    settings.getString(cacheFolderPath)
  } else {
    "/tmp"
  }

  override val registrationSecret: String = byDeployment.PluginRegistrationSecret()
}