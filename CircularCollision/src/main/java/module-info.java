import dk.sdu.mmmi.cbse.collision.CircularCollisionController;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module CircularCollision {
	requires Common;
	uses IPostEntityProcessingService;
	provides IPostEntityProcessingService with CircularCollisionController;
}
