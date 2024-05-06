package dk.sdu.mmmi.cbse.common.data;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameUi {
	private Map<String, UiTextElement> uiTextElements = new ConcurrentHashMap<>();
	public Collection<UiTextElement> getUiTextElements() {
		return uiTextElements.values();
	}
	public void addUiTextElement(UiTextElement uiTextElement) {
		if (uiTextElements.containsKey(uiTextElement.getID())) {
			uiTextElements.remove(uiTextElement.getID());
		}
		uiTextElements.put(uiTextElement.getID(), uiTextElement);
	}
}
