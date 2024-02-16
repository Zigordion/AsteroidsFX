package dk.sdu.mmmi.cbse;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements IEntityProcessingService {
    private static double timer;
    private final double maxTimer = 200;
    private static double shootTimer;
    private final double maxShootTimer = 20;
    private final double rotationSpeed = 5;
    private final double moveSpeed = 1;
    Random random = new Random();

    @Override
    public void process(double deltaTime, GameData gameData, World world) {
        timer -= 1; //should include delta time
        shootTimer -= random.nextDouble();
        if (timer <= 0) {
            Entity enemy = createEnemy(gameData);
            world.addEntity(enemy);
            timer = maxTimer;
        }
        for (Entity enemy : world.getEntities(Enemy.class)) {
            double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
            double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
            enemy.setX(enemy.getX() + changeX * deltaTime * moveSpeed);
            enemy.setY(enemy.getY() + changeY * deltaTime * moveSpeed);
            enemy.setRotation(enemy.getRotation() + random.nextDouble(-rotationSpeed, rotationSpeed) * deltaTime);
            if (enemy.getY() >= gameData.getDisplayHeight() || enemy.getY() <= 0) {
                enemy.setActive(false);
            }
            if (enemy.getX() >= gameData.getDisplayWidth() || enemy.getX() <= 0) {
                enemy.setActive(false);
            }
            if (shootTimer <= 0) {
                getBulletSPIs().stream().findFirst().ifPresent(
                        spi -> world.addEntity(spi.createBullet(enemy, gameData))
                );
            }
        }
        if (shootTimer <= 0) {
            shootTimer = maxShootTimer;
        }
    }

    public Entity createEnemy(GameData gameData) {
        Entity enemy = new Enemy();
        enemy.setPolygonCoordinates(-5, -5, 10, 5, -5, 5, 10, -5);
        enemy.setX(random.nextDouble(5, gameData.getDisplayWidth() - 5));
        enemy.setY(random.nextDouble(5, gameData.getDisplayHeight() - 5));
        enemy.setActive(true);
        return enemy;
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
