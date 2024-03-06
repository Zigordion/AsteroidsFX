package dk.sdu.mmmi.cbse.dualshotWeapon;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.weapon.CommonWeapon;
import dk.sdu.mmmi.cbse.weapon.WeaponSPI;

public class DualshotSystem implements WeaponSPI {
    @Override
    public CommonWeapon createWeapon(Entity origin) {
        return new DualshotWeapon();
    }
}
