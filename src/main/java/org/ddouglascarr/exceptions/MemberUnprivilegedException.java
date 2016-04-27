package org.ddouglascarr.exceptions;

import org.ddouglascarr.enums.ExceptionCodes;

public class MemberUnprivilegedException extends ProjectException
{
    @Override
    public ExceptionCodes getCode()
    {
        return ExceptionCodes.UNPRIVILEGED;
    }
}
