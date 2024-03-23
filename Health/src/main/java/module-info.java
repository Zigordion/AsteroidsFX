import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IUIProcessingService;
import dk.sdu.mmmi.cbse.health.PlayerHealthSystem;

module Health {
    requires Common;
    provides IUIProcessingService with PlayerHealthSystem;
    provides IGamePluginService with PlayerHealthSystem;
}
