import dk.sdu.mmmi.cbse.pickup.PickupSPI;
import dk.sdu.mmmi.cbse.weaponPickup.WeaponPickupService;

module WeaponPickup {
    requires Common;
    requires Pickup;
    provides PickupSPI with WeaponPickupService;
}