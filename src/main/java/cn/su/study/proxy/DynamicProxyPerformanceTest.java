//package cn.su.study.proxy;
//
//import javassist.*;
//import javassist.util.proxy.MethodHandler;
//import javassist.util.proxy.ProxyFactory;
//import javassist.util.proxy.ProxyObject;
//import org.springframework.cglib.proxy.Enhancer;
//import org.springframework.cglib.proxy.MethodInterceptor;
//import org.springframework.cglib.proxy.MethodProxy;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.lang.reflect.Proxy;
//import java.text.DecimalFormat;
//
///**
// * @author suyulong
// * @date 2018/5/22 16:01
// */
//public class DynamicProxyPerformanceTest {
//
//    /**
//     *
//     * Create JDK Proxy: 7 ms
//     * Create CGLIB Proxy: 100 ms
//     * Create JAVAASSIST Proxy: 88 ms
//     * Create JAVAASSIST Bytecode Proxy: 59 ms
//     * ================
//     * Run JDK Proxy: 44 ms, 32,046,941 t/s
//     * Run CGLIB Proxy: 169 ms, 8,343,582 t/s
//     * Run JAVAASSIST Proxy: 211 ms, 6,682,774 t/s
//     * Run JAVAASSIST Bytecode Proxy: 52 ms, 27,116,642 t/s
//     *
//     * @param args
//     * @throws Exception
//     */
//
//    public static void main(String[] args) throws Exception {
//        CountService delegate = new CountServiceImpl();
//
//        long time = System.currentTimeMillis();
//        CountService jdkProxy = createJdkDynamicProxy(delegate);
//        time = System.currentTimeMillis() - time;
//        System.out.println("Create JDK Proxy: " + time + " ms");
//
//        time = System.currentTimeMillis();
//        CountService cglibProxy = createCglibDynamicProxy(delegate);
//        time = System.currentTimeMillis() - time;
//        System.out.println("Create CGLIB Proxy: " + time + " ms");
//
//        time = System.currentTimeMillis();
//        CountService javassistProxy = createJavaAssistDynamicProxy(delegate);
//        time = System.currentTimeMillis() - time;
//        System.out.println("Create JAVAASSIST Proxy: " + time + " ms");
//
//        time = System.currentTimeMillis();
//        CountService javassistBytecodeProxy = createJavaAssistByteCodeDynamicProxy(delegate);
//        time = System.currentTimeMillis() - time;
//        System.out.println("Create JAVAASSIST Bytecode Proxy: " + time + " ms");
//
//        System.out.println("================");
//
//        for (int i = 0; i < 3; i++) {
//            WorkMain(jdkProxy, "Run JDK Proxy: ");
//            WorkMain(cglibProxy, "Run CGLIB Proxy: ");
//            WorkMain(javassistProxy, "Run JAVAASSIST Proxy: ");
//            WorkMain(javassistBytecodeProxy, "Run JAVAASSIST Bytecode Proxy: ");
//            System.out.println("----------------");
//        }
//
//    }
//
//    private static void WorkMain(CountService service, String label)
//        throws Exception {
//        service.count(); // warm up
//        int count = 10000000;
//        long time = System.currentTimeMillis();
//        for (int i = 0; i < count; i++) {
//            service.count();
//        }
//        time = System.currentTimeMillis() - time;
//        System.out.println(label + time + " ms, " + new DecimalFormat().format(count * 1000 / time) + " t/s");
//    }
//
//
//    /**
//     * jdk dynamic proxy
//     * <p>
//     * JDK的动态代理是靠反射完成的 一定要实现接口
//     *
//     * @param delegate
//     * @return
//     */
//    private static CountService createJdkDynamicProxy(final CountService delegate) {
//        CountService countService_jdk = (CountService)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
//            new Class[] {CountService.class},
//            new JdkHandler(delegate));
//        return countService_jdk;
//    }
//
//    private static class JdkHandler implements InvocationHandler {
//
//        final Object delegate;
//
//        private JdkHandler(Object delegate) {this.delegate = delegate;}
//
//        @Override
//        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//            //long time = System.currentTimeMillis();
//            Object o = method.invoke(delegate, args);
//            //time = System.currentTimeMillis() - time;
//            //System.out.println("Create JDK Proxy: " + time + " ms");
//            return o;
//        }
//    }
//
//    /*****************************/
//
//    /**
//     * cglib dynamic proxy
//     * <p>
//     * cglib原理是让目标类生成一个子类，然后让子类去进行方法的增强
//     * <p>
//     * final Class不支持, 因为CGLIB是生成子类来实现AOP,所以final Class自然无法支持了. 需要强制无参数构造函数
//     *
//     * @param delegate
//     * @return
//     */
//    private static CountService createCglibDynamicProxy(final CountService delegate) {
//        Enhancer enhancer = new Enhancer();
//        enhancer.setCallback(new CglibInterceptor(delegate));
//        enhancer.setInterfaces(new Class[] {CountService.class});
//        CountService cglibProxy = (CountService)enhancer.create();
//        return cglibProxy;
//    }
//
//    /**
//     * MethodInterceptor接口只定义了一个方法：
//     * <p>
//     * public Object intercept(Object object, java.lang.reflect.Method method, Object[] args, MethodProxy proxy) throws
//     * Throwable;
//     * <p>
//     * 参数Object object是被代理对象，不会出现死循环的问题。
//     * <p>
//     * 参数java.lang.reflect.Method method是java.lang.reflect.Method类型的被拦截方法。
//     * <p>
//     * 参数Object[] args是被被拦截方法的参数。
//     * <p>
//     * 参数MethodProxy proxy是CGLIB提供的MethodProxy 类型的被拦截方法。
//     * <p>
//     * 注意：
//     * <p>
//     * 1、若原方法的参数存在基本类型，则对于第三个参数Object[] args会被转化成类的类型。如原方法的存在一个参数为int，则在intercept方法中，对应的会存在一个Integer类型的参数。
//     * <p>
//     * 2、若原方法为final方法，则MethodInterceptor接口无法拦截该方法
//     */
//    private static class CglibInterceptor implements MethodInterceptor {
//        final Object delegate;
//
//        private CglibInterceptor(Object delegate) {this.delegate = delegate;}
//
//        @Override
//        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//            //long time = System.currentTimeMillis();
//            Object obj = method.invoke(delegate, objects);
//            //time = System.currentTimeMillis() - time;
//            //System.out.println("Create CGLib Proxy: " + time + " ms");
//            return obj;
//        }
//    }
//
//    /****************************/
//
//    /**
//     * JavaAssist 类似jdk接口实现 动态代理
//     */
//
//    private static class JavaAssistInterceptor implements MethodHandler {
//        final Object delegate;
//
//        private JavaAssistInterceptor(Object delegate) {this.delegate = delegate;}
//
//        @Override
//        public Object invoke(Object o, Method method, Method method1, Object[] objects) throws Throwable {
//            //long time = System.currentTimeMillis();
//            Object obj = method.invoke(delegate, objects);
//            //time = System.currentTimeMillis() - time;
//            //System.out.println("Create JAVAASSIST Proxy: " + time + " ms");
//            return obj;
//        }
//    }
//
//    private static CountService createJavaAssistDynamicProxy(final CountService delegate)
//        throws IllegalAccessException, InstantiationException {
//        ProxyFactory proxyFactory = new ProxyFactory();
//        proxyFactory.setInterfaces(new Class[] {CountService.class});
//        Class<?> proxyClass = proxyFactory.createClass();
//        CountService javaassistProxy = (CountService)proxyClass.newInstance();
//        ((ProxyObject)javaassistProxy).setHandler(new JavaAssistInterceptor(delegate));
//        return javaassistProxy;
//    }
//
//    /****************************/
//
//    /**
//     * JavaAssist byte code
//     */
//    private static CountService createJavaAssistByteCodeDynamicProxy(CountService delegate) throws Exception {
//        ClassPool pool = new ClassPool(true);
//        CtClass ctc = pool.makeClass(CountService.class.getName() + "JavaAssistProxy");
//        ctc.addInterface(pool.get(CountService.class.getName()));
//        ctc.addConstructor(CtNewConstructor.defaultConstructor(ctc));
//        ctc.addField(CtField.make("public " + CountService.class.getName() + " delegate;", ctc));
//        ctc.addMethod(CtNewMethod.make(
//            "public int count() {return delegate.count();}",
//            ctc));
//        Class<?> proxyClass = ctc.toClass();
//        CountService bytecodeProxy = (CountService)proxyClass.newInstance();
//        Field field = bytecodeProxy.getClass().getField("delegate");
//        field.set(bytecodeProxy, delegate);
//        return bytecodeProxy;
//    }
//
//}
