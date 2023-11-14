package io.simplifier.captchaplugin.helper


object SessionIdGenerator {

  private var counter = 0

  def next(): String = {
    val bytes = new Array[Byte](10)
    scala.util.Random.nextBytes(bytes)
    val newSess = "%03d".format(counter) + bytes.map("%02x".format(_)).mkString
    counter += 1
    counter = counter % 1000
    newSess
  }

}
