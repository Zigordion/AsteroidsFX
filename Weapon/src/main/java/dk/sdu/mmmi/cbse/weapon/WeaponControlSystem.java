package dk.sdu.mmmi.cbse.weapon;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IEventListener;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class WeaponControlSystem implements IGamePluginService, IEntityProcessingService, IEventListener {
    private final Map<String, CommonWeapon> weaponMap = new HashMap<>();
    private static GameData gameData;
    private static World world;

    private void shoot(GameData gameData, World world, Entity entity) {
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

    public void setCurrentWeapon(Entity shooter) {
        List<? extends WeaponSPI> weaponSPIs = getWeaponSPI();
        Random random = new Random();
        weaponMap.remove(shooter.getID());
        weaponMap.put(shooter.getID(), weaponSPIs.get(random.nextInt(0, weaponSPIs.size())).createWeapon());
    }


    private List<? extends WeaponSPI> getWeaponSPI() {
        return ServiceLoader.load(WeaponSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Override
    public void onTrigger(EventType eventType, Entity... entities) {
        if(eventType.equals(EventType.SHOOT)){
            for (Entity entity : entities) {
                shoot(gameData,world,entity);
            }
        }
        if(eventType.equals(EventType.WEAPON_PICKUP)){
            for (Entity entity : entities) {
                setCurrentWeapon(entity);
            }
        }
    }

    @Override
    public void process(double deltaTime, GameData gameData, World world) {
        WeaponControlSystem.gameData = gameData;
        WeaponControlSystem.world = world;
    }

    @Override
    public void start(GameData gameData, World world) {
        gameData.getEventBroker().addListener(this, EventType.SHOOT, EventType.WEAPON_PICKUP);
    }

    @Override
    public void stop(GameData gameData, World world) {
        gameData.getEventBroker().removeListener(this, EventType.WEAPON_PICKUP, EventType.SHOOT);
    }
}
