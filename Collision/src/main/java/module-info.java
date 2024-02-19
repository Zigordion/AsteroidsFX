import dk.sdu.mmmi.cbse.collision.CollisionController;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    uses IPostEntityProcessingService;
    provides IPostEntityProcessingService with CollisionController;
}


