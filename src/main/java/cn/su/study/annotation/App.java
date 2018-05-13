package cn.su.study.annotation;

public class App {
    public static void main(String args[]){
        Order order = new Order();
        if(Order.class.isAnnotationPresent(NotNull.class)){
            NotNull notNull = (NotNull)Order.class.getAnnotation(NotNull.class);
            System.out.println(notNull.value());
        }
    }
}
