package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.services.IEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AsteroidPlugin implements IEventListener  {
    private static double timer;
    private final double maxTimer = 400;
    private World world;
    private List<Asteroid> asteroids = new ArrayList<>();
    private final Random random = new Random();
    private final EventBroker eventBroker = EventBroker.getInstance();
    public AsteroidPlugin (){
        eventBroker.addListener(this,EventType.COLLISION);
    }
    public void process(double deltaTime, GameData gameData, World world){
        timer -= deltaTime;
        this.world = world;
        if (timer <= 0) {
            Entity asteroid = createNewAsteroid(gameData);
            world.addEntity(asteroid);
            timer = maxTimer;
        }
    }


    private Entity createNewAsteroid(GameData gameData){
        Asteroid asteroid = new Asteroid(random.nextDouble(4,7));
        asteroid.setRGB(140,140,140);
        asteroid.setPolygonCoordinates(
                -8,0,
                -6,3,
                -4,4.75,
                -1.25,4.6,
                0.5,2,
                4,1.3,
                4,-1.5,
                2,-2.78,
                4,-4,
                2,-5.25,
                0,-6,
                -4,-6,
                -6,-3);
        double[] polygons = asteroid.getPolygonCoordinates();
        double[] newPolygons = new double[polygons.length];
        for (int i = 0; i < polygons.length; i++) {
            newPolygons[i] = polygons[i]* asteroid.getSize();
        }
        asteroid.setPolygonCoordinates(newPolygons);
        asteroid.setX(random.nextDouble(5, gameData.getDisplayWidth() - 5));
        asteroid.setY(random.nextDouble(5, gameData.getDisplayHeight() - 5));
        asteroid.setActive(true);
        double xPos = asteroid.getX();
        double yPos = asteroid.getY();
        double xDirection =(gameData.getDisplayWidth()/2.0)-xPos;
        double yDirection =(gameData.getDisplayHeight()/2.0)-yPos;
        double magnitude = Math.sqrt(xDirection*xDirection+yDirection*yDirection);
        double normalizedX = xDirection/magnitude;
        double normalizedY = yDirection/magnitude;
        asteroid.setXDirection(normalizedX);
        asteroid.setYDirection(normalizedY);
        asteroids.add(asteroid);
        return asteroid;
    }

    public void createSmallerAsteroid(Asteroid prevAsteroid) {
        double[] polygons = prevAsteroid.getPolygonCoordinates();
        double[] newPolygons = new double[polygons.length];

        double newSizeModifier = 0.7;
        for (int i = 0; i < polygons.length; i++) {
            newPolygons[i] = polygons[i]* newSizeModifier;
        }

        int asteroidsPrDestruction = 2;
        for (int i = 0; i < asteroidsPrDestruction; i++) {
            Asteroid asteroid = new Asteroid(prevAsteroid.getSize() * newSizeModifier);
            asteroid.setRGB(prevAsteroid.getRedValue(),prevAsteroid.getGreenValue(),prevAsteroid.getBlueValue());
            asteroid.setPolygonCoordinates(newPolygons);
            asteroid.setX(prevAsteroid.getX());
            asteroid.setY(prevAsteroid.getY());
            asteroid.setYDirection(random.nextDouble(-1,1.001));
            asteroid.setXDirection(random.nextDouble(-1,1.001));
            asteroid.setActive(true);
            asteroids.add(asteroid);
            world.addEntity(asteroid);
        }
    }

    @Override
    public void onTrigger(EventType eventType, Entity... entities) {
        for (Asteroid asteroid : asteroids) {
            if(entities[0] == asteroid && entities[1] instanceof Bullet){
                if(asteroid.getSize()/2.0>1.5){
                    createSmallerAsteroid(asteroid);
                }
                eventBroker.triggerEvent(EventType.ASTEROID_DESTROYED,asteroid);
                asteroid.setActive(false);
                asteroids.remove(asteroid);
                break;
            }

        }
    }
}
