import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.ILateStartService;
import dk.sdu.mmmi.cbse.weapon.WeaponControlSystem;

module Weapon {
    exports dk.sdu.mmmi.cbse.weapon;
    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    uses dk.sdu.mmmi.cbse.weapon.WeaponSPI;
    requires Common;
    requires Player;
    requires CommonBullet;
    requires Enemy;
    provides ILateStartService with WeaponControlSystem;
    provides IEntityProcessingService with WeaponControlSystem;
}