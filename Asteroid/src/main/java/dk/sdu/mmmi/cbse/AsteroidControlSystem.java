package dk.sdu.mmmi.cbse;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class AsteroidControlSystem implements IEntityProcessingService {
    private static double timer;
    @Override
    public void process(double deltaTime, GameData gameData, World world) {
        timer -= 1; //should include delta time
        if (timer <= 0) {
            Entity asteroid = createAsteroid(gameData,world);
        }
    }

    private Entity createAsteroid(GameData gameData, World world){



        return new Asteroid();
    }
}
