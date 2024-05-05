package dk.sdu.mmmi.cbse.weaponPickup;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EventBroker;
import dk.sdu.mmmi.cbse.common.data.EventType;
import dk.sdu.mmmi.cbse.common.services.IEventListener;
import dk.sdu.mmmi.cbse.pickup.Pickup;
import dk.sdu.mmmi.cbse.pickup.PickupSPI;
import dk.sdu.mmmi.cbse.playersystem.Player;

import java.util.ArrayList;
import java.util.List;

public class WeaponPickupService implements PickupSPI, IEventListener {

    private final List<WeaponPickup> weaponPickups = new ArrayList<>();
    EventBroker eventBroker = EventBroker.getInstance();
    public WeaponPickupService(){
        eventBroker.addListener(this, EventType.COLLISION);
    }

    @Override
    public Pickup createPickup(Entity entity) {
        WeaponPickup weaponPickup = new WeaponPickup();
        weaponPickup.setRGB(20,100,200);
        weaponPickup.setX(entity.getX());
        weaponPickup.setY(entity.getY());
        weaponPickup.setPolygonCoordinates(-4,-4, -4,4, 4,4, 4,-4);
        weaponPickup.setActive(true);
        weaponPickups.add(weaponPickup);
        return weaponPickup;
    }

    @Override
    public void onTrigger(EventType eventType, Entity... entities) {
        for (WeaponPickup weaponPickup : weaponPickups) {
            if(entities[0] == weaponPickup && entities[1] instanceof Player){
                eventBroker.triggerEvent(EventType.WEAPON_PICKUP, entities[1]);
                weaponPickup.setActive(false);
                weaponPickups.remove(weaponPickup);
                break;
            }
        }
    }
}
