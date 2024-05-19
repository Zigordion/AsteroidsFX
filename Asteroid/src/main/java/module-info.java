import dk.sdu.mmmi.cbse.asteroidsystem.AsteroidControlSystem;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

module Asteroid {
	requires Common;
	requires CommonBullet;
	requires CommonAsteroid;
	uses dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
	provides IEntityProcessingService with AsteroidControlSystem;
}
