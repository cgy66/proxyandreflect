package com.cgy.reflect.domain;

import com.cgy.reflect.annotation.MyMethod;


import java.io.Serializable;
import java.util.Date;

public class Student implements Person, Serializable {
    private static String mark = "good good study";
    private static final Integer INT_VALUE = 10 ;

    private String name;

    private int age;

    private Date birthday;

    //无参构造
    public Student() {

    }

    //有参构造
    public Student(String name, int age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    //私有构造
    private Student(Date birthday) {
        this.name = null;
        this.age = 0;
        this.birthday = birthday;
    }

    //实现接口方法
    public void eat() {
        System.out.println("吃饭");
    }

    public void sleep() {
        System.out.println("睡觉");
    }

    //成员方法
    @MyMethod(name = "speak", desc = "学生演讲的功能")
    public String speak(String text) {
        System.out.println(text);
        return text;
    }

    //成员方法
    private void study() {
        System.out.println("学生在学习。。。");
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }
//geter and setter


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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


}
