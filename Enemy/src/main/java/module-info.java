import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IEventListener;
import dk.sdu.mmmi.cbse.enemy.Enemy;
import dk.sdu.mmmi.cbse.enemy.EnemyControlSystem;

module Enemy {
    exports dk.sdu.mmmi.cbse.enemy;
    requires Common;
    provides IEntityProcessingService with EnemyControlSystem;
    provides IEventListener with Enemy;
}
