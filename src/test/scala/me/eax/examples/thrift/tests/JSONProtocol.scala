package me.eax.examples.thrift.tests

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}
import java.nio.charset.StandardCharsets

import me.eax.examples.thrift.game._
import me.eax.examples.thrift.tests.gen._
import org.apache.thrift.protocol._
import org.apache.thrift.transport._
import org.scalatest._
import org.scalatest.prop._

class JSONProtocol extends FunSpec with Matchers with GeneratorDrivenPropertyChecks {
  describe("Thrift") {
    it("serializes and deserializes using TJSONProtocol") {
      forAll { (data1: Hero) =>
        val str = {
          val out = new ByteArrayOutputStream()
          data1.write(new TJSONProtocol(new TIOStreamTransport(out)))
          new String(out.toByteArray, StandardCharsets.UTF_8)
        }

        val data2 = {
          val stream = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8))
          Hero.decode(new TJSONProtocol(new TIOStreamTransport(stream)))
        }

        data1 shouldBe data2
      }
    }
  }
}