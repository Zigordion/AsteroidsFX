package dk.sdu.mmmi.cbse.asteroid;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;

public class Asteroid extends Entity  {
    private double xDirection;
    private final double size;
    private boolean isHit = false;
    private final IAsteroidCreator asteroidCreator;
    public Asteroid(double size, IAsteroidCreator asteroidCreator){
        this.size = size;
        this.asteroidCreator = asteroidCreator;
    }

    public double getYDirection() {
        return yDirection;
    }

    public void setYDirection(double yDirection) {
        this.yDirection = yDirection;
    }

    private double yDirection;

    public double getXDirection() {
        return xDirection;
    }

    public void setXDirection(double xDirection) {
        this.xDirection = xDirection;
    }

    @Override
    public void onHit(Entity other) {
        if(!(other instanceof Bullet)){
            return;
        }
        if(size/2.0>1){
            asteroidCreator.createSmallerAsteroid(this);
        }
        setActive(false);
    }

    public double getSize() {
        return size;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        this.isHit = hit;
    }
}
