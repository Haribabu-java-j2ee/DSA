import java.util.*;

public class SortCheck {
    public static void main(String[] args) {
        SortCheck obj = new SortCheck();
        int[] nums = {2, -99, -99, 2, 2};
        System.out.println(obj.circularArrayLoop(nums));
    }
    private int arrayLength;
    private int[] array;

    /**
     * Determines if there exists a circular loop in the array.
     * A loop must:
     * 1. Have more than one element
     * 2. All elements in the loop must have the same direction (all positive or all negative)
     *
     * @param nums Array where nums[i] indicates the number of steps to jump from index i
     * @return true if a valid circular loop exists, false otherwise
     */
    public boolean circularArrayLoop(int[] nums) {
        arrayLength = nums.length;
        this.array = nums;

        // Try to find a loop starting from each position
        for (int startIndex = 0; startIndex < arrayLength; startIndex++) {
            // Skip positions already marked as invalid (value set to 0)
            if (array[startIndex] == 0) {
                continue;
            }

            // Use two pointers (slow and fast) to detect cycles
            int slowPointer = startIndex;
            int fastPointer = getNextIndex(startIndex);

            // Continue while all elements have the same sign (same direction)
            while (array[slowPointer] * array[fastPointer] > 0 &&
                    array[slowPointer] * array[getNextIndex(fastPointer)] > 0) {

                // Cycle detected when slow and fast pointers meet
                if (slowPointer == fastPointer) {
                    // Check if the loop has more than one element
                    if (slowPointer != getNextIndex(slowPointer)) {
                        return true;
                    }
                    break;
                }

                // Move slow pointer one step and fast pointer two steps
                slowPointer = getNextIndex(slowPointer);
                fastPointer = getNextIndex(getNextIndex(fastPointer));
            }

            // Mark all elements in the current path as invalid (set to 0)
            // since they don't form a valid loop
            int currentIndex = startIndex;
            while (array[currentIndex] * array[getNextIndex(currentIndex)] > 0) {
                array[currentIndex] = 0;
                currentIndex = getNextIndex(currentIndex);
            }
        }

        return false;
    }

    /**
     * Calculates the next index in the circular array based on the jump value.
     * Handles both positive and negative jumps with proper wrapping.
     *
     * @param currentIndex The current position in the array
     * @return The next index after jumping from the current position
     */
    private int getNextIndex(int currentIndex) {
        // Calculate next position with proper modulo handling for negative values
        // First modulo ensures the jump value is within bounds
        // Adding arrayLength and second modulo handles negative results
        return (currentIndex + array[currentIndex] % arrayLength + arrayLength) % arrayLength;
    }
}
