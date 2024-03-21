package dk.sdu.mmmi.cbse.scoresystem;


import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.services.IEventListener;
import dk.sdu.mmmi.cbse.common.services.IUIProcessingService;


public class AsteroidScoreController implements IUIProcessingService, IEventListener {
    private static int asteroidScore;
    public AsteroidScoreController(){
        EventBroker.getInstance().addListener(this,EventType.ASTEROID_DESTROYED);
    }
    private final UiTextElement score = new UiTextElement("" + asteroidScore,10,20,255,255,255);

    @Override
    public void processUI(GameData gameData, GameUi gameUi) {
        score.setText("Destroyed asteroids: " + asteroidScore);
        gameUi.addUiTextElement(score);
    }

    @Override
    public void onTrigger(EventType eventType, Entity... entities) {
        asteroidScore++;
    }



    //some method which increments asteroid score when the asteroid's onHit method is called by a bullet sent by a player
    //listen to event on asteroid



}
