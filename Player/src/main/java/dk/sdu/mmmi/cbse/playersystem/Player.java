package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;

/**
 *
 * @author Emil
 */
public class Player extends Entity {

    @Override
    public void onHit(Entity other) {
        if(other instanceof Bullet bullet){
            if(bullet.getShooter()==this){
                return;
            }
        }
        super.onHit(other);
    }
}
