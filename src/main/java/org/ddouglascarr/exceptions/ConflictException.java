package org.ddouglascarr.exceptions;

import org.ddouglascarr.enums.ExceptionCodes;

public class ConflictException extends ProjectException
{
    @Override
    public ExceptionCodes getCode()
    {
        return ExceptionCodes.CONFLICT;
    }
}
