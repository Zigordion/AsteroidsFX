import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.enemy.EnemyControlSystem;

module Enemy {
    exports dk.sdu.mmmi.cbse.enemy;
    requires Common;
    requires CommonBullet;
    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    provides IEntityProcessingService with EnemyControlSystem;

}
