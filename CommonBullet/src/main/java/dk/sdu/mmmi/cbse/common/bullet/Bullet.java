package dk.sdu.mmmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EventBroker;
import dk.sdu.mmmi.cbse.common.data.EventType;
import dk.sdu.mmmi.cbse.common.services.IEventListener;

/**
 *
 * @author corfixen
 */
public class Bullet extends Entity implements IEventListener {
    public Bullet(){
        EventBroker.getInstance().addListener(this,EventType.COLLISION);
    }
    @Override
    public void onTrigger(EventType eventType, Entity... entities) {
        if(entities[0] == this){
            setActive(false);
            EventBroker.getInstance().removeListener(this);
        }
    }
}
