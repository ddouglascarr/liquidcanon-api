package org.ddouglascarr.config;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProjectUtilsImpl implements ProjectUtils
{
    @Override
    public UUID generateUniqueId()
    {
        return UUID.randomUUID();
    }
}
