package dk.sdu.mmmi.cbse.common.data;

import dk.sdu.mmmi.cbse.common.services.IEventListener;

import java.util.List;
import java.util.Map;

public class EventBroker {

    Map<IEventListener, EventType> listenerTopicMap;

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
    
    public void triggerEvent(EventType eventType){
        for (IEventListener eventListener : listenerTopicMap.keySet()) {
            if(listenerTopicMap.get(eventListener) == eventType){
                eventListener.onTrigger(eventType);
            }
        }
    }



}
