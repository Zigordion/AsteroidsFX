package dk.sdu.mmmi.cbse.pickup;

import dk.sdu.mmmi.cbse.common.data.Entity;

public interface PickupSPI {
    Pickup createPickup(Entity entity);
}
