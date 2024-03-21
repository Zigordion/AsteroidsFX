package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EventBroker;
import dk.sdu.mmmi.cbse.common.data.EventType;
import dk.sdu.mmmi.cbse.common.services.IEventListener;

/**
 *
 * @author Emil
 */
public class Player extends Entity implements IEventListener {


    public Player(){
        EventBroker.getInstance().addListener(this,EventType.COLLISION);
    }

    @Override
    public void onTrigger(EventType eventType, Entity ... entities) {
        for (Entity entity : entities) {
            if(entity == this){
                EventBroker.getInstance().triggerEvent(EventType.PLAYER_HIT,entity);
                break;
            }
        }
    }

}
