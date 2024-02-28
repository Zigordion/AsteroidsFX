package dk.sdu.mmmi.cbse.common.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameUi {
    private Map<String, UiPolygonElement> uiPolygonElements = new ConcurrentHashMap<>();
    private Map<String, UiTextElement> uiTextElements = new ConcurrentHashMap<>();


    public Collection<UiPolygonElement> getUiPolygonElements() {
        return uiPolygonElements.values();
    }

    public Collection<UiTextElement> getUiTextElements() {
        return uiTextElements.values();
    }
    public void addUiTextElement(UiTextElement uiTextElement){
        uiTextElements.put(uiTextElement.getID(),uiTextElement);
    }
}
