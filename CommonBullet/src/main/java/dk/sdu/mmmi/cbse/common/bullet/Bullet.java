package dk.sdu.mmmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;

/**
 *
 * @author corfixen
 */
public class Bullet extends Entity {
    private final Entity shooter;
    public Bullet(Entity shooter){
        this.shooter =shooter;
    }
    @Override
    public void onHit(Entity other) {
        if(other == shooter){
            return;
        }
        setActive(false);
    }

    public Entity getShooter() {
        return shooter;
    }
}
