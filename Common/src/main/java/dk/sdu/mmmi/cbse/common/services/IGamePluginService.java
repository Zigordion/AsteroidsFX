package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IGamePluginService {

	/**
	 * @precondition Service provides implementation to service locator
	 * @postcondition runs code at start of game
	 * @param gameData
	 * @param world
	 */
	void start(GameData gameData, World world);
	/**
	 * @precondition Service provides implementation to service locator
	 * @postcondition runs code at end of game
	 * @param gameData
	 * @param world
	 */
	void stop(GameData gameData, World world);
}
