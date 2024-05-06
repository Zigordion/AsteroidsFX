import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.pickupgenerator.PickupGeneratorController;
import dk.sdu.mmmi.pickup.PickupSPI;

module PickupGenerator {
	uses PickupSPI;
	requires Common;
	requires CommonPickup;
	provides IGamePluginService with PickupGeneratorController;
}
