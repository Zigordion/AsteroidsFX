import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.weapon.WeaponControlSystem;

module Weapon {
	exports dk.sdu.mmmi.cbse.weapon;
	uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
	uses dk.sdu.mmmi.commonweapon.WeaponSPI;
	requires Common;
	requires CommonBullet;
	requires CommonWeapon;
	provides IEntityProcessingService with WeaponControlSystem;
	provides IGamePluginService with WeaponControlSystem;
}