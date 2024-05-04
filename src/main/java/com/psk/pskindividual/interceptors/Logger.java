package com.psk.pskindividual.interceptors;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.io.Serializable;

@Interceptor
@Log
public class Logger implements Serializable {
    @AroundInvoke
    public Object logMethod(InvocationContext context) throws Exception {
        System.out.println("Logger logged action: " + context.getMethod().getName());
        return context.proceed();
    }
}
