package dk.sdu.mmmi.cbse.weapon;

import static java.util.stream.Collectors.toList;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IEventListener;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.util.EventBroker;
import dk.sdu.mmmi.commonweapon.CommonWeapon;
import dk.sdu.mmmi.commonweapon.WeaponSPI;
import java.util.*;

public class WeaponControlSystem implements IGamePluginService, IEntityProcessingService, IEventListener {
	private final Map<String, CommonWeapon> weaponMap = new HashMap<>();
	private static GameData gameData;
	private static World world;

	private void shoot(GameData gameData, World world, Entity entity) {
		CommonWeapon currentWeapon = weaponMap.get(entity.getID());
		if (currentWeapon == null) {
			currentWeapon = new CommonWeapon();
		}
		CommonWeapon finalCurrentWeapon = currentWeapon;
		getBulletSPIs().stream().findFirst().ifPresent(spi -> {
			finalCurrentWeapon.shoot(gameData, world, entity, spi);
		});
	}

	private Collection<? extends BulletSPI> getBulletSPIs() {
		return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
	}

	public void setRandomWeapon(Entity shooter) {
		List<? extends WeaponSPI> weaponSPIs = getWeaponSPI();
		Random random = new Random();
		weaponMap.remove(shooter.getID());
		weaponMap.put(shooter.getID(), weaponSPIs.get(random.nextInt(0, weaponSPIs.size())).createWeapon());
	}

	private List<? extends WeaponSPI> getWeaponSPI() {
		return ServiceLoader.load(WeaponSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
	}

	@Override
	public void onTrigger(Event event) {
		if (event.getEventType().equals(EventType.SHOOT)) {
			for (Entity entity : event.getEntities()) {
				shoot(gameData, world, entity);
			}
		}
		if (event.getEventType().equals(EventType.WEAPON_PICKUP)) {
			for (Entity entity : event.getEntities()) {
				setRandomWeapon(entity);
			}
		}
		if (event.getEventType().equals(EventType.PLAYER_HIT)) {
			CommonWeapon commonWeapon = new CommonWeapon();
			weaponMap.replace(event.getEntities()[0].getID(), commonWeapon);
		}
	}

	@Override
	public void process(double deltaTime, GameData gameData, World world) {
		WeaponControlSystem.gameData = gameData;
		WeaponControlSystem.world = world;
	}

	@Override
	public void start(GameData gameData, World world) {
		EventBroker.getInstance().addListener(this, EventType.SHOOT, EventType.WEAPON_PICKUP, EventType.PLAYER_HIT);
	}

	@Override
	public void stop(GameData gameData, World world) {
		EventBroker.getInstance().removeListener(this, EventType.WEAPON_PICKUP, EventType.SHOOT, EventType.PLAYER_HIT);
	}
}
