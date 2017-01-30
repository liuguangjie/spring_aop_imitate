package aop.imitate.cglib;

import aop.imitate.ReflectiveMethodInvocation;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;


import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by free on 17-1-29.
 */
public class CglibBeforeProxy {

    private final Object target;

    private final org.aopalliance.intercept.MethodInterceptor interceptor;

    public CglibBeforeProxy(Object target, org.aopalliance.intercept.MethodInterceptor interceptor) {
        this.target = target;
        this.interceptor = interceptor;
    }


    public org.aopalliance.intercept.MethodInterceptor getInterceptor() {
        return interceptor;
    }

    public Object getProxy() {
        Enhancer enhancer = createEnhancer();

        enhancer.setSuperclass(getTarget().getClass());
        enhancer.setCallbacks(new Callback[]{new DynamicAdvisedInterceptor()});

        return enhancer.create();
    }



    public Object getTarget() {
        return target;
    }

    public Enhancer createEnhancer() {
        return new Enhancer();
    }

    private  class DynamicAdvisedInterceptor implements MethodInterceptor {

        public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            Object interceptor=new CglibMethodInvocation(proxy,getTarget(),method,args,getTarget().getClass(),getInterceptor(),methodProxy).proceed();
            return interceptor;
        }
    }


    private  class CglibMethodInvocation extends ReflectiveMethodInvocation {

        private final MethodProxy methodProxy;

        private boolean protectedMethod;



        public CglibMethodInvocation(Object proxy, Object target, Method method, Object[] arguments,
                                     Class targetClass, org.aopalliance.intercept.MethodInterceptor interceptor, MethodProxy methodProxy) {
            super(proxy, target, method, arguments, targetClass, interceptor);
            this.methodProxy = methodProxy;
            this.protectedMethod = Modifier.isProtected(method.getModifiers());
        }


        /**
         *  public ReflectiveMethodInvocation(Object proxy, Object target,
         Method method,Object[] arguments,
         Class targetClass, MethodInterceptor interceptor) {
         */

        @Override
        protected Object invokeJoinpoint() throws Throwable {
            return this.methodProxy.invoke(this.target, this.arguments);
        }
    }


}
