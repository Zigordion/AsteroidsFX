import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.enemy.EnemyControlSystem;

module Enemy {
    exports dk.sdu.mmmi.cbse.enemy;
    requires Common;
    requires Weapon;
    uses dk.sdu.mmmi.cbse.weapon.IWeaponControlSystem;
    provides IEntityProcessingService with EnemyControlSystem;

}
