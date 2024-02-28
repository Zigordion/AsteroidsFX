package dk.sdu.mmmi.cbse.common.data;

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
        if(uiTextElements.containsKey(uiTextElement.getID())){
//            if(uiTextElements.get(uiTextElement.getID()).getText().equals(uiTextElement.getText())){
//                return;
//            }
            uiTextElements.remove(uiTextElement.getID());
        }
        uiTextElements.put(uiTextElement.getID(),uiTextElement);
    }
}
