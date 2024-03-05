package dk.sdu.mmmi.cbse.multishotWeapon;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.weapon.CommonWeapon;
import dk.sdu.mmmi.cbse.weapon.WeaponControlSystem;

public class MultishotWeapon extends CommonWeapon {
    @Override
    public void shoot(GameData gameData, World world, Entity entity, BulletSPI spi) {
        Entity bullet = createBullet(gameData,entity,spi,45);
        Entity bullet1 = createBullet(gameData,entity,spi,-45);
        Entity bullet2 = createBullet(gameData, entity, spi,0);
        world.addEntity(bullet2);
        world.addEntity(bullet1);
        world.addEntity(bullet);
    }

    private Entity createBullet(GameData gameData, Entity entity, BulletSPI spi, int rotation) {
        Entity bullet = spi.createBullet(entity, gameData);
        bullet.setRGB(255,255,255);
        bullet.setRotation(entity.getRotation()+rotation);
        return bullet;
    }


}
