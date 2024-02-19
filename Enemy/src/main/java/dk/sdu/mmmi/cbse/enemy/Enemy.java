package dk.sdu.mmmi.cbse.enemy;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;

public class Enemy extends Entity {
    @Override
    public void onHit(Entity other) {
        if(other instanceof Bullet bullet){
            if(bullet.getShooter()!=this){
                setActive(false);
            }
        }
        else if(other instanceof Enemy){
            return;
        }else {
            setActive(false);
        }
    }
}
