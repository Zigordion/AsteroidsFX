import dk.sdu.mmmi.cbse.common.services.IUIProcessingService;
import dk.sdu.mmmi.cbse.scoresystem.scoreController;

module ScoreSystem {
    requires Common;
    provides IUIProcessingService with scoreController;
}
