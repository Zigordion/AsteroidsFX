package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.enemy.Enemy;

import java.util.Random;

public class EnemyControlSystem implements IEntityProcessingService {

    private static double shootTimer;
    private final double maxShootTimer = 60;
    private final double rotationSpeed = 5;
    private final double moveSpeed = 1;
    private final Random random = new Random();
    private final EnemyPlugin enemyPlugin = new EnemyPlugin();

    @Override
    public void process(double deltaTime, GameData gameData, World world) {
        enemyPlugin.process(deltaTime, gameData, world);
        shootTimer -= random.nextDouble()*deltaTime;

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
                EventBroker.getInstance().triggerEvent(EventType.SHOOT,enemy);
            }
        }
        if (shootTimer <= 0) {
            shootTimer = maxShootTimer;
        }
    }


}
