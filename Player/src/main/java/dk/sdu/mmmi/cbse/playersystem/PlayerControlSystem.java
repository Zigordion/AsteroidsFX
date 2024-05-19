package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.util.EventBroker;
import dk.sdu.mmmi.cbse.player.Player;

public class PlayerControlSystem implements IEntityProcessingService {

	private final double ROTATION_SPEED = 3.5;
	private final double MOVE_SPEED = 1;

	@Override
	public void process(double deltaTime, GameData gameData, World world) {
		for (Entity player : world.getEntities(Player.class)) {
			if (gameData.getKeys().isDown(GameKeys.LEFT)) {
				player.setRotation(player.getRotation() - ROTATION_SPEED * deltaTime);
			}
			if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
				player.setRotation(player.getRotation() + ROTATION_SPEED * deltaTime);
			}
			if (gameData.getKeys().isDown(GameKeys.UP)) {
				double changeX = Math.cos(Math.toRadians(player.getRotation()));
				double changeY = Math.sin(Math.toRadians(player.getRotation()));
				player.setX((player.getX() + changeX * MOVE_SPEED * deltaTime) % gameData.getDisplayWidth());
				player.setY((player.getY() + changeY * MOVE_SPEED * deltaTime) % gameData.getDisplayHeight());
			}

			if (gameData.getKeys().isPressed(GameKeys.SPACE)) {
				Event shootEvent = new Event(EventType.SHOOT, world, gameData, player);
				EventBroker.getInstance().triggerEvent(shootEvent);
			}
			validatePlayerPosition(gameData, player);
		}
	}

	private void validatePlayerPosition(GameData gameData, Entity player) {
		if (player.getX() <= 0) {
			player.setX(gameData.getDisplayWidth() - 1);
		}
		if (player.getY() <= 0) {
			player.setY(gameData.getDisplayHeight() - 1);
		}
	}

}
