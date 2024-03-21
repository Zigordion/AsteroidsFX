package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EventType;

import java.util.List;

public abstract class AEventListener extends Entity {
    public abstract void onTrigger(EventType eventType);
    public abstract void onTrigger(EventType eventType, Entity entity, Entity other);
    private List<EventType> topics;
    public List<EventType> getTopics(){
        return topics;
    }
}
