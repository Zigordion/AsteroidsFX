package dk.sdu.mmmi.cbse.multishotWeapon;

import dk.sdu.mmmi.commonweapon.CommonWeapon;
import dk.sdu.mmmi.commonweapon.WeaponSPI;

public class MultishotSystem implements WeaponSPI {
	@Override
	public CommonWeapon createWeapon() {
		return new MultishotWeapon();
	}
}
