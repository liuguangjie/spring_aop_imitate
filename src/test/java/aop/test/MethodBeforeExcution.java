package aop.test;

import aop.imitate.MethodCallBackBefore;

import java.lang.reflect.Method;

/**
 * Created by free on 17-1-29.
 */
public class MethodBeforeExcution implements MethodCallBackBefore {
    public void before(Method method, Object[] args, Object target) {
        System.out.println("在目标方法之前执行 >>>> 目标方法名字: [ "+method.getName()+"() ]  目标类名字: [ "+target.getClass().getName()+" ]");
    }
}
