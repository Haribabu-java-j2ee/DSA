package sorting.problemset;

import java.util.ArrayList;

/**
 * LHS is value, RHS is the no of bits need to be moved
 * 5<<2
 * shift 2 bits in 5 which becomes 20
 *
 * Given four billion of 32-bit integers, return any one that’s not among them. Assume you have 1 GiB (10243 bytes) of memory.
 *
 * Follow up: what if you only have 10 MiB of memory?
 *
 * input
 * {
 * "arr": [0, 1, 2, 3]
 * }
 * output: 4
 *
 *
 */
//https://docs.google.com/document/d/12GM6DDn8Swrh_mn9-RZaKl_39zB3T_wpXKFrHz3i8j8/edit?usp=sharing
public class FindMissingin4Billion {
    public static void main(String[] args) {

        System.out.println(5 << 2);
        //System.exit(0);


        long[] arr1 = {0, 1, 2, 3, 4, 5, 6, 7, 7, 8, 9, 10, 11, 12, 13, 14, 15};


        long[] arr2 = {4294967295l, 399999999, 0};

        ArrayList<Long> arr = new ArrayList<>();
        for (int i = 0; i < arr1.length; i++) {
            arr.add(arr1[i]);
        }
        System.out.println(find_integer(arr));
        System.out.println("=====");
        System.out.println(find_integer2(arr));

        System.out.println("------");
        arr = new ArrayList<>();
        for (int i = 0; i < arr2.length; i++) {
            arr.add(arr2[i]);
        }
        System.out.println(find_integer(arr));
        System.out.println("=====");
        System.out.println(find_integer2(arr));



    }

    static Long find_integer(ArrayList<Long> arr) {
        int size = (int) ((Math.pow(2, 32)) / 8);
        byte[] bytes = new byte[size]; // Initialized with zeros by the JVM.
        for (long inputValue : arr) {
            int byteIndex = (int) (inputValue / 8);
            int bitIndex = (int) (inputValue % 8);
            // Set the bit corresponding to inputValue:
            bytes[byteIndex] |= 1 << bitIndex;
        }
        for (int byteIndex = 0; byteIndex < size; byteIndex++) {
            for (int bitIndex = 0; bitIndex < 8; bitIndex++) {
                if ((bytes[byteIndex] & (1 << bitIndex)) == 0) {
                    // As soon as we have found the first unset bit,
                    // return the number corresponding to that bit.
                    return byteIndex * 8L + bitIndex;
                }
            }
        }
        throw new IllegalStateException(
                "Must have found an unset bit and returned from the loop");
    }


    /*
     * Asymptotic complexity in terms of size of `arr` `n`:
     * Time: O(n).
     * Auxiliary space: O(1).
     * Total space: O(n).
     */

    // We split the whole range of possible input values [0..2^32) into
    // 2^16 non-overlapping subranges, each subrange is 2^16-long:
    // 0-th subrange: [0 .. 2^16),
    // 1-st subrange: [2^16 .. 2^17),
    // ...,
    // the last, (2^16-1)-th subrange: [2^32-2^16 .. 2^32).

    static Long find_integer2(ArrayList<Long> arr) {
        int TWO_POWER_SIXTEEN = (int) (Math.pow(2, 16));
        // This array will store how many input array elements actually fall
        // into each bucket. Its size is 2^16 * 8 bytes = 2^19 bytes = 1/2 MiB.
        long[] numbersInBucket = new long[TWO_POWER_SIXTEEN];
        for (long inputValue : arr) {
            // Dividing the number by 2^16 determines which bucket/subrange it falls into.
            int bucket = (int) (inputValue >> 16);
            numbersInBucket[bucket]++;
        }
        // Now let's find a bucket/subrange that has at least one number missing
        // from the input AND then find a missing number in that subrange.
        for (int bucket = 0; bucket < TWO_POWER_SIXTEEN; bucket++) {
            if (numbersInBucket[bucket] >= TWO_POWER_SIXTEEN) {
                // This bucket _might_ have its entire subrange covered by input values.
                // Let's skip it. There certainly exists a bucket will fewer input values.
                continue;
            }
            // We found a bucket with fewer than 2^16 input values fallen into it.
            // This guarantees that at least one value from this bucket's subrange
            // isn't present in the input.
            // Such value is a correct answer, let's find and return it.

            // We will read all the input numbers one more time. This time we will
            // only care about values from this particular bucket/subrange.
            // We will use a simple boolean array to store which values
            // from the current bucket subrange present/absent in the input.
            boolean[] presentInBucket = new boolean[TWO_POWER_SIXTEEN]; // Initialized by false by JVM.
            for (long inputValue : arr) {
                int bucketThisValueFallsInto = (int) (inputValue >> 16);
                if (bucketThisValueFallsInto == bucket) {
                    int indexWithinCurrentSubrange = (int) (inputValue % TWO_POWER_SIXTEEN);
                    presentInBucket[indexWithinCurrentSubrange] = true;
                }
            }
            // Let us find a falsy value in presentInBucket array. It corresponds to a value
            // that's missing in the input. Such value is a correct answer and we can return it.
            for (int i = 0; i < TWO_POWER_SIXTEEN; i++) {
                if (!presentInBucket[i]) {
                    int startOfSubrange = bucket << 16;
                    return startOfSubrange + (long) i;
                }
            }
            throw new IllegalStateException("We knew that the subrange of bucket " + bucket +
                    " isn't completely covered by input numbers but we didn't find" +
                    " any value missing in input.");
        }
        throw new IllegalStateException("We knew that at least one bucket/subrange" +
                " is bound to have fewer than 2^16 input values in it" +
                " (because four billion is less than 2^16 * 2^16) but didn't find any");
    }


}
