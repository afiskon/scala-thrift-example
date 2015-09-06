package me.eax.examples.thrift.tests

import me.eax.examples.thrift.game._
import org.scalacheck._

package object gen {
  implicit lazy val arbWarriorInfo: Arbitrary[ClassSpecificInfo.Warrior] = Arbitrary(
    for {
      weapon <- Gen.option(Gen.oneOf(Weapon.list))
      arrowsNumber <- Gen.posNum[Long]
    } yield ClassSpecificInfo.Warrior(WarriorInfo(weapon, arrowsNumber))
  )

  implicit lazy val arbMageInfo: Arbitrary[ClassSpecificInfo.Mage] = Arbitrary(
    for {
      spellbook <- Gen.listOf(Gen.oneOf(Spell.list))
      mana <- Gen.posNum[Long]
    } yield ClassSpecificInfo.Mage(MageInfo(spellbook.toSet, mana))
  )

  implicit lazy val arbClassSpecificInfo: Arbitrary[ClassSpecificInfo] = Arbitrary(
    Gen.oneOf(
      Arbitrary.arbitrary[ClassSpecificInfo.Warrior],
      Arbitrary.arbitrary[ClassSpecificInfo.Mage]
    )
  )

  implicit lazy val arbHero: Arbitrary[Hero] = Arbitrary(
    for {
      name <- Arbitrary.arbitrary[String]
      hp <- Gen.posNum[Long]
      xp <- Gen.posNum[Long]
      classSpecificInfo <- Arbitrary.arbitrary[ClassSpecificInfo]
    } yield Hero(name, hp, xp, classSpecificInfo)
  )
}
