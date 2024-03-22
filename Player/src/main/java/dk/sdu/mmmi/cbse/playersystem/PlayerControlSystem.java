package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class PlayerControlSystem implements IEntityProcessingService {

    private final double rotationSpeed = 3.5;
    private final double moveSpeed = 1;


    @Override
    public void process(double deltaTime, GameData gameData, World world) {
        for (Entity player : world.getEntities(Player.class)) {
            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                player.setRotation(player.getRotation() - rotationSpeed*deltaTime);
            }
            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                player.setRotation(player.getRotation() + rotationSpeed*deltaTime);
            }
            if (gameData.getKeys().isDown(GameKeys.UP)) {
                double changeX = Math.cos(Math.toRadians(player.getRotation()));
                double changeY = Math.sin(Math.toRadians(player.getRotation()));
                player.setX(player.getX() + changeX*moveSpeed*deltaTime);
                player.setY(player.getY() + changeY*moveSpeed*deltaTime);
            }

            if (gameData.getKeys().isPressed(GameKeys.SPACE)) { //doesn't get called if up and left is clicked, only laptop
                EventBroker.getInstance().triggerEvent(EventType.SHOOT,player);
            }
            validatePlayerPosition(gameData, player);
        }
    }

    private void validatePlayerPosition(GameData gameData, Entity player) {
        if (player.getX() < 0) {
            player.setX(1);
        }
        if (player.getX() > gameData.getDisplayWidth()) {
            player.setX(gameData.getDisplayWidth() - 1);
        }

        if (player.getY() < 0) {
            player.setY(1);
        }

        if (player.getY() > gameData.getDisplayHeight()) {
            player.setY(gameData.getDisplayHeight() - 1);
        }
    }


}
