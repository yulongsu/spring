package cn.su.study.btrace;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.println;
import static com.sun.btrace.BTraceUtils.strcat;

/**
 * @author suyulong
 * @date 2019/3/14 7:16 PM
 */
@BTrace
public class BtraceDemo {

    @OnMethod(
        clazz = "cn.su.daily.Main",
        method = "stripNonCharCodepoints",
        location = @Location(Kind.RETURN)
    )
    public static void trace(@Return String output){
        println(strcat("result:",output));
    }
}
