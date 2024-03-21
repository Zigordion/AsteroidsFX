package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EventBroker;
import dk.sdu.mmmi.cbse.common.data.EventType;
import dk.sdu.mmmi.cbse.common.services.AEventListener;

/**
 *
 * @author Emil
 */
public class Player extends Entity implements AEventListener {

    public Player(){
        EventBroker.getInstance().addListener(this,EventType.COLLISION);
    }
    @Override
    public void onTrigger(EventType eventType) {

    }

    @Override
    public void onTrigger(EventType eventType, Entity entity, Entity other) {
        if(eventType == EventType.COLLISION && entity == this){
            setActive(false);
        }
    }
}
