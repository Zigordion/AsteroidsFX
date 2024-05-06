package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.Event;
import dk.sdu.mmmi.cbse.common.data.Entity;

public interface IEventListener {
     /*
     * REMEMBER TO ADD TOPIC TO EVENT BROKER
      */
     void onTrigger(Event event);

}
