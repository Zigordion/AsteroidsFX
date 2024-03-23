package dk.sdu.mmmi.cbse.enemy;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EventBroker;
import dk.sdu.mmmi.cbse.common.data.EventType;
import dk.sdu.mmmi.cbse.common.services.IEventListener;
import dk.sdu.mmmi.cbse.common.services.Interactable;

public class Enemy extends Entity implements IEventListener {
    private final EventBroker eventBroker =  EventBroker.getInstance();

    public Enemy(){
        eventBroker.addListener(this, EventType.COLLISION);
    }

    @Override
    public void onTrigger(EventType eventType, Entity ... entities) {
        if(entities[0] == this){
            if(entities[1] instanceof Enemy){
                return;
            }
            eventBroker.triggerEvent(EventType.GENERATE_PICKUP,this);
            eventBroker.removeListener(this);
            setActive(false);
        }
    }

}
