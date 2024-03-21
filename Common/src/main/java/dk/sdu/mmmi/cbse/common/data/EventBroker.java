package dk.sdu.mmmi.cbse.common.data;

import dk.sdu.mmmi.cbse.common.services.IEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventBroker {

    private final Map<IEventListener, EventType> listenerTopicMap = new ConcurrentHashMap<>();

    private EventBroker(){}
    private static EventBroker instance;
    public static EventBroker getInstance(){
        if(instance == null){
            instance = new EventBroker();
        }
        return instance;
    }

    public void addListener(IEventListener eventListener, EventType eventType){
        listenerTopicMap.put(eventListener, eventType);
    }
    public void removeListener(IEventListener eventListener){
        listenerTopicMap.remove(eventListener);
    }

    public void triggerEvent(EventType eventType,Entity ... entities){
        for (IEventListener eventListener : listenerTopicMap.keySet()) {
            if(listenerTopicMap.get(eventListener) == eventType){
                eventListener.onTrigger(entities);
            }
        }
    }

}
