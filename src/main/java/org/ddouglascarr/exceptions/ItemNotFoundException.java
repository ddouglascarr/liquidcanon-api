package org.ddouglascarr.exceptions;

import org.ddouglascarr.enums.ExceptionCodes;

public class ItemNotFoundException
        extends Exception
{
    private static ExceptionCodes code = ExceptionCodes.ITEM_NOT_FOUND;

    public ExceptionCodes getCode()
    {
        return code;
    }
}
