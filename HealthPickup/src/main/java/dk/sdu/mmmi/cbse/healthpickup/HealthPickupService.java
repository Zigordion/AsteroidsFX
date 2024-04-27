package dk.sdu.mmmi.cbse.healthpickup;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EventBroker;
import dk.sdu.mmmi.cbse.pickup.Pickup;
import dk.sdu.mmmi.cbse.pickup.PickupSPI;

public class HealthPickupService implements PickupSPI {
    @Override
    public Pickup createPickup(Entity entity, EventBroker eventBroker) {
        HealthPickup healthPickup = new HealthPickup(eventBroker);
        healthPickup.setRGB(20,200,100);
        healthPickup.setX(entity.getX());
        healthPickup.setY(entity.getY());
        healthPickup.setPolygonCoordinates(-4,-4, -4,4, 4,4, 4,-4);
        healthPickup.setActive(true);
        return healthPickup;
    }
}
