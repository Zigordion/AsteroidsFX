package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EventType;

import java.util.List;

public interface IEventListener {
     /*
     * REMEMBER TO ADD TOPIC TO EVENT BROKER
      */
     void onTrigger(Entity ... entities);

}
