import dk.sdu.mmmi.cbse.asteroidsystem.AsteroidControlSystem;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

module Asteroid {
    exports dk.sdu.mmmi.cbse.asteroidsystem;
    requires Common;
    requires CommonBullet;
    requires CommonAsteroid;
    uses dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
    provides IEntityProcessingService with AsteroidControlSystem;
}


