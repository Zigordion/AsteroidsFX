
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Player {
    uses dk.sdu.mmmi.cbse.weapon.IWeaponControlSystem;
    exports dk.sdu.mmmi.cbse.playersystem;
    requires Common;
    requires Weapon;
    provides IGamePluginService with dk.sdu.mmmi.cbse.playersystem.PlayerPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.playersystem.PlayerControlSystem;
    
}
