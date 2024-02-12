package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Arrays;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    @Override
    public void process(GameData gameData, World world) {

        for (Entity bullet : world.getEntities(Bullet.class)) {
            //Moves the bullet?
            //Handle hit entity?
            double changeX = Math.cos(Math.toRadians(bullet.getRotation()));
            double changeY = Math.sin(Math.toRadians(bullet.getRotation()));
            bullet.setX(bullet.getX() + changeX*5);
            bullet.setY(bullet.getY() + changeY*5);
            if(bullet.getY() >= gameData.getDisplayHeight() || bullet.getY() <= 0){
                bullet.setActive(false);
            }
            if(bullet.getX() >= gameData.getDisplayWidth() || bullet.getX() <= 0){
                bullet.setActive(false);
            }
        }
    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        /*
            Spawn bullet
            set rotation
            set trajectory?
         */
        Entity bullet = new Bullet();
        bullet.setActive(true);
        bullet.setPolygonCoordinates(-2,-2,2,2,-2,2);
        bullet.setX(shooter.getX());
        bullet.setY(shooter.getY());
        bullet.setRotation(shooter.getRotation());

        return bullet;
    }

    private void setShape(Entity entity) {
    }

}
