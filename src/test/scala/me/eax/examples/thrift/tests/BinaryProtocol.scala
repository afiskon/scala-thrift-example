package me.eax.examples.thrift.tests

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

import me.eax.examples.thrift.game._
import me.eax.examples.thrift.tests.gen._
import org.apache.thrift.protocol._
import org.apache.thrift.transport._
import org.scalatest._
import org.scalatest.prop._

class BinaryProtocol extends FunSpec with Matchers with GeneratorDrivenPropertyChecks {
  describe("Thrift") {
    it("serializes and deserializes using TBinaryProtocol") {
      forAll { (data1: Hero) =>
        val bytes = {
          val out = new ByteArrayOutputStream()
          data1.write(new TBinaryProtocol(new TIOStreamTransport(out)))
          out.toByteArray
        }

        val data2 = {
          val stream = new ByteArrayInputStream(bytes)
          Hero.decode(new TBinaryProtocol(new TIOStreamTransport(stream)))
        }

        data1 shouldBe data2
      }
    }

    it("serializes and deserializes lists using TBinaryProtocol") {
      forAll { (data1: List[Hero]) =>
        val bytes = {
          val out = new ByteArrayOutputStream()
          val proto = new TBinaryProtocol(new TIOStreamTransport(out))
          proto.writeListBegin(new TList(TType.STRUCT, data1.size)) // or Map, or Set
          data1.foreach(_.write(proto))
          proto.writeListEnd()
          out.toByteArray
        }

        val data2 = {
          val stream = new ByteArrayInputStream(bytes)
          val proto = new TBinaryProtocol(new TIOStreamTransport(stream))
          val listInfo = proto.readListBegin()
          val res = (for(_ <- 1 to listInfo.size) yield Hero.decode(proto)).toList
          proto.readListEnd()
          res
        }

        data1 shouldBe data2
      }
    }
  }
}
