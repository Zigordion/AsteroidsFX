import dk.sdu.mmmi.cbse.healthpickup.HealthPickupService;
import dk.sdu.mmmi.pickup.PickupSPI;

module HealthPickup {
	requires Common;
	requires PickupGenerator;
	requires CommonPlayer;
	requires CommonPickup;
	provides PickupSPI with HealthPickupService;
}