package cn.su.study.reflection;

import cn.su.study.pojo.Person;

public class App {

	public static void main(String[] args) {
		Person onePerson = new Person();
		onePerson.makeFriends();
		System.out.println(onePerson.toString());
	}

}
