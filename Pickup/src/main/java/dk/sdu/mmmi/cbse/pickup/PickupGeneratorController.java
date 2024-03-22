package dk.sdu.mmmi.cbse.pickup;

import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.services.IEventListener;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.List;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class PickupGeneratorController implements IEventListener, IGamePluginService {

    @Override
    public void onTrigger(EventType eventType, Entity... entities) {
        List<? extends PickupSPI> pickupGenerators = getPickupSPI();
        Random random = new Random();
        int index = random.nextInt(0,pickupGenerators.size());
        pickupGenerators.get(index).createPickup(entities[0]);

        //Do random check for if a pickup should be generated.
        //find all available pickup types
        //spawn them at the first entity's location
    }

    /*
    A pickup is an entity which triggers an event
    Health pickup: player health system listens to this and increases player health by 1
    Weapon pickup: weapon control system generates a new random weapon for the entity that picked up the item.
        Note that for that to work, the weapon system needs to know if the entity colliding with the pickup item is a
        weapon user.
     */
    private List<? extends PickupSPI> getPickupSPI() {
        return ServiceLoader.load(PickupSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Override
    public void start(GameData gameData, World world) {
        EventBroker.getInstance().addListener(this,EventType.GENERATE_PICKUP);
    }

    @Override
    public void stop(GameData gameData, World world) {
        EventBroker.getInstance().removeListener(this,EventType.GENERATE_PICKUP);
    }
}