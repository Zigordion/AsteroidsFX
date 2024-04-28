package dk.sdu.mmmi.cbse.splitpackage;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class HelloWorld implements IPostEntityProcessingService {

    @Override
    public void postProcess(GameData gameData, World world) {
        System.out.println("Hello World in Bullet module");
    }
}
