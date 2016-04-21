package org.ddouglascarr.exceptions;

import org.ddouglascarr.enums.Exceptions;

public class ItemNotFoundException
        extends Exception
{
    private static Exceptions code = Exceptions.ITEM_NOT_FOUND;

    public Exceptions getCode()
    {
        return code;
    }
}
