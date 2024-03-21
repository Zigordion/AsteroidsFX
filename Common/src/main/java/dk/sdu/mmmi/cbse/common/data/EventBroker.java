package dk.sdu.mmmi.cbse.common.data;

import dk.sdu.mmmi.cbse.common.services.IEventListener;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class EventBroker {

    private final Map<IEventListener, List<EventType>> listenerTopicMap = new ConcurrentHashMap<>();
    private EventBroker(){}
    private static EventBroker instance;
    public static EventBroker getInstance(){
        if(instance == null){
            instance = new EventBroker();
        }
        return instance;
    }

    public void addListener(IEventListener eventListener, EventType ... eventType){
        for (EventType type : eventType) {
            if(type == EventType.SHOOT){
                System.out.println("added");
            }
        }
        listenerTopicMap.put(eventListener, List.of(eventType));
    }
    public void removeListener(IEventListener eventListener, EventType ... eventType){
        List<EventType> listenerEvents = listenerTopicMap.get(eventListener);
        for (EventType listenerEvent : listenerEvents) {
            listenerEvents.remove(listenerEvent);
        }
        if(listenerEvents.isEmpty()){
            listenerTopicMap.remove(eventListener);
        }
    }

    public void triggerEvent(EventType eventType,Entity ... entities){
        for (IEventListener eventListener : listenerTopicMap.keySet()) {
            if(listenerTopicMap.get(eventListener).contains(eventType)){
                eventListener.onTrigger(eventType, entities);
                if(eventType == EventType.SHOOT){
                    System.out.println("sh");
                }
            }
        }
    }

}
