package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Random;

public class AsteroidControlSystem implements IEntityProcessingService, IAsteroidCreator {
    private static double timer;
    private final double maxTimer = 200;
    private final double speed = 0.6;
    private final double rotationSpeed = 1;
    private final int asteroidsPrDestruction = 3;
    private final double newSizeModifier = 0.7;
    private Random random = new Random();
    private World world;
    @Override
    public void process(double deltaTime, GameData gameData, World world) {
        timer -= 1; //should include delta time
        this.world = world;
        if (timer <= 0) {
            Entity asteroid = createNewAsteroid(gameData);
            world.addEntity(asteroid);
            timer = maxTimer;
        }
        for (Entity entity : world.getEntities(Asteroid.class)) {
            if(entity instanceof Asteroid asteroid){
                asteroid.setX((asteroid.getX()+asteroid.getXDirection()*deltaTime*speed)% gameData.getDisplayWidth());
                asteroid.setY((asteroid.getY()+asteroid.getYDirection()*deltaTime*speed)% gameData.getDisplayHeight());
                asteroid.setRotation(asteroid.getRotation()+rotationSpeed*deltaTime);
                if (asteroid.getY() < 0) {
                    asteroid.setY(gameData.getDisplayHeight()-1);
                }
                if (asteroid.getX() < 0) {
                    asteroid.setX(gameData.getDisplayWidth()-1);
                }
            }
        }
    }

    private Entity createNewAsteroid(GameData gameData){
        Asteroid asteroid = new Asteroid(random.nextDouble(4,7), this);
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

        return asteroid;
    }

    @Override
    public void createSmallerAsteroid(Asteroid prevAsteroid) {
        if(prevAsteroid.isHit()){
            return;
        }
        prevAsteroid.setHit(true);//prevents multiple calls to the same asteroid
        double[] polygons = prevAsteroid.getPolygonCoordinates();
        double[] newPolygons = new double[polygons.length];

        for (int i = 0; i < polygons.length; i++) {
            newPolygons[i] = polygons[i]*newSizeModifier;
        }

        for (int i = 0; i < asteroidsPrDestruction; i++) {
            //Gets called twice for some reason
            Asteroid asteroid = new Asteroid(prevAsteroid.getSize() * newSizeModifier,this);
            asteroid.setPolygonCoordinates(newPolygons);
            asteroid.setX(prevAsteroid.getX());
            asteroid.setY(prevAsteroid.getY());
            asteroid.setYDirection(random.nextDouble(-1,1.001));
            asteroid.setXDirection(random.nextDouble(-1,1.001));
            asteroid.setActive(true);
            //add to world somehow
            world.addEntity(asteroid);
        }
    }
}
