package dk.sdu.mmmi.cbse.enemy;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EventBroker;
import dk.sdu.mmmi.cbse.common.data.EventType;
import dk.sdu.mmmi.cbse.common.services.IEventListener;

public class Enemy extends Entity implements IEventListener {

    public Enemy(){
        EventBroker.getInstance().addListener(this, EventType.COLLISION);
    }

    @Override
    public void onTrigger(Entity ... entities) {
        for (Entity entity : entities) {
            if(entity == this){
                setActive(false);
                EventBroker.getInstance().removeListener(this);
                break;
            }
        }
    }

}
