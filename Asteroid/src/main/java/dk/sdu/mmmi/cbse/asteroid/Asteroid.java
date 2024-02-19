package dk.sdu.mmmi.cbse.asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;

public class Asteroid extends Entity  {
    private double xDirection;
    private double size;
    private final IAsteroidCreator asteroidCreator;
    public Asteroid(double size, IAsteroidCreator asteroidCreator){
        this.size = size;
        this.asteroidCreator = asteroidCreator;
    }

    public double getyDirection() {
        return yDirection;
    }

    public void setyDirection(double yDirection) {
        this.yDirection = yDirection;
    }

    private double yDirection;

    public double getxDirection() {
        return xDirection;
    }

    public void setxDirection(double xDirection) {
        this.xDirection = xDirection;
    }

    @Override
    public void onHit() {
        if(size/2.0>1){
            asteroidCreator.createSmallerAsteroid(this);
        }
        setActive(false);
    }

    public double getSize() {
        return size;
    }

}
