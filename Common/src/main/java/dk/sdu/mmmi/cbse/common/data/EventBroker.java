package dk.sdu.mmmi.cbse.common.data;

import dk.sdu.mmmi.cbse.common.services.AEventListener;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EventBroker implements IPostEntityProcessingService {

    Map<AEventListener, EventType> listenerTopicMap;

    private EventBroker(){}
    private static EventBroker instance;
    public static EventBroker getInstance(){
        if(instance == null){
            instance = new EventBroker();
        }
        return instance;
    }

    public void addListener(AEventListener eventListener, EventType eventType){
        listenerTopicMap.put(eventListener, eventType);
    }
    public void removeListener(AEventListener eventListener){
        listenerTopicMap.remove(eventListener);
    }

    public void triggerEvent(EventType eventType){
        for (AEventListener eventListener : listenerTopicMap.keySet()) {
            if(listenerTopicMap.get(eventListener) == eventType){
                eventListener.onTrigger(eventType);
            }
        }
    }
    public void triggerEvent(EventType eventType,Entity entity, Entity entity2){
        for (AEventListener eventListener : listenerTopicMap.keySet()) {
            if(listenerTopicMap.get(eventListener) == eventType){
                eventListener.onTrigger(eventType);
            }
        }
    }


    @Override
    public void postProcess(GameData gameData, World world) {
        List<AEventListener> eventListeners = getIEventListeners();
        for (AEventListener eventListener : eventListeners) {
            if(!listenerTopicMap.containsKey(eventListener)){
                for (EventType topic : eventListener.getTopics()) {
                    addListener(eventListener, topic);
                }
            }
        }
    }

    private List<AEventListener> getIEventListeners(){
        return ServiceLoader.load(AEventListener.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
