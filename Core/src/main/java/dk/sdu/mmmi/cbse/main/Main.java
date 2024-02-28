package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import java.util.Collection;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import static java.util.stream.Collectors.toList;

import dk.sdu.mmmi.cbse.common.services.IUIProcessingService;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
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

public class Main extends Application {

    private final GameData gameData = new GameData();
    private final World world = new World();
    private final GameUi gameUi = new GameUi();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
    private final Map<UiTextElement,Text> elementTextMap = new ConcurrentHashMap<>();
    private final Pane gameWindow = new Pane();

    public static void main(String[] args) {
        launch(Main.class);
    }

    @Override
    public void start(Stage window) throws Exception {
        //Score part should be extracted to separate module
        UiTextElement score = new UiTextElement("Destroyed asteroids: ",10,20,255,0,255);
        gameUi.addUiTextElement(score);
        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        gameWindow.setBackground(background);
        Scene scene = initiateScene();

        // Lookup all Game Plugins using ServiceLoader
        for (IGamePluginService iGamePlugin : getPluginServices()) {
            iGamePlugin.start(gameData, world);
        }
        draw();

        render();

        window.setScene(scene);
        window.setTitle("ASTEROIDS");
        window.show();

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

    private void render() {
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

        // Update
        for (IEntityProcessingService entityProcessorService : getEntityProcessingServices()) {
            entityProcessorService.process(deltaTime, gameData, world);
        }
        for (IPostEntityProcessingService postEntityProcessorService : getPostEntityProcessingServices()) {
            postEntityProcessorService.postProcess(gameData, world);
        }
        for (IUIProcessingService uiProcessingService : getIUIProcessingServices()) {
            uiProcessingService.process(gameData, gameUi);
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
        for (UiPolygonElement polygonElement : gameUi.getUiPolygonElements()) {
            //Each element
        }
    }

    private Collection<? extends IGamePluginService> getPluginServices() {
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends IPostEntityProcessingService> getPostEntityProcessingServices() {
        return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
    private Collection<? extends IUIProcessingService> getIUIProcessingServices() {
        return ServiceLoader.load(IUIProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
