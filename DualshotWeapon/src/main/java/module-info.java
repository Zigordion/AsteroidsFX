import dk.sdu.mmmi.cbse.dualshotWeapon.DualshotSystem;
import dk.sdu.mmmi.commonweapon.WeaponSPI;

module DualshotWeapon {
	requires Common;
	requires CommonBullet;
	requires CommonWeapon;
	provides WeaponSPI with DualshotSystem;
}
