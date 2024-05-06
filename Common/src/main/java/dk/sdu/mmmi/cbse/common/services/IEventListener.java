package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.Event;

public interface IEventListener {
	/*
	 * REMEMBER TO ADD TOPIC TO EVENT BROKER
	 */
	void onTrigger(Event event);

}
