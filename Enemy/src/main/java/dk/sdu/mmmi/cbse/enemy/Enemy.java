package dk.sdu.mmmi.cbse.enemy;
import dk.sdu.mmmi.cbse.common.data.Entity;

public class Enemy extends Entity {
    @Override
    public void onHit(Entity other) {
         if(!(other instanceof Enemy)){
            setActive(false);
            super.onHit(other);
        }
    }
}
