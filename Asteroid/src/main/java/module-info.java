import dk.sdu.mmmi.cbse.asteroid.AsteroidControlSystem;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

module Asteroid {
    requires Common;
    uses dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
    provides IEntityProcessingService with AsteroidControlSystem;
}


