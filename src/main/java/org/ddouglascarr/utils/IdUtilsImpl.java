package org.ddouglascarr.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IdUtilsImpl implements IdUtils
{
    @Override
    public UUID generateUniqueId()
    {
        return UUID.randomUUID();
    }
}
