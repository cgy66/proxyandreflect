package com.cgy.proxy;

import com.cgy.proxy.invocation.PayInvocationHandler;
import com.cgy.proxy.service.PayService;
import com.cgy.proxy.service.impl.PayServiceImpl;
import sun.misc.ProxyGenerator;

import java.io.*;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) throws Exception{
        PayService target = new PayServiceImpl();

        PayInvocationHandler handler = new PayInvocationHandler(target);
        PayService proxyService = (PayService) Proxy.newProxyInstance(PayService.class.getClassLoader(), target.getClass().getInterfaces(), handler);
        System.out.println(proxyService.getClass().getName());
        proxyService.pay();
        //从jvm中获取代理对象com.sun.proxy.$Proxy0 的字节码文件
      byte[] bs =  ProxyGenerator.generateProxyClass("com.sun.proxy.$Proxy0",new Class[]{PayService.class});
      writeBytesToFile(bs);
    }

    public static void writeBytesToFile(byte[] bs) throws IOException {

        OutputStream out = new FileOutputStream("D:\\aaa.class");
        InputStream is = new ByteArrayInputStream(bs);
        byte[] buff = new byte[1024];
        int len = 0;
        while((len=is.read(buff))!=-1){
            out.write(buff, 0, len);
        }
        is.close();
        out.close();
    }
}
