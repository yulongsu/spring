package cn.su.study.annotation;

import java.lang.annotation.*;

@Documented
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNull
{
    public String value() default "";
}
