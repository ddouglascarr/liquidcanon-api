package org.ddouglascarr.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FooAspect
{
    @Pointcut("@annotation(Foo)")
    public void fooPointcut() {}

    @Before("fooPointcut()")
    public void logFoobar()
    {
        System.out.println("Foobar");
    }

}
