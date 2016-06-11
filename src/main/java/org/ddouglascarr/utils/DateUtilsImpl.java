package org.ddouglascarr.utils;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DateUtilsImpl implements DateUtils
{
    @Override
    public Date generateCurrentDate()
    {
        return new Date();
    }
}
