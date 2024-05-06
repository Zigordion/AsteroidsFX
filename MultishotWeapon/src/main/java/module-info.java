import dk.sdu.mmmi.cbse.multishotWeapon.MultishotSystem;
import dk.sdu.mmmi.cbse.weapon.WeaponSPI;

module MultishotWeapon {
	requires Weapon;
	requires Common;
	requires CommonBullet;
	provides WeaponSPI with MultishotSystem;
}
