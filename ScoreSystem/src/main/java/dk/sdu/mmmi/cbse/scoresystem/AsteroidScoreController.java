package dk.sdu.mmmi.cbse.scoresystem;

import dk.sdu.mmmi.cbse.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IUIProcessingService;

import java.util.Collection;


public class AsteroidScoreController implements IUIProcessingService, IEntityProcessingService, OnHitListener {
    private static int asteroidScore;
    private UiTextElement score = new UiTextElement("" + asteroidScore,10,20,255,255,255);

    @Override
    public void processUI(GameData gameData, GameUi gameUi) {
        score.setText("Destroyed asteroids: " + asteroidScore);
        gameUi.addUiTextElement(score);
    }


    @Override
    public void notifyHit() {
        asteroidScore++;
        System.out.println("hit registered +" + asteroidScore);

    }

    @Override
    public void process(double deltaTime, GameData gameData, World world) {
        Collection<Entity> entities = world.getEntities();
        for (Entity entity : entities) {
            if(entity instanceof Asteroid){
                entity.addOnHitListener(this);
                //System.out.println("add listener");
            }
        }
    }

    //some method which increments asteroid score when the asteroid's onHit method is called by a bullet sent by a player
    //listen to event on asteroid



}
