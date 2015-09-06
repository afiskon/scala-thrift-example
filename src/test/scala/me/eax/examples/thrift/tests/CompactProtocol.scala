package me.eax.examples.thrift.tests

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

import me.eax.examples.thrift.game._
import me.eax.examples.thrift.tests.gen._
import org.apache.thrift.protocol._
import org.apache.thrift.transport._
import org.scalatest._
import org.scalatest.prop._

class CompactProtocol extends FunSpec with Matchers with GeneratorDrivenPropertyChecks {
  describe("Thrift") {
    it("serializes and deserializes using TCompactProtocol") {
      forAll { (data1: Hero) =>
        val bytes = {
          val out = new ByteArrayOutputStream()
          data1.write(new TCompactProtocol(new TIOStreamTransport(out)))
          out.toByteArray
        }

        val data2 = {
          val stream = new ByteArrayInputStream(bytes)
          Hero.decode(new TCompactProtocol(new TIOStreamTransport(stream)))
        }

        data1 shouldBe data2
      }
    }
  }
}