// include "ololo.thrift"

namespace java me.eax.examples.thrift.game

// exception Ololo { ... }

// service Ololo { ... }

enum Weapon {
    Sword = 1
    Bow = 2
}

struct WarriorInfo {
    1: optional Weapon weapon
    2: required i64 arrowsNumber
}

enum Spell {
    Fireball = 1
    Thunderbolt = 2
}

struct MageInfo {
    1: required set<Spell> spellbook
    2: required i64 mana
}

union ClassSpecificInfo {
    1: WarriorInfo warrior
    2: MageInfo mage
}

struct Hero {
    1: required string name
    2: required i64 hp
    3: required i64 xp
    4: ClassSpecificInfo classSpecificInfo
    // TODO: gender, race
}