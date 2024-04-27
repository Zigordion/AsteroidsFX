package dk.sdu.mmmi.cbse.weaponPickup;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EventBroker;
import dk.sdu.mmmi.cbse.pickup.Pickup;
import dk.sdu.mmmi.cbse.pickup.PickupSPI;

public class WeaponPickupService implements PickupSPI {

    @Override
    public Pickup createPickup(Entity entity, EventBroker eventBroker) {
        WeaponPickup weaponPickup = new WeaponPickup(eventBroker);
        weaponPickup.setRGB(20,100,200);
        weaponPickup.setX(entity.getX());
        weaponPickup.setY(entity.getY());
        weaponPickup.setPolygonCoordinates(-4,-4, -4,4, 4,4, 4,-4);
        weaponPickup.setActive(true);
        return weaponPickup;
    }
}
