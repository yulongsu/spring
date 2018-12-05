package cn.su.study;

import cn.su.exam.BizException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.DefaultParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author suyulong
 * @date 2018/5/14 16:04
 */
public class PublicTest {
    public static void main(String[] args) throws InterruptedException {


        Integer integer = 42;
        System.out.println(integer);


        try {
            //判断instanceof 类型
            Object map = new HashMap<String,String>(0);
            if (map instanceof Map) {
                System.out.println(((HashMap)map).size());
            }

            //throw new RuntimeException("123");
            //throw new BizException("123", "");
        } catch (BizException e) {
            //throw new RuntimeException("ABC");
        }
        //System.out.println("Oops, cannot get logQueryUrl, please use this command 'WAIT instanceId;' in the console
        // to see additional details!");
        //List<String> list = new ArrayList<String>();
        //list.add("a");
        //list.add("b");
        //list.add("c");
        //String str = StringUtils.join(list.toArray(), ",");
        //System.out.println(str); ////a,b,c
        //
        //
        //String str1 = "a,b,c";
        //List<String> result = Arrays.asList(StringUtils.split(str1,","));
        //System.out.println(result);

        //System.out.println(excludeRuleId("1,2","1"));
        //
        //System.out.println(excludeRuleId("1","1"));
        //
        //System.out.println(excludeRuleId("1,2,2,2","2"));


        /*Thread t = new Thread(()-> System.out.println(1/0));
        t.start();
        t.join();
*/
        //java中整数默认int，小数默认double
        //fload f = 3.4 //就是错误的，类型不匹配
        //float f = 3.4f;
        //
        //String a = new String("asdf");
        //String b = new String("asdf");
        //String f = "asdf";
        //System.out.println(b == a);
        //System.out.println(f == a);
        //a = a.intern();
        //System.out.println(b == a);
        //System.out.println(f == a);
        //b = b.intern();
        //System.out.println(b == a);

        //System.out.println(1 & 2);

        //System.out.println(2 & 2);

        /*for (; ; ) {

            Random random = new Random();
            int result;
            result = random.nextInt(2);
            //result = (result < 1) ? 1 : result;
            System.out.println(result);
        }*/

        //PublicTest publicTest = new PublicTest();
        ////publicTest.printMethodInClass();
        //
        //String tableVO = null, name = null;
        //try {
        //    publicTest.getParamName(tableVO, name);
        //} catch (NoSuchMethodException e) {
        //    e.printStackTrace();
        //}


        /*List<String> a = new ArrayList<>(Arrays.asList("1","2","3"));
        List<String> b = new ArrayList<>();
        b.addAll(a);
        //Iterator<String> iterator = a.iterator();
        //while (iterator.hasNext()){
        //    iterator.next();
        //    iterator.remove();
        //}
        System.out.println(a);
        System.out.println(b);

        Optional<String> any = b.stream().filter(x -> !a.contains(x)).findFirst();
        if(any.isPresent()){
            System.out.println("haha");
        }*/
    }

    private static List<String> excludeRuleId(String origRules, String excludeId) {
        return Arrays.asList(StringUtils.split(origRules, ",")).stream().filter(
            id -> !id.equals(excludeId)).collect(Collectors.toList());
    }

    /**
     * 方法名：getParamName 参数类型：[Ljava.lang.String;
     * <p>
     * 虽然封装成了数组类型，但反射不支持可变参数的执行
     *
     * @param args
     * @throws NoSuchMethodException
     */
    public void getParamName(String... args) throws NoSuchMethodException {
        DefaultParameterNameDiscoverer discover = new DefaultParameterNameDiscoverer();
        String[] parameterNames = discover.getParameterNames(this.getClass()
            .getMethod("getParamName", String[].class));
        for (String param : parameterNames) {
            //只能打印成：args
            System.out.println(param);
        }
    }

    public void printMethodInClass() {
        Class<?> clazz = this.getClass();
        //获取本类的所有方法，存放入数组
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("方法名：" + method.getName());
            //获取本方法所有参数类型，存入数组
            Class<?>[] getTypeParameters = method.getParameterTypes();
            if (getTypeParameters.length == 0) {
                System.out.println("此方法无参数");
            }
            for (Class<?> class1 : getTypeParameters) {
                String parameterName = class1.getName();
                System.out.println("参数类型：" + parameterName);
            }
            System.out.println("****************************");
        }

    }

}
