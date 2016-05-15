package org.ddouglascarr.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class HandleServiceErrorsAspect
{

    @Around("@annotation(HandleServiceErrors)")
    public ResponseEntity<Object> handleUnprivilegedExecption(ProceedingJoinPoint joinPoint)
    {
        try {
            return (ResponseEntity<Object>) joinPoint.proceed();
        } catch (ItemNotFoundException e) {
            return new ResponseEntity<>(e.getResponseHeaders(), HttpStatus.NOT_FOUND);
        } catch (MemberUnprivilegedException e) {
            return new ResponseEntity<>(e.getResponseHeaders(), HttpStatus.UNAUTHORIZED);
        } catch (Throwable e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
