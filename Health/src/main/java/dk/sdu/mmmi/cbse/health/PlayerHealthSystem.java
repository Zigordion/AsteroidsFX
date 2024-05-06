package dk.sdu.mmmi.cbse.health;

import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.services.IEventListener;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IUIProcessingService;
import dk.sdu.mmmi.cbse.player.Player;

public class PlayerHealthSystem implements IUIProcessingService, IGamePluginService, IEventListener {
	private static int playerHealth;
	private final int maxPlayerHealth = 3;
	private final UiTextElement playerHealthTitle = new UiTextElement("Lives", 0, 0, 255, 255, 255);
	private final UiTextElement playerHealthIndicator = new UiTextElement("", 0, 0, 255, 255, 255);

	private static UiTextElement gameOverText;
	private static GameData GameData;
	@Override
	public void processUI(GameData gameData, GameUi gameUi) {
		GameData = gameData;
		playerHealthTitle.setY(20);
		playerHealthTitle.setX(gameData.getDisplayWidth() - 80);
		playerHealthIndicator.setY(40);
		playerHealthIndicator.setX(gameData.getDisplayWidth() - 80);

		playerHealthIndicator.setText("❤".repeat(playerHealth));
		if (gameOverText != null) {
			gameOverText.setY(gameData.getDisplayHeight() / 2.0);
			gameOverText.setX(gameData.getDisplayWidth() / 2.0);
			gameOverText.setRGB(255, 0, 0);
			gameUi.addUiTextElement(gameOverText);
		}
		gameUi.addUiTextElement(playerHealthTitle);
		gameUi.addUiTextElement(playerHealthIndicator);
	}

	@Override
	public void start(GameData gameData, World world) {
		playerHealth = maxPlayerHealth;
		EventBroker.getInstance().addListener(this, EventType.PLAYER_HIT, EventType.HEALTH_PICKUP);
	}

	@Override
	public void stop(GameData gameData, World world) {
		EventBroker.getInstance().removeListener(this);
	}

	@Override
	public void onTrigger(Event event) {
		if (event.getEventType() == EventType.PLAYER_HIT) {
			playerHealth--;
			event.getEntities()[0].setX(GameData.getDisplayWidth() / 2.0);
			event.getEntities()[0].setY(GameData.getDisplayHeight() / 2.0);
			if (playerHealth <= 0) {
				event.getEntities()[0].setActive(false);
				gameOverText = new UiTextElement("Game Over", 0, 0, 255, 0, 0);
			}
			for (Entity entity : event.getWorld().getEntities()) {
				if (!(entity instanceof Player)) {
					entity.setActive(false);
				}
			}
		}
		if (event.getEventType() == EventType.HEALTH_PICKUP) {
			if (playerHealth == maxPlayerHealth) {
				return;
			}
			playerHealth++;
		}
	}
}
