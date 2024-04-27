package dk.sdu.mmmi.cbse.pickup;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EventBroker;

public interface PickupSPI {
    Pickup createPickup(Entity entity, EventBroker eventBroker);
}
