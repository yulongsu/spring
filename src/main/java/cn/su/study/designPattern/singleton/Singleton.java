package cn.su.study.designPattern.singleton;

/**
 * 单例模式  懒汉模式
 *
 * @author suyulong
 * @date 2018/6/6 00:47
 */
public class Singleton {
    private Singleton(){};

    private static Singleton instance = null;

    public static Singleton getInstance(){
        if(instance == null){
            synchronized (Singleton.class){
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

/**
 * 饿汉模式
 *
 * 在类初始化时，已经自行实例化
 */
class Singleton1{
    private Singleton1(){};

    private static final Singleton1 instance = new Singleton1();

    private static Singleton1 getInstance(){
        return instance;
    }
}

/**
 * 静态内部类
 *
 */
class Singleton2{
    private static class lazyHolder{
        private static Singleton2 INSTANCE = new Singleton2();
    }

    private Singleton2(){};

    private static Singleton2 getInstance(){
        /**
         * 对于外部类来说 内部类相当于它的一个属性
         * 内部类中的private也相当于它本身的private属性
         * 所以根据类内可见原则 内部类private是可以被外部类访问的
         */
        return lazyHolder.INSTANCE;
    }

}

/**
 * 单元素的枚举类型已经成为实现Singleton的最佳方法。
 *
 * 首先，在枚举中我们明确了构造方法限制为私有，在我们访问枚举实例时会执行构造方法，
 * 同时每个枚举实例都是static final类型的，也就表明只能被实例化一次。在调用构造方法时，我们的单例被实例化。
 * 也就是说，因为enum中的实例被保证只会被实例化一次，所以我们的INSTANCE也被保证实例化一次。
 * 可以看到，枚举实现单例还是比较简单的，除此之外我们再来看一下Enum这个类的声明：
 *
 * public abstract class Enum<E extends Enum<E>>
 *         implements Comparable<E>, Serializable
 *
 * 可以看到，枚举也提供了序列化机制。某些情况，比如我们要通过网络传输一个数据库连接的句柄，会提供很多帮助。
 *
 */
class Singleton3{}

enum Singleton3Enum {
    INSTANCE;

    private Singleton3 instance;

    /**
     * 默认就是private的，而且只会调用1次
     */
    Singleton3Enum(){
        instance = new Singleton3();
    }

    public Singleton3 getInstance(){
        return instance;
    }
}
