import dk.sdu.mmmi.cbse.common.services.IUIProcessingService;
import dk.sdu.mmmi.cbse.scoresystem.ScoreController;

module ScoreSystem {
    requires Common;
    requires java.net.http;
    provides IUIProcessingService with ScoreController;
}
