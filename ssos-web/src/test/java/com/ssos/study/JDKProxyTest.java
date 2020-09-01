package com.ssos.study;


import sun.misc.ProxyGenerator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JDKProxyTest {
    public static void main(String[] args) throws Exception {
//        Student student = new StudentImpl();
//
//        Student studentProxy = (Student) Proxy.newProxyInstance(student.getClass().getClassLoader(),
//                new Class[]{Student.class},
//                (Object proxy, Method method, Object[] argss) -> {
//                    System.out.println("我是内存中的前置");
//                    method.invoke(student);
//                    return proxy;
//                });
//        studentProxy.getStudent();
//
//        String studentProxyClassName = studentProxy.getClass().toString();
//        studentProxyClassName = studentProxyClassName.substring(studentProxyClassName.lastIndexOf(".") + 2);
//        byte[] bytes = ProxyGenerator.generateProxyClass(studentProxyClassName,new Class[]{Student.class});
//        Files.write(Paths.get("/Volumes/Mac/www/"+studentProxyClassName+".class"),bytes);
//
//
//        URLClassLoader classLoader = new URLClassLoader(new URL[]{new URL("file:/Volumes/Mac/www/")});
//        Class<?> proxy = classLoader.loadClass("Proxy0");
//        Constructor<?> declaredConstructors = proxy.getConstructor(InvocationHandler.class);
//        Student student1 = (Student) declaredConstructors.newInstance(new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] argss) throws Throwable {
//                System.out.println("我是加载class文件的前置");
//                method.invoke(student, argss);
//                return proxy;
//            }
//        });
//        student1.getStudent();
    }
}
