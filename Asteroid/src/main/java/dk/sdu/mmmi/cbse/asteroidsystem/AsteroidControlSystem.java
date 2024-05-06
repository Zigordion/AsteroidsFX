package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;


public class AsteroidControlSystem implements IEntityProcessingService{
    private final AsteroidPlugin asteroidPlugin = new AsteroidPlugin();
    @Override
    public void process(double deltaTime, GameData gameData, World world) {
        asteroidPlugin.process(deltaTime, gameData, world);
        for (Entity entity : world.getEntities(Asteroid.class)) {
            if(entity instanceof Asteroid asteroid){
                double speed = 0.6;
                asteroid.setX((asteroid.getX()+asteroid.getXDirection()*deltaTime* speed)% gameData.getDisplayWidth());
                asteroid.setY((asteroid.getY()+asteroid.getYDirection()*deltaTime* speed)% gameData.getDisplayHeight());
                double rotationSpeed = 1;
                asteroid.setRotation(asteroid.getRotation()+ rotationSpeed *deltaTime);
                if (asteroid.getY() < 0) {
                    asteroid.setY(gameData.getDisplayHeight()-1);
                }
                if (asteroid.getX() < 0) {
                    asteroid.setX(gameData.getDisplayWidth()-1);
                }
            }
        }
    }



}
