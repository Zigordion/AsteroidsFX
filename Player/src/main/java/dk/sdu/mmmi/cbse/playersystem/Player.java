package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EventBroker;
import dk.sdu.mmmi.cbse.common.data.EventType;
import dk.sdu.mmmi.cbse.common.services.IEventListener;
import dk.sdu.mmmi.cbse.common.services.Interactable;

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
            if(entity!= this){
                continue;
            }
            for (Entity other : entities) {
                if(other instanceof Interactable){
                    return;
                }
            }
            EventBroker.getInstance().triggerEvent(EventType.PLAYER_HIT,this);
            break;
        }
    }
}
