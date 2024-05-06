import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.enemysystem.EnemyControlSystem;

module Enemy {
	exports dk.sdu.mmmi.cbse.enemysystem;
	requires Common;
	requires CommonEnemy;
	provides IEntityProcessingService with EnemyControlSystem;
}
