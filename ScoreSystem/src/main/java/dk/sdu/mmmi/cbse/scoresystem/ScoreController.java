package dk.sdu.mmmi.cbse.scoresystem;

import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.services.IEventListener;
import dk.sdu.mmmi.cbse.common.services.IUIProcessingService;
import dk.sdu.mmmi.cbse.common.util.EventBroker;

public class ScoreController implements IUIProcessingService, IEventListener {
	private static int score;
	public ScoreController() {
		EventBroker.getInstance().addListener(this, EventType.ASTEROID_DESTROYED);
	}
	private final UiTextElement scoreUI = new UiTextElement("" + score, 10, 20, 255, 255, 255);

	@Override
	public void processUI(GameData gameData, GameUi gameUi) {
		scoreUI.setText("Destroyed asteroids: " + score);
		gameUi.addUiTextElement(scoreUI);
	}

	@Override
	public void onTrigger(Event event) {
		score++;
	}

}
