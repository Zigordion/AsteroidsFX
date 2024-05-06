package dk.sdu.mmmi.cbse.weapon;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public class CommonWeapon {
	public void shoot(GameData gameData, World world, Entity entity, BulletSPI spi) {
		Entity bullet = spi.createBullet(entity, gameData);
		bullet.setRGB(255, 255, 255);
		world.addEntity(bullet);
	}
}
