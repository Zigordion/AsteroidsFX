import dk.sdu.mmmi.cbse.weapon.IWeaponControlSystem;
import dk.sdu.mmmi.cbse.weapon.WeaponControlSystem;

module Weapon {
    exports dk.sdu.mmmi.cbse.weapon;
    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    uses dk.sdu.mmmi.cbse.weapon.WeaponSPI;
    requires Common;
    requires CommonBullet;
    provides IWeaponControlSystem with WeaponControlSystem;
}