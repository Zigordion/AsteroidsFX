package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IGamePluginService {

	/**
	 * @precondition gameData != null <br>
	 * world != null <br>
	 * world contains all entities that need to be affected by this process
	 * @postcondition All entities that the provider handles has been added to world
	 * @param gameData
	 * @param world
	 */
	void start(GameData gameData, World world);
	/**
	 * @precondition gameData != null <br>
	 * 	 * world != null <br>
	 * 	 * world contains all entities that need to be affected by this process
	 * @postcondition All entities that the provider handles has been removed from world
	 * @param gameData
	 * @param world
	 */
	void stop(GameData gameData, World world);
}
