package dk.sdu.mmmi.cbse.pickup;

import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.services.IEventListener;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.List;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class PickupGeneratorController implements IEventListener, IGamePluginService {

    private static World world;
    private double pickupGenerationChance = 0.3;
    @Override
    public void onTrigger(EventType eventType, Entity... entities) {
        List<? extends PickupSPI> pickupGenerators = getPickupSPI();
        Random random = new Random();
        if(random.nextDouble() < pickupGenerationChance){
            int index = random.nextInt(0,pickupGenerators.size());
            Pickup pickup = pickupGenerators.get(index).createPickup(entities[0]);
            world.addEntity(pickup);
        }
    }
    private List<? extends PickupSPI> getPickupSPI() {
        return ServiceLoader.load(PickupSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Override
    public void start(GameData gameData, World world) {
        PickupGeneratorController.world = world;
        EventBroker.getInstance().addListener(this,EventType.GENERATE_PICKUP);
    }

    @Override
    public void stop(GameData gameData, World world) {
        EventBroker.getInstance().removeListener(this,EventType.GENERATE_PICKUP);
    }
}