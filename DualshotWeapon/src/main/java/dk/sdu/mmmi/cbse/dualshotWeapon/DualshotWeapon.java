package dk.sdu.mmmi.cbse.dualshotWeapon;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.weapon.CommonWeapon;

public class DualshotWeapon extends CommonWeapon {
	@Override
	public void shoot(GameData gameData, World world, Entity entity, BulletSPI spi) {
		Entity bullet = createBullet(gameData, entity, spi, 15);
		Entity bullet1 = createBullet(gameData, entity, spi, -15);
		world.addEntity(bullet1);
		world.addEntity(bullet);
	}

	private Entity createBullet(GameData gameData, Entity entity, BulletSPI spi, int rotation) {
		Entity bullet = spi.createBullet(entity, gameData);
		bullet.setRGB(255, 255, 255);
		bullet.setRotation(entity.getRotation() + rotation);
		return bullet;
	}

}
