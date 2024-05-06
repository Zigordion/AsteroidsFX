import dk.sdu.mmmi.cbse.weaponPickup.WeaponPickupService;
import dk.sdu.mmmi.pickup.PickupSPI;

module WeaponPickup {
	requires Common;
	requires CommonPickup;
	requires CommonPlayer;
	provides PickupSPI with WeaponPickupService;
}