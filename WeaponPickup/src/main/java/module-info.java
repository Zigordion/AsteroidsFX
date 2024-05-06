import dk.sdu.mmmi.pickup.PickupSPI;
import dk.sdu.mmmi.cbse.weaponPickup.WeaponPickupService;

module WeaponPickup {
    requires Common;
    requires CommonPickup;
    requires CommonPlayer;
    provides PickupSPI with WeaponPickupService;
}