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
        if(listenerTopicMap.containsKey(eventListener)){
            List<EventType> listenerEvents = listenerTopicMap.get(eventListener);
            listenerEvents.addAll(Arrays.asList(eventType));
            listenerTopicMap.replace(eventListener,listenerEvents);
        }else{
            listenerTopicMap.put(eventListener, new ArrayList<>(Arrays.asList(eventType)));
        }
    }
    public void removeListener(IEventListener eventListener, EventType ... eventType){
        List<EventType> listenerEvents = listenerTopicMap.get(eventListener);
        for (EventType listenerEvent : listenerTopicMap.get(eventListener)) {
            for (EventType type : eventType) {
                if(listenerEvent == type){
                    listenerEvents.remove(listenerEvent);
                }
            }
        }
        listenerTopicMap.replace(eventListener,listenerEvents);
        if(listenerEvents.isEmpty()){
            listenerTopicMap.remove(eventListener);
        }
    }

    public void triggerEvent(Event event){
        for (IEventListener eventListener : listenerTopicMap.keySet()) {
            if(listenerTopicMap.get(eventListener).contains(event.getEventType())){
                eventListener.onTrigger(event);
            }
        }
    }

}
