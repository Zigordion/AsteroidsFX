package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.services.*;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class ServiceLocator {
    public List<IEntityProcessingService> entityProcessingServiceList(){
        return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    public List<IGamePluginService> gamePluginServices() {
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    public List<IPostEntityProcessingService> postEntityProcessingServices() {
        return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    public List<IUIProcessingService> uiProcessingServices() {
        return ServiceLoader.load(IUIProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
    public List<ILateStartService> lateStartServices() {
        return ServiceLoader.load(ILateStartService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
