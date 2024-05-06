import dk.sdu.mmmi.cbse.common.services.IUIProcessingService;
import dk.sdu.mmmi.cbse.scoresystem.ScoreController;

module ScoreSystem {
	requires Common;
	provides IUIProcessingService with ScoreController;
}
