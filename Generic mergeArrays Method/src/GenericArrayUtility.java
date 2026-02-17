import java.lang.reflect.Array;

public class GenericArrayUtility {

    public static <T> T[] mergeArrays(T[] a, T[] b) {// a for 1st array and b for 2nd array  

        if (a == null && b == null) {
            return null;
        }
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }

        @SuppressWarnings("unchecked")
        T[] merged = (T[]) Array.newInstance(
                a.getClass().getComponentType(),
                a.length + b.length
        );

        for (int i = 0; i < a.length; i++) {
            merged[i] = a[i];
        }

        for (int i = 0; i < b.length; i++) {
            merged[a.length + i] = b[i];
        }

        return merged;
    }
}
