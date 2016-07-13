package com.chuangyou.common.util;

import java.util.Collection;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chuangyou.common.util.Log;

//JSON对象转换工具类
public class JSONUtil {
	
    /**
     * 把对象转换为JSON字符串
     */
    public static String getJSONString(Object object) {
	String jsonString = null;
	try {
	    if (object != null) {
		if (object instanceof Collection || object instanceof Object[]) {
		    jsonString = JSONArray.toJSONString(object);
		} else {
		    jsonString = JSON.toJSONString(object);
		}
	    }
	} catch (Exception e) {
	    Log.error("json转换异常", e);
	}
	return jsonString == null ? "{}" : jsonString;
    }

    /**
     * 从一个JSON 对象字符格式中得到一个java对象
     * 
     * @param <T>
     */
    public static <T> Object getDTO(String jsonString, Class<T> clazz) {
	T result = null;
	try {
	    result = JSON.parseObject(jsonString, clazz);
	} catch (Exception e) {
	    Log.error("json转换异常,json:" + jsonString + "  class :" + clazz.getName(), e);
	}
	return result;
    }

    public static String getValue(String json, String filed) {
	try {
	    if (json == null) {
		return null;
	    }
	    JSONObject jobj = JSON.parseObject(json);
	    if (jobj.containsKey(filed)) {
		return jobj.getString(filed);
	    }
	    return null;
	} catch (Exception e) {
	    Log.warn("JSON getValue error,json:" + json + " filed" + filed, e);
	    return null;
	}
    }

    public static void main(String[] args) throws Exception {
	Person person = new Person("张三", 18);
	Dog dog = new Dog("张三", "黑背");
	Hourse hourse = new Hourse("深南大道9966号", person, dog);

	// hourse2str
	String hourseStr = getJSONString(hourse);
	System.out.println("Hourse:" + hourseStr);
	// str2hourse
	Hourse newHorse = (Hourse) getDTO(hourseStr, Hourse.class);
	System.out.println(newHorse.getDog().getMaster());

	Person person2 = new Person("李四", 20);
	Dog dog2 = new Dog("李四", "金毛");
	Hourse hourse2 = new Hourse("深南大道9977号", person2, dog2);

	Hourse[] hourArr = new Hourse[2];
	hourArr[0] = hourse;
	hourArr[1] = hourse2;
	// 数组转字符串
	String houseArrStr = getJSONString(hourArr);
	System.out.println("houseArrStr : " + houseArrStr);

	// 执行速度
	long l = System.currentTimeMillis();
	for (int i = 0; i <= 1000000; i++) {
	    String hourseStrr = getJSONString(hourse);
	    getDTO(hourseStrr, Hourse.class);
	}
	System.out.println(System.currentTimeMillis() - l);
    }

    public static class Hourse {
	private String adress;
	private Person person;
	private Dog dog;

	public Hourse() {

	}

	public Hourse(String adress, Person person, Dog dog) {
	    this.adress = adress;
	    this.person = person;
	    this.dog = dog;
	}

	public String getAdress() {
	    return adress;
	}

	public void setAdress(String adress) {
	    this.adress = adress;
	}

	public Person getPerson() {
	    return person;
	}

	public void setPerson(Person person) {
	    this.person = person;
	}

	public Dog getDog() {
	    return dog;
	}

	public void setDog(Dog dog) {
	    this.dog = dog;
	}

    }

    public static class Person {
	private String name;
	private int age;

	public Person() {

	}

	public Person(String name, int age) {
	    this.name = name;
	    this.age = age;
	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public int getAge() {
	    return age;
	}

	public void setAge(int age) {
	    this.age = age;
	}

	public String toString() {
	    return "name :" + name + "___age :" + age;
	}
    }

    public static class Dog {
	private String master;
	private String variety;

	public Dog() {

	}

	public Dog(String master, String variety) {
	    this.master = master;
	    this.variety = variety;
	}

	public String getMaster() {
	    return master;
	}

	public void setMaster(String master) {
	    this.master = master;
	}

	public String getVariety() {
	    return variety;
	}

	public void setVariety(String variety) {
	    this.variety = variety;
	}

	public String toString() {
	    return " master :" + master + " __variety" + variety;
	}
    }
}
