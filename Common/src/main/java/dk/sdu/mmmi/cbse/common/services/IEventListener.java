package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.Event;

public interface IEventListener {
	/**
	 * This method is called when an event is triggered. Remember to subscribe to an
	 * EventType in order to receive the event trigger
	 * 
	 * @param event
	 *            The event that triggered this method call.
	 */
	void onTrigger(Event event);

}
