import dk.sdu.mmmi.cbse.common.services.ILateStartService;
import dk.sdu.mmmi.cbse.weapon.WeaponControlSystem;

module Weapon {
    exports dk.sdu.mmmi.cbse.weapon;
    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    requires Common;
    requires Player;
    requires CommonBullet;
    provides ILateStartService with WeaponControlSystem;
}