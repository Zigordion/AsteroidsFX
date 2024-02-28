import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IUIProcessingService;
import dk.sdu.mmmi.cbse.scoresystem.AsteroidScoreController;

module ScoreSystem {
    requires Common;
    requires Asteroid;
    provides IUIProcessingService with AsteroidScoreController;
    provides IEntityProcessingService with AsteroidScoreController;
}
