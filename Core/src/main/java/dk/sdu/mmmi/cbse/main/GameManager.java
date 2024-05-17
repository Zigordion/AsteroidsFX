package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.services.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import static java.util.stream.Collectors.toList;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameManager {

    private final GameData gameData = new GameData();
    private final World world = new World();
    private final GameUi gameUi = new GameUi();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
    private final Map<UiTextElement,Text> elementTextMap = new ConcurrentHashMap<>();
    private final Pane gameWindow = new Pane();
    private final List<IEntityProcessingService> entityProcessingServices;
    private final List<IGamePluginService> gamePluginServices;
    private final List<IUIProcessingService> uiProcessingServices;
    private final List<IPostEntityProcessingService> postEntityProcessingServices;
    private final List<ILateStartService> lateStartServices;

    public GameManager(List<IEntityProcessingService> entityProcessingServices,
                       List<IGamePluginService> gamePluginServices,
                       List<IUIProcessingService> uiProcessingServices,
                       List<IPostEntityProcessingService> postEntityProcessingServices,
                       List<ILateStartService> lateStartServices) {
        this.entityProcessingServices = entityProcessingServices;
        this.gamePluginServices = gamePluginServices;
        this.uiProcessingServices = uiProcessingServices;
        this.postEntityProcessingServices = postEntityProcessingServices;
        this.lateStartServices = lateStartServices;
    }


    public void start(Stage window) throws Exception {

        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        gameWindow.setBackground(background);
        Scene scene = initiateScene();

        // Lookup all Game Plugins using ServiceLoader
        for (IGamePluginService iGamePlugin : gamePluginServices) {
            iGamePlugin.start(gameData, world);
        }
        draw();

        render();

        window.setScene(scene);
        window.setTitle("ASTEROIDS");
        window.show();
        for (ILateStartService lateStartService : lateStartServices) {
            lateStartService.lateStart(gameData, world);
        }
    }

    private Scene initiateScene() {
        Scene scene = new Scene(gameWindow);
        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKey(GameKeys.LEFT, true);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, true);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKey(GameKeys.UP, true);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, true);
            }
        });
        scene.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKey(GameKeys.LEFT, false);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, false);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKey(GameKeys.UP, false);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, false);
            }
        });
        return scene;
    }

    void render() {
        new AnimationTimer() {
            private long then = 0;
            @Override
            public void handle(long now) {
                long deltaTime = now-then;
                then = now;
                update(deltaTime/10_000_000.0);
                draw();
                drawUI();
                gameData.getKeys().update();
            }

        }.start();
    }

    private void update(double deltaTime) {
        for (IEntityProcessingService entityProcessorService : entityProcessingServices) {
            entityProcessorService.process(deltaTime, gameData, world);
        }
        for (IPostEntityProcessingService postEntityProcessorService : postEntityProcessingServices) {
            postEntityProcessorService.postProcess(gameData, world);
        }
        for (IUIProcessingService uiProcessingService : uiProcessingServices) {
            uiProcessingService.processUI(gameData, gameUi);
        }

    }

    private void draw() {
        for (Entity entity : world.getEntities()) {
            if(!polygons.containsKey(entity)){
                Polygon polygon = new Polygon(entity.getPolygonCoordinates());
                Color color = Color.rgb(
                        entity.getRedValue(),
                        entity.getGreenValue(),
                        entity.getBlueValue());
                polygon.setFill(color);
                polygons.put(entity,polygon);
                gameWindow.getChildren().add(polygon);
            }
            if(!entity.isActive()){
                if(entity instanceof IEventListener eventListener){
                    EventBroker.getInstance().removeListener(eventListener);
                }
                world.removeEntity(entity);
                gameWindow.getChildren().remove(polygons.get(entity));
                polygons.remove(entity);
            }else {
                Polygon polygon = polygons.get(entity);
                polygon.setTranslateX(entity.getX());
                polygon.setTranslateY(entity.getY());
                polygon.setRotate(entity.getRotation());
            }
        }

    }
    private void drawUI(){
        // go through each element in game ui and update ui accordingly
        for (UiTextElement textElement : gameUi.getUiTextElements()) {
            Text text;
            if(!elementTextMap.containsKey(textElement)){
                text = new Text(textElement.getText());
                text.setY(textElement.getY());
                text.setX(textElement.getX());

                elementTextMap.put(textElement,text);
                gameWindow.getChildren().add(text);
            }
            else {
                text = elementTextMap.get(textElement);
            }
            Color color = Color.rgb(
                    textElement.getRedValue(),
                    textElement.getGreenValue(),
                    textElement.getBlueValue());
            text.setFill(color);
            text.setText(textElement.getText());
            text.setX(textElement.getX());
            text.setY(textElement.getY());
        }
    }

}
