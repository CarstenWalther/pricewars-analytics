package de.hpi.epic.pricewars.logging.producer

import org.apache.flink.streaming.util.serialization.AbstractDeserializationSchema
import org.json4s.Formats
import org.json4s.native.JsonMethods.parse

import de.hpi.epic.pricewars.logging.MyDateTimeSerializer

/**
  * Created by Jan on 30.11.2016.
  */
object OrderSchema extends AbstractDeserializationSchema[Order] {
  override def deserialize(message: Array[Byte]): Order = {
    implicit def formats: Formats = org.json4s.DefaultFormats + MyDateTimeSerializer // ++ org.json4s.ext.JodaTimeSerializers.all

    val json = new String(message)
    parse(json).extract[Order]
  }
}
