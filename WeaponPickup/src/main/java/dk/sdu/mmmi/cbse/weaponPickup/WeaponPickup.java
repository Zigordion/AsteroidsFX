package dk.sdu.mmmi.cbse.weaponPickup;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EventBroker;
import dk.sdu.mmmi.cbse.common.data.EventType;
import dk.sdu.mmmi.cbse.pickup.Pickup;

public class WeaponPickup extends Pickup {
    public WeaponPickup(){
        EventBroker.getInstance().addListener(this, EventType.COLLISION);
    }
    @Override
    public void onTrigger(EventType eventType, Entity... entities) {
        EventBroker.getInstance().triggerEvent(EventType.WEAPON_PICKUP,entities[0]);
    }
}
