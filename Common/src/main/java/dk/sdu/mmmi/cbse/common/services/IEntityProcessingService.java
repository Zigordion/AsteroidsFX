package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IEntityProcessingService {

    /**
     * @param gameData
     * @param world
     * @precondition:
     * Service is provided to service locator
     * @postcondition
     * process is run each frame
     */
    void process(double deltaTime, GameData gameData, World world);
}
