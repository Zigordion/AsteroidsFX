package dk.sdu.mmmi.cbse.enemy;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EventBroker;
import dk.sdu.mmmi.cbse.common.data.EventType;
import dk.sdu.mmmi.cbse.common.services.AEventListener;

public class Enemy extends AEventListener {

    public Enemy(){
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
