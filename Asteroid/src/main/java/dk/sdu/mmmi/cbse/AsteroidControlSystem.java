package dk.sdu.mmmi.cbse;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class AsteroidControlSystem implements IEntityProcessingService {
asdasd
    @Override
    public void process(double deltaTime, GameData gameData, World world) {
        timer -= 1; //should include delta time
        if (timer <= 0) {
            Entity enemy = createEnemy(gameData);
            world.addEntity(enemy);
            timer = maxTimer;
        }
    }
}
