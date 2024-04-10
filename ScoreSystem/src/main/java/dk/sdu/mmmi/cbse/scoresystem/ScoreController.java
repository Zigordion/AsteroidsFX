package dk.sdu.mmmi.cbse.scoresystem;


import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.services.IEventListener;
import dk.sdu.mmmi.cbse.common.services.IUIProcessingService;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ScoreController implements IUIProcessingService, IEventListener {
    private static int score;
    public ScoreController(){
        EventBroker.getInstance().addListener(this, EventType.ASTEROID_DESTROYED);
    }
    private final UiTextElement scoreUI = new UiTextElement("" + score,10,20,255,255,255);

    @Override
    public void processUI(GameData gameData, GameUi gameUi) {
        scoreUI.setText("Destroyed asteroids: " + score);
        gameUi.addUiTextElement(scoreUI);
    }

    @Override
    public void onTrigger(EventType eventType, Entity... entities) {
        HttpClient client = HttpClient.newHttpClient();

        // Define the URL of the API you want to call
        String apiUrl = "http://localhost:8080/api/score";

        // Create an HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            score = Integer.parseInt(response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
