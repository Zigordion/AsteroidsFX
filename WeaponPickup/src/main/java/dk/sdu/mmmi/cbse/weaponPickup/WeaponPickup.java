package dk.sdu.mmmi.cbse.weaponPickup;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EventBroker;
import dk.sdu.mmmi.cbse.common.data.EventType;
import dk.sdu.mmmi.cbse.pickup.Pickup;
import dk.sdu.mmmi.cbse.playersystem.Player;

public class WeaponPickup extends Pickup {
    public WeaponPickup(){
        EventBroker.getInstance().addListener(this, EventType.COLLISION);
    }
    @Override
    public void onTrigger(EventType eventType, Entity... entities) {
        for (Entity entity : entities) {
            if(entity != this){
                continue;
            }
            for (Entity other : entities) {
                if(other instanceof Player){
                    EventBroker.getInstance().triggerEvent(EventType.WEAPON_PICKUP,other);
                    setActive(false);
                    return;
                }
            }
        }
    }
}
