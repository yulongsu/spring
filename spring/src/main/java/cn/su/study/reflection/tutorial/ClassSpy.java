package cn.su.study.reflection.tutorial;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

import static java.lang.System.out;

/**
 * @author hiro.syl
 * @date 2018/01/09
 */
public class ClassSpy {
    /**
     * java cn.su.study.reflection.tutorial.ClassSpy java.lang.ClassCastException CONSTRUCTOR
     *
     * java cn.su.study.reflection.tutorial.ClassSpy cn.su.study.reflection.tutorial.ClassMember FIELD METHOD
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            Class<?> c = Class.forName(args[0]);
            out.format("Class:%n  %s%n%n", c.getCanonicalName());

            Package p = c.getPackage();
            out.format("Package:%n  %s%n%n",
                (p != null ? p.getName() : "-- No Package --"));

            for (int i = 1; i < args.length; i++) {
                switch (ClassMember.valueOf(args[i])) {
                    case CONSTRUCTOR:
                        printMembers(c.getConstructors(), "Constructor");
                        break;
                    case FIELD:
                        printMembers(c.getFields(), "Field");
                        break;
                    case METHOD:
                        printMembers(c.getMethods(), "Methods");
                        break;
                    case CLASS:
                        printClasses(c);
                        break;
                    case ALL:
                        printMembers(c.getConstructors(), "Constuctors");
                        printMembers(c.getFields(), "Fields");
                        printMembers(c.getMethods(), "Methods");
                        printClasses(c);
                        break;
                    default:
                        out.format("Wrong Args! %n");

                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void printMembers(Member[] mbrs, String s) {
        out.format("%s:%n", s);
        for (Member mbr : mbrs) {
            if (mbr instanceof Field) {
                Field f = (Field)mbr;
                out.format("  %s%n", f.toGenericString());
                out.format("  -- declared in: %s%n", f.getDeclaringClass());
            } else if (mbr instanceof Constructor) {
                out.format("  %s%n", ((Constructor)mbr).toGenericString());
            } else if (mbr instanceof Method) { out.format("  %s%n", ((Method)mbr).toGenericString()); }
        }
        if (mbrs.length == 0) { out.format("  -- No %s --%n", s); }
        out.format("%n");
    }

    private static void printClasses(Class<?> c) {
        out.format("Classes:%n");
        Class<?>[] clss = c.getClasses();
        for (Class<?> cls : clss) { out.format(" %s%n", cls.getCanonicalName()); }
        if (clss.length == 0) {
            out.format("  -- No member interfaces, classes, or enums --%n");
        }
        out.format("%n");
    }
}

enum ClassMember {
    CONSTRUCTOR,
    FIELD,
    METHOD,
    CLASS,
    ALL
}

