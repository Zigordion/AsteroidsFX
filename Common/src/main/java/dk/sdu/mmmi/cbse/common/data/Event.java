package dk.sdu.mmmi.cbse.common.data;

public class Event {
	private final EventType eventType;
	private final Entity[] entities;
	private final World world;
	private final GameData gameData;

	public EventType getEventType() {
		return eventType;
	}

	public Entity[] getEntities() {
		return entities;
	}

	public World getWorld() {
		return world;
	}

	public GameData getGameData() {
		return gameData;
	}

	public Event(EventType eventType, World world, GameData gameData, Entity... entities) {
		this.eventType = eventType;
		this.entities = entities;
		this.world = world;
		this.gameData = gameData;
	}
}
