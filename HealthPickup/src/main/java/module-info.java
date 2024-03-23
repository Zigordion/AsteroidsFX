import dk.sdu.mmmi.cbse.healthpickup.HealthPickupService;
import dk.sdu.mmmi.cbse.pickup.PickupSPI;

module HealthPickup {
    requires Common;
    requires Pickup;
    requires Player;
    provides PickupSPI with HealthPickupService;
}