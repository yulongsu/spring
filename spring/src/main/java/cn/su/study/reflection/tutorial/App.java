package cn.su.study.reflection.tutorial;

import java.lang.reflect.Field;

import static cn.su.study.reflection.tutorial.Sex.Man;

/**
 * @author hiro.syl
 * @date 2017/12/28
 */
public class App {
    //匿名内部类
    static Object o = new Object(){
      public void m(){}
    };

    static Class c = o.getClass().getEnclosingClass();

    public static void main(String[] args){
        /**
         * 在ide里执行，会出现null point
         * 以Javaw所执行的应用程式（eclipse）没有主控制台（console），
         * 所以取不到console物件，System.console()只能是null了。
         */
        // Class c = System.console().getClass();

        //OutPut : class cn.su.study.reflection.tutorial.Sex
        System.out.println(Man.getClass());

        //Output : class [B
        byte[] bytes = new byte[1024];
        System.out.println(bytes.getClass());

        //Output : boolean
        System.out.println(boolean.class);

        //class java.io.PrintStream
        System.out.println(java.io.PrintStream.class);

        //class [[[I
        System.out.println(int[][][].class);

        try {
            //Returns the Class in which these members were declared.
            // Anonymous Class Declarations will not have a declaring class but will have an enclosing class.
            Field f = System.class.getField("out");
            //class java.lang.System
            System.out.println(f.getDeclaringClass());

            //The declaring class of the anonymous class defined by o is null.
            //null
            System.out.println(App.o.getClass().getDeclaringClass());

            //The anonymous class defined by o
            //class cn.su.study.reflection.tutorial.App
            System.out.println(App.c);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}
