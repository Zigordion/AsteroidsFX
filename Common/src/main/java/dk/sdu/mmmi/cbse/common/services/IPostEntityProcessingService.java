package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 *
 * @author jcs
 */
public interface IPostEntityProcessingService {
	/**
	 * @precondition entity processing has been run, and this service is provided to
	 *               the service locator
	 * @postcondition runs the process after each entity process is run
	 * @param gameData
	 * @param world
	 */
	void postProcess(GameData gameData, World world);
}
