package aop.imitate;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * Created by free on 17-1-29.
 */
public class ReflectiveMethodInvocation implements MethodInvocation {

    protected final Object proxy;

    protected final Object target;

    protected final Method method;

    protected Object[] arguments;

    private final Class targetClass;

    private final MethodInterceptor interceptor;


    public ReflectiveMethodInvocation(Object proxy, Object target,
                                      Method method,Object[] arguments,
                                      Class targetClass, MethodInterceptor interceptor) {
        this.proxy = proxy;
        this.target = target;
        this.method = method;
        this.arguments=arguments;
        this.targetClass = targetClass;
        this.interceptor=interceptor;
    }


    public Method getMethod() {
        return  this.method;
    }

    public Object[] getArguments() {
        return (this.arguments != null ? this.arguments : new Object[0]);
    }
    private boolean flag=true;
    public Object proceed() throws Throwable {

        if(flag){
            flag=false;
            return this.interceptor.invoke(this);
        }else {
            return method.invoke(this.target,this.arguments);
            // return invokeJoinpoint();
        }

    }

    protected Object invokeJoinpoint() throws Throwable {
        return null;
    }

    public Object getThis() {
        return this.target;
    }

    public AccessibleObject getStaticPart() {
        return this.method;
    }
}
