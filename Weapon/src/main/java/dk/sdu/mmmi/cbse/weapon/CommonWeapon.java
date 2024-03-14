package dk.sdu.mmmi.cbse.weapon;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public class CommonWeapon {
    /*
    Pickup entity?
    Weapon extends pickup entity?

    Add weapon to active weapon in weapon Control System when collision with player is detected?
    each weapon will contain a method which determines the way the bullet is spawned

    2 types of weapons:
    multishot: shoot 2 bullets straight ahead
    spreadshot: shoot 3 bullets spread out 45 deg. apart


     */

    public void shoot(GameData gameData, World world, Entity entity, BulletSPI spi){
        Entity bullet =  spi.createBullet(entity, gameData);
        bullet.setRGB(255,255,255);
        world.addEntity(bullet);
    }


}
