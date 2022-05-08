package me.happylandmc.core.math;

public class Number {
    public static int getRandom(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }

    public static double getRandom(final double start, final double end) {
        //return start + ((end - start) * new Random().nextDouble());
        return (Math.random() * (end - start + 1) + start);
    }

}
