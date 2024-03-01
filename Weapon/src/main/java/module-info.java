import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.weapon.WeaponControlSystem;

module Weapon {
    requires Common;
    requires Player;
    requires CommonBullet;
    provides IGamePluginService with WeaponControlSystem;
}