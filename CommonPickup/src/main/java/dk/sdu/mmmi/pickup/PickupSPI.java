package dk.sdu.mmmi.pickup;

import dk.sdu.mmmi.cbse.common.data.Entity;

public interface PickupSPI {
    Pickup createPickup(Entity entity);
}
