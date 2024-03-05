package dk.sdu.mmmi.cbse.weapon;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.ILateStartService;
import dk.sdu.mmmi.cbse.playersystem.IPlayerShootListener;
import dk.sdu.mmmi.cbse.playersystem.Player;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class WeaponControlSystem implements IPlayerShootListener, ILateStartService {
    private CommonWeapon currentWeapon;
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

    public void setCurrentWeapon(CommonWeapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }
    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
