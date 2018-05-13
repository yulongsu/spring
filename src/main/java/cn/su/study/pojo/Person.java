package cn.su.study.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Person implements Serializable {

	private static final long serialVersionUID = 5504219157355904951L;

	private String name;
	private Integer age;
	private Sex sex;
	private Double weight;
	private BigDecimal salary;
	private List<Person> friends;

	public Person() {
		this.name = RandomStringUtils.random(5, new char[] { 's', 'y', 'l', '1', '6' });
		this.age = RandomUtils.nextInt(18, 60);
		this.sex = Sex.random();
		//double四舍五入
		this.weight = BigDecimal.valueOf(RandomUtils.nextDouble(60d, 90d)).setScale(2, BigDecimal.ROUND_HALF_DOWN)
				.doubleValue();
		this.salary = BigDecimal.valueOf((age + weight) * 100).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	public void makeFriends() {
		int num = RandomUtils.nextInt(1, 10);
		List<Person> list = new ArrayList<>(num);
		for (int i = 0; i < num; i++) {
			list.add(new Person());
		}
		this.setFriends(list);
	}

	enum Sex {
		MALE("male", "男"), FEMALE("female", "女"),;

		private String gender;
		private String name;

		private Sex(String gender, String name) {
			this.gender = gender;
			this.name = name;
		}

		public String getGender() {
			return gender;
		}

		public String getName() {
			return name;
		}

		public static Sex random() {
			return (RandomUtils.nextInt(1, 16) % 2 == 0) ? MALE : FEMALE;
		}

	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public List<Person> getFriends() {
		return friends;
	}

	public void setFriends(List<Person> friends) {
		this.friends = friends;
	}

}
