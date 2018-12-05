package cn.su.study.spring;

import cn.su.study.pojo.Person;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author suyulong
 * @date 2018/11/21 3:12 PM
 */
public class PersonTest {

    @Test
    public void testMakeFriends(){
        Person person = new Person();
        person.makeFriends();
        System.out.println(person);
    }

    @Test
    public void testMakeFriendsPrivate(){
        Person person = new Person();
        Class clazz = person.getClass();
        try {
            Method makeFriendsPrivate = clazz.getDeclaredMethod("makeFriendsPrivate");
            makeFriendsPrivate.setAccessible(true);
            makeFriendsPrivate.invoke(person);
            makeFriendsPrivate.setAccessible(false);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
