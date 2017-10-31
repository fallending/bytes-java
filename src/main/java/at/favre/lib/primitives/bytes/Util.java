package at.favre.lib.primitives.bytes;

import java.util.*;

final class Util {
    private Util() {
    }

    /**
     * Returns the values from each provided byteArray combined into a single byteArray.
     * For example, {@code append(new byte[] {a, b}, new byte[] {}, new
     * byte[] {c}} returns the byteArray {@code {a, b, c}}.
     *
     * @param arrays zero or more {@code byte} arrays
     * @return a single byteArray containing all the values from the source arrays, in
     * order
     */
    static byte[] concat(byte[]... arrays) {
        int length = 0;
        for (byte[] array : arrays) {
            length += array.length;
        }
        byte[] result = new byte[length];
        int pos = 0;
        for (byte[] array : arrays) {
            System.arraycopy(array, 0, result, pos, array.length);
            pos += array.length;
        }
        return result;
    }

    /**
     * Returns the index of the first appearance of the value {@code target} in
     * {@code array}.
     *
     * @param array  an array of {@code byte} values, possibly empty
     * @param target a primitive {@code byte} value
     * @return the least index {@code i} for which {@code array[i] == target}, or
     * {@code -1} if no such index exists.
     */
    static int indexOf(byte[] array, byte target, int start, int end) {
        for (int i = start; i < end; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the start position of the first occurrence of the specified {@code
     * target} within {@code array}, or {@code -1} if there is no such occurrence.
     * <p>
     * <p>More formally, returns the lowest index {@code i} such that {@code
     * java.util.Arrays.copyOfRange(array, i, i + target.length)} contains exactly
     * the same elements as {@code target}.
     *
     * @param array  the array to search for the sequence {@code target}
     * @param target the array to search for as a sub-sequence of {@code array}
     */
    public static int indexOf(byte[] array, byte[] target) {
        Objects.requireNonNull(array, "array must not be null");
        Objects.requireNonNull(target, "target must not be null");
        if (target.length == 0) {
            return 0;
        }

        outer:
        for (int i = 0; i < array.length - target.length + 1; i++) {
            for (int j = 0; j < target.length; j++) {
                if (array[i + j] != target[j]) {
                    continue outer;
                }
            }
            return i;
        }
        return -1;
    }

    /**
     * Returns the index of the last appearance of the value {@code target} in
     * {@code array}.
     *
     * @param array  an array of {@code byte} values, possibly empty
     * @param target a primitive {@code byte} value
     * @return the greatest index {@code i} for which {@code array[i] == target},
     * or {@code -1} if no such index exists.
     */
    static int lastIndexOf(byte[] array, byte target, int start, int end) {
        for (int i = end - 1; i >= start; i--) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Copies a collection of {@code Byte} instances into a new array of
     * primitive {@code byte} values.
     * <p>
     * <p>Elements are copied from the argument collection as if by {@code
     * collection.toArray()}.  Calling this method is as thread-safe as calling
     * that method.
     *
     * @param collection a collection of {@code Byte} objects
     * @return an array containing the same values as {@code collection}, in the
     * same order, converted to primitives
     * @throws NullPointerException if {@code collection} or any of its elements
     *                              is null
     */
    static byte[] toArray(Collection<Byte> collection) {
        Object[] boxedArray = collection.toArray();
        int len = boxedArray.length;
        byte[] array = new byte[len];
        for (int i = 0; i < len; i++) {
            array[i] = (Byte) boxedArray[i];
        }
        return array;
    }

    static List<Byte> toList(byte[] arr) {
        List<Byte> list = new ArrayList<>();
        for (byte b : arr) {
            list.add(b);
        }
        return list;
    }

    static Byte[] toObjectArray(byte[] array) {
        Byte[] objectArray = new Byte[array.length];
        for (int i = 0; i < array.length; i++) {
            objectArray[i] = array[i];
        }
        return objectArray;
    }

    /**
     * Simple Durstenfeld shuffle
     * <p>
     * See: https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle#The_modern_algorithm
     *
     * @param array
     * @param random
     * @return shuffled array
     */
    static byte[] shuffle(byte[] array, Random random) {
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            byte a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
        return array;
    }
}
