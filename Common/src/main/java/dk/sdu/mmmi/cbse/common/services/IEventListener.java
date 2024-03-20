package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.EventType;

public interface IEventListener {
    void onTrigger(EventType eventType);

}
