package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameUi;

public interface IUIProcessingService {
	/**
	 * @precondition gameData != null <br>
	 * gameUi != null <br>
	 * gameUi contains all the ui elements that need to be affected by this process
	 * @postcondition All ui elements that the provider handles has been added or removed to world<br>
	 * All ui elements has been updated
	 * @param gameData
	 * @param gameUi
	 */
	void processUI(GameData gameData, GameUi gameUi);
}
