package dk.sdu.mmmi.cbse.dualshotWeapon;

import dk.sdu.mmmi.cbse.weapon.CommonWeapon;
import dk.sdu.mmmi.cbse.weapon.WeaponSPI;

public class DualshotSystem implements WeaponSPI {
    @Override
    public CommonWeapon createWeapon() {
        return new DualshotWeapon();
    }
}
