package dk.sdu.mmmi.cbse.weapon;

import dk.sdu.mmmi.cbse.common.data.Entity;

public interface WeaponSPI {

    CommonWeapon createWeapon(Entity origin);
}
