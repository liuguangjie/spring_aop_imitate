package aop.test;

import aop.imitate.adapter.MethodBeforeAdviceInterceptor;
import aop.imitate.cglib.CglibBeforeProxy;
import org.junit.Test;

/**
 * Created by free on 17-1-29.
 */
public class AopBeforeTest {

    @Test
    public void testBefore(){
        //通知
        MethodBeforeExcution methodBeforeExcution=new MethodBeforeExcution();
        //目标
        Hello hello=new Hello();
        //
        MethodBeforeAdviceInterceptor interceptor=new MethodBeforeAdviceInterceptor(methodBeforeExcution);
        CglibBeforeProxy cglibBeforeProxy=new CglibBeforeProxy(hello,interceptor);

        Hello h2= (Hello) cglibBeforeProxy.getProxy();
        h2.sayBefore();
    }
}
