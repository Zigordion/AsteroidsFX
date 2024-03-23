import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.pickup.PickupGeneratorController;

module Pickup {
    uses dk.sdu.mmmi.cbse.pickup.PickupSPI;
    exports dk.sdu.mmmi.cbse.pickup;
    requires Common;
    provides IGamePluginService with PickupGeneratorController;
}
