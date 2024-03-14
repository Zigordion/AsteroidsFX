package dk.sdu.mmmi.cbse.weapon;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class WeaponControlSystem implements IWeaponControlSystem {
    private Map<String, CommonWeapon> weaponMap = new HashMap<>();
    public void notifyShot(GameData gameData, World world, Entity entity) {
        CommonWeapon currentWeapon = weaponMap.get(entity.getID());
        if(currentWeapon == null){
            currentWeapon = new CommonWeapon();
        }
        CommonWeapon finalCurrentWeapon = currentWeapon;
        getBulletSPIs().stream().findFirst().ifPresent(
                spi -> {
                    finalCurrentWeapon.shoot(gameData, world, entity, spi);
                }
        );
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }


    //Pick functionality missing, this should listen to the weapon pickup entity on hit
    public void setCurrentWeapon(Entity shooter) {
        List<? extends WeaponSPI> weaponSPIs = getWeaponSPI();
        Random random = new Random();
        weaponMap.remove(shooter.getID());
        weaponMap.put(shooter.getID(), weaponSPIs.get(random.nextInt(0, weaponSPIs.size())).createWeapon());
    }


    private List<? extends WeaponSPI> getWeaponSPI() {
        return ServiceLoader.load(WeaponSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
