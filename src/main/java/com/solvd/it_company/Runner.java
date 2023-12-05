package com.solvd.it_company;

import java.lang.reflect.*;

public class Runner {
    public static void main(String[] args) {
        Class<?> clazz = Application.class;
        Field[] fields = clazz.getDeclaredFields();
        System.out.println("FIELDS");
        for (Field field : fields) {
            System.out.println(field.getName());
            System.out.println(Modifier.toString(field.getModifiers()));
        }
        System.out.println("\n METHODS");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.print(method.getName() + " " + Modifier.toString(method.getModifiers()) +
                    " " + method.getReturnType().getSimpleName() + " ");

            //PARAMETRS
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                System.out.println(parameter.getType().getSimpleName());
            }
            System.out.println();
        }

        System.out.println("\nCONSTRUCTORS");
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor.getName() + " " + Modifier.toString(constructor.getModifiers()) + " "
                    + constructor.getParameterCount());
        }
        //INVOKE
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor(String.class, int.class, String.class, int.class);
            constructor.setAccessible(true);
            Object applicationObject = constructor.newInstance("store", 3, "Internet store", 1200);

            Method method = clazz.getDeclaredMethod("whoAmI");
            method.setAccessible(true);
            Object result = method.invoke(applicationObject);
            System.out.println("\nResult of invoke method : " + result.toString());
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
