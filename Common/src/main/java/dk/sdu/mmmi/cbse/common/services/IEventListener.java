package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EventType;

public interface IEventListener {
     /*
     * REMEMBER TO ADD TOPIC TO EVENT BROKER
      */
     void onTrigger(EventType eventType, Entity ... entities);

}
