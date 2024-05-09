package dk.sdu.mmmi.cbse.dualshotWeapon;

import dk.sdu.mmmi.commonweapon.CommonWeapon;
import dk.sdu.mmmi.commonweapon.WeaponSPI;

public class DualshotSystem implements WeaponSPI {
	@Override
	public CommonWeapon createWeapon() {
		return new DualshotWeapon();
	}
}
