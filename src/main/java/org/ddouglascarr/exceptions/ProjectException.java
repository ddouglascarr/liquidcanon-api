package org.ddouglascarr.exceptions;

import org.ddouglascarr.enums.ExceptionCodes;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

public abstract class ProjectException extends Exception
{

    public abstract ExceptionCodes getCode();

    public MultiValueMap<String, String> getResponseHeaders()
    {
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("error-code", getCode().toString());
        return headers;
    }

}
