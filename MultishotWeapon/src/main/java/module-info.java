import dk.sdu.mmmi.cbse.multishotWeapon.MultishotSystem;
import dk.sdu.mmmi.commonweapon.WeaponSPI;

module MultishotWeapon {
	requires CommonWeapon;
	requires Common;
	requires CommonBullet;
	provides WeaponSPI with MultishotSystem;
}
