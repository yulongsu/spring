package cn.su.study.reflection.tutorial;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * @author hiro.syl
 * @date 2018/01/08
 */
public class ClassDeclarationSpy {
    /**
     * D:\my.project\spring\spring\target\classes>java cn.su.study.reflection.tutorial.ClassDeclarationSpy java.security.Identity
     Class
     java.security.Identity

     Modifiers:
     public abstract

     Type Parameter :
     -- No Type Parameters --

     Implemented Interfaces:
     interface java.security.Principal
     interface java.io.Serializable

     Inheritance Path:
     java.lang.Object

     Annotations:
     @java.lang.Deprecated()

     */
    public static void main(String[] args){
        try {
            Class<?> c = Class.forName(args[0]);
            out.format("Class %n %s%n%n",c.getCanonicalName());
            out.format("Modifiers:%n %s%n%n", Modifier.toString(c.getModifiers()));
            out.format("Type Parameter : %n");
            TypeVariable[] tv = c.getTypeParameters();
            if(tv.length != 0){
                out.format(" ");
                for(TypeVariable t : tv)
                    out.format("%s ",t.getName());
                out.format("%n%n");
            } else {
                out.format("  -- No Type Parameters --%n%n");
            }

            out.format("Implemented Interfaces:%n");
            Type[] intfs = c.getGenericInterfaces();
            if (intfs.length != 0) {
                for (Type intf : intfs)
                    out.format("  %s%n", intf.toString());
                out.format("%n");
            } else {
                out.format("  -- No Implemented Interfaces --%n%n");
            }

            out.format("Inheritance Path:%n");
            List<Class> l = new ArrayList<Class>();
            printAncestor(c,l);
            if (l.size() != 0) {
                for (Class<?> cl : l)
                    out.format("  %s%n", cl.getCanonicalName());
                out.format("%n");
            } else {
                out.format("  -- No Super Classes --%n%n");
            }

            out.format("Annotations:%n");
            Annotation[] ann = c.getAnnotations();
            if (ann.length != 0) {
                for (Annotation a : ann)
                    out.format("  %s%n", a.toString());
                out.format("%n");
            } else {
                out.format("  -- No Annotations --%n%n");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printAncestor(Class<?> c,List<Class> l){
        Class<?> ancestor = c.getSuperclass();
        if(ancestor != null){
            l.add(ancestor);
            printAncestor(ancestor,l);
        }
    }
}
