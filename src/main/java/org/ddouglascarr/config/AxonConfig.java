package org.ddouglascarr.config;

import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.contextsupport.spring.AnnotationDriven;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventhandling.annotation.AnnotationEventListenerBeanPostProcessor;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.fs.FileSystemEventStore;
import org.axonframework.eventstore.fs.SimpleEventFileResolver;
import org.ddouglascarr.command.member.MemberAggregate;
import org.ddouglascarr.command.unit.UnitAggregate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
@AnnotationDriven
public class AxonConfig
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
                new FileSystemEventStore(new SimpleEventFileResolver(new File("./target/events")));
        return eventStore;
    }

    @Bean
    public EventSourcingRepository<UnitAggregate> eventSourcingRepository()
    {
        EventSourcingRepository eventSourcingRepository = new EventSourcingRepository(
                UnitAggregate.class, eventStore());
        eventSourcingRepository.setEventBus(eventBus());
        return eventSourcingRepository;
    }

    @Bean
    public EventSourcingRepository<MemberAggregate> getMemberSourcingRepository()
    {
        EventSourcingRepository repository = new EventSourcingRepository(
                MemberAggregate.class, eventStore());
        repository.setEventBus(eventBus());
        return repository;
    }

    @Bean
    AnnotationEventListenerBeanPostProcessor annotationEventListenerBeanPostProcessor()
    {
        AnnotationEventListenerBeanPostProcessor listener =
                new AnnotationEventListenerBeanPostProcessor();
        listener.setEventBus(eventBus());
        return listener;
    }
}
