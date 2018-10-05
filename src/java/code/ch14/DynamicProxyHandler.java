package code.ch14;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author yuzhe
 * @since 9/3/18
 */
public class DynamicProxyHandler implements InvocationHandler {

    private Object proxied;
    public static int doSthCount;
    public static int doSthElseCount;


    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
        this.doSthCount = 0;
        this.doSthElseCount = 0;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        StringBuilder sb = new StringBuilder();
        sb.append("**** proxy:")
                .append(proxy.getClass())
                .append(", method: ")
                .append(method.getName());
        if (args != null) {
            sb.append(", args:");
            for (Object arg : args) {
                sb.append(arg).append(",");
            }
        }
        System.out.println(sb.toString());
        if(method.getName().equals("doSomething")){
            doSthCount++;
        }
        if(method.getName().equals("somethingElse")){
            doSthElseCount++;
        }
        return method.invoke(proxied, args);
    }
}

class SimpleDynamicProxy {

    public static void consumer(Interface i){
        i.doSomething();
        i.somethingElse("whatever");
    }


    public static void main(String[] args) {
        RealObject realObject = new RealObject();
        consumer(realObject);
        System.out.println("------------proxy------------");
        DynamicProxyHandler proxyHandler = new DynamicProxyHandler(realObject);
        Interface proxy = (Interface) Proxy.newProxyInstance(
                Interface.class.getClassLoader(),
                new Class[]{Interface.class},
                proxyHandler
        );
        consumer(proxy);
        System.out.println("doSth:" + DynamicProxyHandler.doSthCount);
        System.out.println("doSthElse:" + DynamicProxyHandler.doSthElseCount);
    }
}


interface Interface {
    void doSomething();

    void somethingElse(String arg);
}

class RealObject implements Interface {

    @Override
    public void doSomething() {
        System.out.println("do something");
    }

    @Override
    public void somethingElse(String arg) {
        System.out.println("do something else: " + arg);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
