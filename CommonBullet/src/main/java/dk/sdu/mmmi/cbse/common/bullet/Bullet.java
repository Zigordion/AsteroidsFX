package dk.sdu.mmmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EventBroker;
import dk.sdu.mmmi.cbse.common.data.EventType;
import dk.sdu.mmmi.cbse.common.services.IEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author corfixen
 */
public class Bullet extends Entity implements IEventListener {
    public Bullet(){
        EventBroker.getInstance().addListener(this,EventType.COLLISION);
    }
    @Override
    public void onTrigger(Entity... entities) {
        for (Entity entity : entities) {
            if(entity == this){
                setActive(false);
                EventBroker.getInstance().removeListener(this);
                break;
            }
        }
    }
}
