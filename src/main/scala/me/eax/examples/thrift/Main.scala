package me.eax.examples.thrift

import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets

import me.eax.examples.thrift.game._
import org.apache.thrift.protocol.TSimpleJSONProtocol
import org.apache.thrift.transport.TIOStreamTransport

object Main extends App {
  def heroToJson(hero: Hero): String = {
    val out = new ByteArrayOutputStream()
    hero.write(new TSimpleJSONProtocol(new TIOStreamTransport(out)))
    new String(ByteBuffer.wrap(out.toByteArray).array(), StandardCharsets.UTF_8)
  }

  val mage = Hero(
    name = "afiskon", hp = 25L, xp = 1024L,
    ClassSpecificInfo.Mage(MageInfo(Set(Spell.Thunderbolt, Spell.Fireball), mana = 100L))
  )

  val warrior = Hero(
    name = "eax", hp = 50L, xp = 256L,
    ClassSpecificInfo.Warrior(WarriorInfo(Some(Weapon.Sword), 0L))
  )

  println(s"mage = $mage")
  println(s"warrior = $warrior")
  println(s"weapons = ${Weapon.list}, spells = ${Spell.list}")
  println(s"mageJson = ${heroToJson(mage)}")
  println(s"warriorJson = ${heroToJson(warrior)}")
}
