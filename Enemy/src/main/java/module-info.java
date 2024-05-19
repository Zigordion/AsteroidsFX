import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.enemysystem.EnemyControlSystem;

module Enemy {
	requires Common;
	requires CommonEnemy;
	provides IEntityProcessingService with EnemyControlSystem;
}
