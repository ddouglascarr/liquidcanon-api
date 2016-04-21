package org.ddouglascarr.exceptions;

import org.ddouglascarr.enums.ExceptionCodes;

public class ItemNotFoundException
        extends ProjectException
{

    @Override
    public ExceptionCodes getCode()
    {
        return ExceptionCodes.ITEM_NOT_FOUND;
    }
}
