package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameUi;

public interface IUIProcessingService {
    void process(GameData gameData, GameUi gameUi);
}
