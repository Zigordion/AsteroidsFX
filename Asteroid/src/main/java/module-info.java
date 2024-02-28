import dk.sdu.mmmi.cbse.asteroid.AsteroidControlSystem;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

module Asteroid {
    exports dk.sdu.mmmi.cbse.asteroid;
    requires Common;
    requires CommonBullet;
    uses dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
    provides IEntityProcessingService with AsteroidControlSystem;
}


