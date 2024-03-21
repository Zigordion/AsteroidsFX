package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {
    private final double moveSpeed = 5;

    @Override
    public void process(double deltaTime, GameData gameData, World world) {
        for (Entity entity : world.getEntities(Bullet.class)) {
            Bullet bullet = (Bullet) entity;
            double changeX = Math.cos(Math.toRadians(bullet.getRotation()));
            double changeY = Math.sin(Math.toRadians(bullet.getRotation()));
            bullet.setX(bullet.getX() + changeX*moveSpeed*deltaTime);
            bullet.setY(bullet.getY() + changeY*moveSpeed*deltaTime);
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
        Entity bullet = new Bullet();
        bullet.setActive(true);
        bullet.setPolygonCoordinates(-2,-2  ,-2,2,  2,2,  2,-2);
        bullet.setX(shooter.getX() + Math.cos(Math.toRadians(shooter.getRotation()))*25);
        bullet.setY(shooter.getY() + Math.sin(Math.toRadians(shooter.getRotation()))*25);
        bullet.setRotation(shooter.getRotation());
        return bullet;
    }
}
