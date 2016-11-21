package Reflaction;


import jdk.nashorn.internal.objects.annotations.Constructor;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;

public class UserSystemManager {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        User user = new User("Stiven", 20);


        Class<? extends User> userClass = user.getClass();
        System.out.println("class name = " + user.getClass().getName());

        java.lang.reflect.Constructor<?>[] constructors = userClass.getDeclaredConstructors();

        for (java.lang.reflect.Constructor<?> constructor : constructors) {

            printModByID(constructor.getModifiers());
            System.out.println(Arrays.toString(constructor.getGenericParameterTypes()));
        }


        String test = "test";

        System.out.println(test.hashCode());

        test = test.concat("CXXX");

        System.out.println(test.hashCode());


        // reflaction
        test = "test";
        System.out.println(test + " " + test.hashCode());

        Field value = test.getClass().getDeclaredField("value");
        value.setAccessible(true);
        value.set(test, "test_xxx".toCharArray());
        System.out.println(test + " " + test.hashCode());

    }

    private static void printModByID(int mod) {

        if (Modifier.isPublic(mod)) {
            System.out.println("Public");
        }
        if (Modifier.isFinal(mod)) {
            System.out.println("Finale");
        }
        if (Modifier.isPrivate(mod)) {
            System.out.println("Privet");
        }

    }


}
