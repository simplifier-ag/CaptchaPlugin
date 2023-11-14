package io.simplifier.captchaplugin.pluginSlots

import io.simplifier.pluginbase.util.json.JSONFormatter.{decomposeJSON, renderJSONCompact}
import org.json4s._
import org.apache.commons.codec.binary.Base64

import java.nio.charset.StandardCharsets

trait SlotResult {

  implicit val formats: Formats = DefaultFormats.lossless

  abstract class BaseResponseClass {
    val baseKey: String
  }

  case class Result(result: JValue, success: Boolean) extends BaseResponseClass {
    val baseKey = "Result"

    def this(result: JValue) = this(result, true)

    def this(result: String) = this(JString(result))

    def this(result: Any) = this(Extraction.decompose(result)(formats))
  }

  case class Error(message: JValue, success: Boolean) extends BaseResponseClass {
    val baseKey = "Error"

    def this(message: JValue) = this(message, false)

    def this(message: String) = this(JString(message), false)

    def this(message: Any) = this(Extraction.decompose(message)(formats))
  }

  val base64Codec = new Base64()

  protected def encodeB64(value: Array[Byte]): String =
    base64Codec.encodeToString(value)

  protected def anyToJsonBytes(value: Any): Array[Byte] =
    renderJSONCompact(decomposeJSON(value)).getBytes(StandardCharsets.UTF_8)

}
