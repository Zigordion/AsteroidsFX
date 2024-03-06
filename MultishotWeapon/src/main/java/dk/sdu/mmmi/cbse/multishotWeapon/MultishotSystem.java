package dk.sdu.mmmi.cbse.multishotWeapon;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.weapon.CommonWeapon;
import dk.sdu.mmmi.cbse.weapon.WeaponSPI;

public class MultishotSystem implements WeaponSPI {
    @Override
    public CommonWeapon createWeapon(Entity origin) {
        return new MultishotWeapon();
    }
}
