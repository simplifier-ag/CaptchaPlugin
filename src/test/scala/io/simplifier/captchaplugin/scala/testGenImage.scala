import org.scalatest.{BeforeAndAfter,FunSuite}

import com.google.code.kaptcha.impl._
import com.google.code.kaptcha.Producer
import com.google.code.kaptcha.util.Config

import java.awt.image.BufferedImage
import java.io.{FileOutputStream, BufferedOutputStream, ByteArrayOutputStream, IOException}
import java.util.Date
import java.util.Enumeration
import java.util.Properties


class testGenImage extends FunSuite {

  import org.apache.commons.codec.binary.Base64

  test("genImage") {
    val processor= new DefaultKaptcha()
    processor.setConfig(new Config(new Properties()))
    val text=processor.createText()
    val img=processor.createImage(text)
    val baos = new ByteArrayOutputStream()
    javax.imageio.ImageIO.write(img, "jpg", baos)
    baos.flush()
    val imageInByte = baos.toByteArray()
    baos.close()
    val base64Codec = new Base64()
    val b64String = base64Codec.encodeToString(imageInByte)

    /* println(text)
    println(b64String)
    val bos = new BufferedOutputStream(new FileOutputStream("display.jpeg"))
    Stream.continually(bos.write(imageInByte))
    bos.close()
    */
    assert(imageInByte.nonEmpty)
  }

}
