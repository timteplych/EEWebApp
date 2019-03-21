package ru.ttv.eewebapp.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * @author Teplykh Timofey  16.03.2019
 */
public class UserActionLogger {

    @AroundInvoke
    public Object printLog(InvocationContext ctx) throws Exception{
        System.out.println("Invoked method "+ ctx.getMethod().getName());
        return ctx.proceed();
    }
}
