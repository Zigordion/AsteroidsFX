package dk.sdu.mmmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;

/**
 *
 * @author corfixen
 */
public class Bullet extends Entity {
    private final long creationTime;
    public Bullet(){
        canCollide = false;
        creationTime = System.currentTimeMillis();
    }
    public long getCreationTime() {
        return creationTime;
    }

}
