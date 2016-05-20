package org.ddouglascarr.config;

import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.contextsupport.spring.AnnotationDriven;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.fs.FileSystemEventStore;
import org.axonframework.eventstore.fs.SimpleEventFileResolver;
import org.ddouglascarr.entities.UnitEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
@AnnotationDriven
public class AxomConfig
{

    @Bean
    public SimpleCommandBus commandBus() {
        SimpleCommandBus simpleCommandBus = new SimpleCommandBus();
        return simpleCommandBus;
    }

    @Bean
    public DefaultCommandGateway commandGateway() {
        return new DefaultCommandGateway(commandBus());
    }

    @Bean
    public EventBus eventBus()
    {
        return new SimpleEventBus();
    }

    @Bean
    public EventStore eventStore()
    {
        EventStore eventStore =
                new FileSystemEventStore(new SimpleEventFileResolver(new File("./events")));
        return eventStore;
    }

    @Bean
    public EventSourcingRepository eventSourcingRepository()
    {
        EventSourcingRepository eventSourcingRepository = new EventSourcingRepository(
                UnitEntity.class, eventStore());
        eventSourcingRepository.setEventBus(eventBus());
        return eventSourcingRepository;
    }
}
