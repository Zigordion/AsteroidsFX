package dk.sdu.mmmi.cbse.weapon;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.playersystem.IPlayerShootListener;
import dk.sdu.mmmi.cbse.playersystem.Player;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class WeaponControlSystem implements IPlayerShootListener, IGamePluginService {
    @Override
    public void notifyShot(GameData gameData, World world, Entity entity) {
        System.out.println("shoot");
        getBulletSPIs().stream().findFirst().ifPresent(
                spi -> {
                    Entity bullet = spi.createBullet(entity, gameData);
                    bullet.setRGB(255,255,255);
                    world.addEntity(bullet);
                }
        );
    }



    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Override
    public void start(GameData gameData, World world) { //change to late start
        for (Entity entity : world.getEntities(Player.class)) {
            Player player = (Player) entity;
            player.addPlayerShootListeners(this);
            System.out.println("added");
        }
    }

    @Override
    public void stop(GameData gameData, World world) {

    }
}
