package io.simplifier.captchaplugin.models

import io.simplifier.captchaplugin.helper.{ImageGenerator, SessionIdGenerator}


object CaptchaStore {

  private val maxTrys = 5

  private val activeCaptchas = collection.mutable.Map[String, Captcha]()

  case class Captcha(challenge:Array[Byte], response:String, session:String, numTry: Int = 0)
  case class TestResult(matchCaptcha:Boolean, leftTrys:Int)


  def demandCaptcha(imageType: String = "jpg"): Captcha = {
    val image = new ImageGenerator(imageType)
    val c = Captcha(image.imageInByte, image.text, SessionIdGenerator.next())
    activeCaptchas += c.session -> c
    c
  }

  def testCaptcha(session: String, test:String): TestResult = activeCaptchas.get(session) match {
    case Some(item) =>
      if (item.numTry >= maxTrys) {
        invalidateCaptcha(session)
        TestResult(matchCaptcha = false, 0)
      }
      else {
        val c = item.copy(numTry = item.numTry +1 )
        activeCaptchas(session) = c
        if (item.response == test) {
          invalidateCaptcha(session)
          TestResult(matchCaptcha = true, 0)
        } else {
          TestResult(matchCaptcha = false, maxTrys - c.numTry)
        }
      }
    case _ =>
      TestResult(matchCaptcha = false, 0)
  }


  private def invalidateCaptcha(session: String): Unit =
    activeCaptchas -= session

}
