package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IEntityProcessingService {

	/**
	 * @param gameData
	 * @param world
	 * @precondition: gameData != null <br>
	 * world != null <br>
	 * world contains all entities that need to be affected by this process
	 * @postcondition deltatime is updated according to the difference in time since last frame.<br>
	 * All entities that the provider handles has been added or removed to world<br>
	 * All entities has been updated
	 */
	void process(double deltaTime, GameData gameData, World world);
}
