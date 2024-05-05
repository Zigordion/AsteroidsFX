package dk.sdu.mmmi.cbse.healthpickup;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EventBroker;
import dk.sdu.mmmi.cbse.common.data.EventType;
import dk.sdu.mmmi.cbse.common.services.IEventListener;
import dk.sdu.mmmi.cbse.pickup.Pickup;
import dk.sdu.mmmi.cbse.pickup.PickupSPI;
import dk.sdu.mmmi.cbse.playersystem.Player;

import java.util.ArrayList;
import java.util.List;

public class HealthPickupService implements PickupSPI, IEventListener {

    private final List<HealthPickup> healthPickups = new ArrayList<>();
    EventBroker eventBroker = EventBroker.getInstance();
    public HealthPickupService(){
        eventBroker.addListener(this, EventType.COLLISION);
    }

    @Override
    public Pickup createPickup(Entity entity) {
        HealthPickup healthPickup = new HealthPickup();
        healthPickup.setRGB(20,200,100);
        healthPickup.setX(entity.getX());
        healthPickup.setY(entity.getY());
        healthPickup.setPolygonCoordinates(-4,-4, -4,4, 4,4, 4,-4);
        healthPickup.setActive(true);
        return healthPickup;
    }
    @Override
    public void onTrigger(EventType eventType, Entity... entities) {
        for (HealthPickup healthPickup : healthPickups) {
            if(entities[0] == healthPickup && entities[1] instanceof Player){
                eventBroker.triggerEvent(EventType.HEALTH_PICKUP, entities[1]);
                healthPickup.setActive(false);
            }
        }
    }
}
