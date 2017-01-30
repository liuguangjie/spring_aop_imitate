package aop.imitate.adapter;

import aop.imitate.MethodCallBackBefore;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by free on 17-1-29.
 */
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {
    private final MethodCallBackBefore advice;

    public MethodBeforeAdviceInterceptor(MethodCallBackBefore advice) {
        this.advice = advice;
    }

    public Object invoke(MethodInvocation mi) throws Throwable {
        this.advice.before(mi.getMethod(), mi.getArguments(), mi.getThis());
        return mi.proceed();
    }
}
