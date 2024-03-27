package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.ServiceLoader;
import static java.util.stream.Collectors.toList;

@Configuration
public class ServiceConfig {
    public ServiceConfig(){}
    @Bean
    public GameManager game(){
        return new GameManager(gamePluginServices(),
                entityProcessingServiceList(),
                postEntityProcessingServices(),
                uiProcessingServices(),
                lateStartServices());
    }

    @Bean
    public List<IEntityProcessingService> entityProcessingServiceList(){
        return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public List<IGamePluginService> gamePluginServices() {
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public List<IPostEntityProcessingService> postEntityProcessingServices() {
        return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public List<IUIProcessingService> uiProcessingServices() {
        return ServiceLoader.load(IUIProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
    @Bean
    public List<ILateStartService> lateStartServices() {
        return ServiceLoader.load(ILateStartService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

}
