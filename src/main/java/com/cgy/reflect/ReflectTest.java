package com.cgy.reflect;

import com.cgy.reflect.annotation.MyMethod;
import com.cgy.reflect.domain.Student;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;

public class ReflectTest {

    public static void main(String[] args) throws Exception {

        Class clazz = Student.class;
        getElementTypes(clazz);
        newInstance(clazz);
        invokeMethod(clazz);
        commonProgramming();
        invokeProgramming(clazz);
        forceVist(clazz);

    }


    /**
     * 获取类的各种信息
     *
     * @param clazz
     */
    public static void getElementTypes(Class<Student> clazz) throws Exception {
        String className = clazz.getName();
        System.out.println(className);
        Field[] fields = clazz.getDeclaredFields(); //获取类属性
        for (Field f : fields) {
            System.out.println(f.getName());
        }

        Method[] methods = clazz.getDeclaredMethods(); //获取本类中所有方法
        for (Method m : methods) {
            System.out.println("方法：" + m.getName());
        }
        Method[] allMethods = clazz.getMethods(); //获取所有方法
        for (Method m : allMethods) {
            System.out.println("方法：" + m.getName());
        }

        Class[] interfaces = clazz.getInterfaces(); //获取所有的接口
        for (Class c : interfaces) {
            System.out.println(c.getName());
        }
        Class superclass = clazz.getSuperclass(); //获取父类
        System.out.println(superclass.getName());
        Method speak = clazz.getMethod("speak", String.class); //获取指定方法(方法名，入参类型...)
        MyMethod myMethod = speak.getAnnotation(MyMethod.class); //获取注解相关信息
        String methodName = myMethod.name();
        String desc = myMethod.desc();
        System.out.println(methodName);
        System.out.println(desc);
    }

    /***
     * 对象的创建
     * @param clazz
     * @throws Exception
     */
    public static void newInstance(Class<Student> clazz) throws Exception {
        //调用默认构造创建，类似 Student s = new Studnet();
        Student s = clazz.newInstance();
        System.out.println(s);
        //无参public构造
        Constructor c1 = clazz.getConstructor(); //getConstructor(Class<?>... parameterTypes) 此方法获只能取public的构造
        Student s1 = (Student) c1.newInstance();
        System.out.println(s1);
        //有参public构造
        Constructor c2 = clazz.getConstructor(String.class, int.class, Date.class);
        Student s2 = (Student) c2.newInstance("张三", 20, new Date());
        System.out.println(s2);
        //私有构造
        Constructor c3 = clazz.getDeclaredConstructor(Date.class);
        c3.setAccessible(true); //私有的类型必须设置暴力访问才可以 包括 Constructor Field  Method
        Student s3 = (Student) c3.newInstance(new Date());
        System.out.println(s3);
    }

    /**
     * 反射调用方法
     */
    public static void invokeMethod(Class clazz) throws Exception {
        Method speak = clazz.getMethod("speak", String.class); //获取共有方法
        Method study = clazz.getDeclaredMethod("study"); //获取私有方法
        study.setAccessible(true);
        speak.invoke(clazz.newInstance(), "大家好");
        study.invoke(clazz.newInstance());

    }

    /**
     * 正常编程
     */
    public static void commonProgramming() {
        Student student = new Student();
        student.setAge(20);
        student.setName("张三");
        student.setBirthday(new Date());
        System.out.println(student);
        student.speak("大家好");
        Student student1 = new Student("李四", 22, new Date());
        System.out.println(student1);
        student1.eat();
    }

    /**
     * 反射编程
     *
     * @param clazz
     * @throws Exception
     */
    public static void invokeProgramming(Class clazz) throws Exception {
        Constructor c = clazz.getConstructor();
        Student student = (Student) c.newInstance();
        Method setAge = clazz.getDeclaredMethod("setAge", int.class);
        setAge.setAccessible(true);//私有方法设置暴力访问
        setAge.invoke(student, 20);
        Method setName = clazz.getDeclaredMethod("setName", String.class);
        setName.setAccessible(true);
        setName.invoke(student, "张三");
        Method setBirthday = clazz.getDeclaredMethod("setBirthday", Date.class);
        setBirthday.setAccessible(true);
        setBirthday.invoke(student, new Date());
        System.out.println(student);
        Method speak = clazz.getMethod("speak", String.class);
        speak.invoke(student, "大家好");
        Constructor c1 = clazz.getConstructor(String.class, int.class, Date.class);
        Student student1 = (Student) c1.newInstance("李四", 22, new Date());
        System.out.println(student1);
        Method eat = clazz.getMethod("eat");
        eat.invoke(student1);
    }

    /**
     * 暴力修改对象信息，一般用不到
     */
    public static void forceVist(Class clazz) throws Exception {

        Field mark = clazz.getDeclaredField("mark");
        mark.setAccessible(true);
        String markStr = (String) mark.get("mark");
        System.out.println(markStr);
        mark.set(clazz, "day day up");
        String markStr1 = (String) mark.get("mark");
        System.out.println(markStr1);

        Field field = clazz.getDeclaredField("INT_VALUE");
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(clazz, 200);
        int value1 = (Integer) field.get("INT_VALUE");
        System.out.println(value1);

    }
}
