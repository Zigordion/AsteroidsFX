package dk.sdu.mmmi.cbse.enemy;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EventBroker;
import dk.sdu.mmmi.cbse.common.data.EventType;
import dk.sdu.mmmi.cbse.common.services.IEventListener;
import dk.sdu.mmmi.cbse.common.services.Interactable;

public class Enemy extends Entity implements IEventListener {

    public Enemy(){
        EventBroker.getInstance().addListener(this, EventType.COLLISION);
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
            setActive(false);
            EventBroker.getInstance().triggerEvent(EventType.GENERATE_PICKUP,this);
            EventBroker.getInstance().removeListener(this);
            break;
        }
    }

}
