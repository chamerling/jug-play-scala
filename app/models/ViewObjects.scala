package models


import java.io._
import java.security._

case class EventViewObject(event: Event, talks: List[Talk], speakers: List[Speaker]) {
  
  def hex(array: Array[Byte]) = {
    val sb = new StringBuffer()
    for (i <- 0 to array.length -1)
      sb.append(Integer.toHexString((array(i)
        & 0xFF) | 0x100).substring(1, 3));

    sb.toString();
  }
  
  def md5Hex(message: String) = {
    try {
      val md =
        MessageDigest.getInstance("MD5");
      hex(md.digest(message.getBytes("UTF-8")));
    } catch {
      case e: NoSuchAlgorithmException => "nop"
      case e: UnsupportedEncodingException =>"nop"
    }
  }

  def gravatar(speaker: Speaker) = {
    "http://www.gravatar.com/avatar/" + md5Hex(speaker.email.get)
  }

}