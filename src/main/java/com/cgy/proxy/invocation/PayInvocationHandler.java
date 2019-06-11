package com.cgy.proxy.invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PayInvocationHandler implements InvocationHandler {
    //要被代理的对象
    private Object target;

    //构造方法传入被代理对象
    public PayInvocationHandler(Object target) {
        this.target = target;
    }

    /*
      Proxy.newProxyInstance 方法在构造代理对象的时候会将此类的对象（this）传入，
      并调用此对象的invoke方法 ，从而调用before()和aftr()增强方法
      JDK 底层利用字节码重组实现代理对象的生成
      byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
                proxyName, interfaces);
      具体请参考jdk 源码，此处不再深究
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        before();  //前置增强调用
        Object result = method.invoke(target, args);  //利用反射调用主业务方法
        after();  //后置增强调用
        return result;
    }

    private void before() {
        System.out.println("前置增强业务逻辑");
    }

    private void after() {
        System.out.println("后置增强后置增强业务逻辑");
    }
}
