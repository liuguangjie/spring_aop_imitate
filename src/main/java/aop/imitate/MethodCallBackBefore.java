package aop.imitate;

import org.aopalliance.aop.Advice;

import java.lang.reflect.Method;

/**
 * Created by free on 17-1-29.
 */
public interface MethodCallBackBefore extends Advice {

    /**
     *
     * @param method
     * @param args
     * @param target
     */
    public void before(Method method,Object[] args,Object target);

}
