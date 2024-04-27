package dk.sdu.mmmi.cbse.healthpickup;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EventBroker;
import dk.sdu.mmmi.cbse.common.data.EventType;
import dk.sdu.mmmi.cbse.pickup.Pickup;
import dk.sdu.mmmi.cbse.playersystem.Player;

public class HealthPickup extends Pickup {
    private final EventBroker eventBroker;

    public HealthPickup(EventBroker eventBroker){
        this.eventBroker = eventBroker;
        eventBroker.addListener(this, EventType.COLLISION);
    }
    @Override
    public void onTrigger(EventType eventType, Entity... entities) {
        if(entities[0] == this && entities[1] instanceof Player){
            eventBroker.triggerEvent(EventType.HEALTH_PICKUP, entities[1]);
            setActive(false);
        }
    }
}
