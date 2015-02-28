package ru.ildev.libgdx.examples.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ru.ildev.libgdx.examples.Box2DExample;
import ru.ildev.libgdx.examples.MyGame;
import ru.ildev.libgdx.examples.SnakeExample;
import ru.ildev.libgdx.examples.SphereExample;
import ru.ildev.libgdx.examples.WaterExample;

/**
 * @author Ilyas Shafigin
 * @version 1.0
 * @since 26.02.15
 */
public class GdxExamples {

    public static final List<Class<? extends GdxExample>> tests = new ArrayList<Class<? extends GdxExample>>(Arrays.asList(
            MyGame.class,
            Box2DExample.class,
            WaterExample.class,
            SphereExample.class,
            SnakeExample.class
    ));

    /**
     *
     * @return
     */
    public static List<String> getNames() {
        List<String> names = new ArrayList<String>(tests.size());
        for (Class clazz : tests)
            names.add(clazz.getSimpleName());
        Collections.sort(names);
        return names;
    }

    private static Class<? extends GdxExample> forName (String name) {
        for (Class clazz : tests)
            if (clazz.getSimpleName().equals(name)) return clazz;
        return null;
    }

    /**
     *
     * @param testName
     * @return
     */
    public static GdxExample newTest(String testName) {
        try {
            return forName(testName).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
