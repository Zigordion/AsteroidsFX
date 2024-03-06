package dk.sdu.mmmi.cbse.weapon;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.OnHitListener;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.ILateStartService;
import dk.sdu.mmmi.cbse.common.services.IUIProcessingService;
import dk.sdu.mmmi.cbse.playersystem.IPlayerShootListener;
import dk.sdu.mmmi.cbse.playersystem.Player;
import dk.sdu.mmmi.cbse.enemy.Enemy;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class WeaponControlSystem implements IPlayerShootListener, IEntityProcessingService, OnHitListener, ILateStartService {
    private static CommonWeapon currentWeapon;
    @Override
    public void notifyShot(GameData gameData, World world, Entity entity) {
        if(currentWeapon == null){
            currentWeapon = new CommonWeapon();
        }
        getBulletSPIs().stream().findFirst().ifPresent(
                spi -> {
                    currentWeapon.shoot(gameData,world,entity,spi);
                }
        );
    }


    @Override
    public void lateStart(GameData gameData, World world) { 
        for (Entity entity : world.getEntities(Player.class)) {
            Player player = (Player) entity;
            player.addPlayerShootListeners(this);
        }
    }

    public void setCurrentWeapon(CommonWeapon newWeapon) {
        currentWeapon = newWeapon;

    }
    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }


    //Pick functionality missing, this should listen to the weapon pickup entity on hit
    @Override
    public void notifyHit(Entity origin, Entity other) {
        List<? extends WeaponSPI> weaponSPIs = getWeaponSPI();
        Random random = new Random();
        setCurrentWeapon(weaponSPIs.get(random.nextInt(0, weaponSPIs.size())).createWeapon(origin));
    }

    @Override
    public void process(double deltaTime, GameData gameData, World world) {
        for (Entity entity : world.getEntities(Enemy.class) ) {
            entity.addOnHitListener(this);
        }
    }

    private List<? extends WeaponSPI> getWeaponSPI() {
        return ServiceLoader.load(WeaponSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
