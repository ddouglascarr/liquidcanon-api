package org.ddouglascarr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ContextRefreshedEventListener implements ApplicationListener<ApplicationReadyEvent>
{
    @Autowired


    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent)
    {
        System.out.println("applicationReadyEvent");
    }
}
