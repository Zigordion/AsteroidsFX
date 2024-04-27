package dk.sdu.mmmi.cbse.scoresystem;


import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.services.IEventListener;
import dk.sdu.mmmi.cbse.common.services.IUIProcessingService;


public class ScoreController implements IUIProcessingService, IEventListener {
    private static int score;
    private boolean isInit = false;
    private final UiTextElement scoreUI = new UiTextElement("" + score,10,20,255,255,255);

    @Override
    public void processUI(GameData gameData, GameUi gameUi) {
        if(!isInit){
            gameData.getEventBroker().addListener(this, EventType.ASTEROID_DESTROYED);
            isInit = true;
        }
        scoreUI.setText("Destroyed asteroids: " + score);
        gameUi.addUiTextElement(scoreUI);
    }

    @Override
    public void onTrigger(EventType eventType, Entity... entities) {
        score++;
    }

}
