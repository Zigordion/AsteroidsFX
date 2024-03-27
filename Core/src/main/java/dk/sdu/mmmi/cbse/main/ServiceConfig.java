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
    public GameManager gameManager(){
        return new GameManager(serviceLocator());
    }
    @Bean
    public ServiceLocator serviceLocator(){
        return new ServiceLocator();
    }

}
