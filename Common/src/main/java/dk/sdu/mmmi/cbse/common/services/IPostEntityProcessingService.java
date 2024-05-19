package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 *
 * @author jcs
 */
public interface IPostEntityProcessingService {
	/**
	 * @precondition gameData != null <br>
	 * world != null <br>
	 * The IEntityProcessingService process method has been called before this method. <br>
	 * world contains all entities that need to be affected by this process
	 * @postcondition All entities that the provider handles has been added or removed to world All entities has been updated
	 * @param gameData
	 * @param world
	 */
	void postProcess(GameData gameData, World world);
}
