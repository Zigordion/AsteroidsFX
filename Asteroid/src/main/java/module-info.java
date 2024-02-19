import dk.sdu.mmmi.cbse.asteroid.AsteroidControlSystem;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

module Asteroid {
    requires Common;
    requires CommonBullet;
    uses dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
    provides IEntityProcessingService with AsteroidControlSystem;
}


