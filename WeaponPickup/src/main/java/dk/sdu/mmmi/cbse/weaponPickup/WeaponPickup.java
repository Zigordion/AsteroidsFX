package dk.sdu.mmmi.cbse.weaponPickup;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EventBroker;
import dk.sdu.mmmi.cbse.common.data.EventType;
import dk.sdu.mmmi.cbse.pickup.Pickup;
import dk.sdu.mmmi.cbse.playersystem.Player;

public class WeaponPickup extends Pickup {
    private final EventBroker eventBroker;

    public WeaponPickup(EventBroker eventBroker){
        this.eventBroker = eventBroker;
        eventBroker.addListener(this, EventType.COLLISION);
    }
    @Override
    public void onTrigger(EventType eventType, Entity... entities) {
        if(entities[0] == this && entities[1] instanceof Player){
            eventBroker.triggerEvent(EventType.WEAPON_PICKUP, entities[1]);
            setActive(false);
        }
    }
}
