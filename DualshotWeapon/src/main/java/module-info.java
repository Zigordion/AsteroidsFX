import dk.sdu.mmmi.cbse.dualshotWeapon.DualshotSystem;
import dk.sdu.mmmi.cbse.weapon.WeaponSPI;

module DualshotWeapon {
	requires Weapon;
	requires Common;
	requires CommonBullet;
	provides WeaponSPI with DualshotSystem;
}
