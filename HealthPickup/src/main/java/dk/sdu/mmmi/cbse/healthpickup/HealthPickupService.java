package dk.sdu.mmmi.cbse.healthpickup;

import dk.sdu.mmmi.cbse.common.data.Event;
import dk.sdu.mmmi.pickup.Pickup;
import dk.sdu.mmmi.pickup.PickupSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EventBroker;
import dk.sdu.mmmi.cbse.common.data.EventType;
import dk.sdu.mmmi.cbse.common.services.IEventListener;
import dk.sdu.mmmi.cbse.player.Player;

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
        healthPickups.add(healthPickup);
        return healthPickup;
    }
    @Override
    public void onTrigger(Event event) {
        for (HealthPickup healthPickup : healthPickups) {
            if(event.getEntities()[0] == healthPickup && event.getEntities()[1] instanceof Player){
                Event newEvent = new Event(EventType.HEALTH_PICKUP, event.getWorld(), event.getGameData(),event.getEntities()[1]);
                eventBroker.triggerEvent(newEvent);
                healthPickup.setActive(false);
                healthPickups.remove(healthPickup);
                break;
            }
        }
    }
}
