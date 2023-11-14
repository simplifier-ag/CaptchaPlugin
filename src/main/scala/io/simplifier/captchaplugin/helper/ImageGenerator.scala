package io.simplifier.captchaplugin.helper

import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.nio.file.{Files, Paths}
import java.util.Properties

import com.google.code.kaptcha.impl._
import com.google.code.kaptcha.util.Config
import javax.imageio.ImageIO
import io.simplifier.captchaplugin.pluginFramework.Globals

class ImageGenerator(imageType: String = "jpg") {
  protected val processor = new DefaultKaptcha()
  processor.setConfig(new Config(new Properties()))

  val text: String = processor.createText()
  protected val img: BufferedImage = processor.createImage(text)
  protected val baos: ByteArrayOutputStream = new ByteArrayOutputStream()

  ImageIO.setCacheDirectory(Files.createDirectories(Paths.get(Globals.cacheFolder)).toFile)
  javax.imageio.ImageIO.write(img, imageType, baos)
  baos.flush()

  val imageInByte: Array[Byte] = baos.toByteArray
  baos.close()
}