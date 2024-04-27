package dk.sdu.mmmi.cbse.enemy;

import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Random;

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
        shootTimer -= random.nextDouble();//should include delta time+
        if (timer <= 0) {
            Entity enemy = createEnemy(gameData);
            world.addEntity(enemy);
            timer = maxTimer;
        }
        for (Entity enemy : world.getEntities(Enemy.class)) {
            double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
            double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
            enemy.setX((enemy.getX() + changeX * deltaTime * moveSpeed)% gameData.getDisplayWidth());
            enemy.setY((enemy.getY() + changeY * deltaTime * moveSpeed)% gameData.getDisplayHeight());
            enemy.setRotation(enemy.getRotation() + random.nextDouble(-rotationSpeed, rotationSpeed) * deltaTime);
            if (enemy.getY() <= 0) {
                enemy.setY(gameData.getDisplayHeight()-1);
            }
            if (enemy.getX() <= 0) {
                enemy.setX(gameData.getDisplayWidth()-1);
            }
            if (shootTimer <= 0) {
                gameData.getEventBroker().triggerEvent(EventType.SHOOT,enemy);
            }
        }
        if (shootTimer <= 0) {
            shootTimer = maxShootTimer;
        }
    }

    public Entity createEnemy(GameData gameData) {
        Entity enemy = new Enemy(gameData.getEventBroker());
        enemy.setRGB(128,0,0);
        enemy.setPolygonCoordinates(-5, -5, 10, 5, -5, 5, 10, -5);
        enemy.setX(random.nextDouble(5, gameData.getDisplayWidth() - 5));
        enemy.setY(random.nextDouble(5, gameData.getDisplayHeight() - 5));
        enemy.setActive(true);
        return enemy;
    }

}
