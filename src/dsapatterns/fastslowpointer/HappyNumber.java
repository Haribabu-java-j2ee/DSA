package dsapatterns.fastslowpointer;

/**
 * =====================================================================================
 * HAPPY NUMBER - Floyd's Cycle Detection on Sequence
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/happy-number/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Sum of squares of digits creates a sequence. Either reaches 1 (happy) or enters a
 * cycle (unhappy). Use Floyd's: slow computes once, fast computes twice. When they
 * meet, check if value is 1.
 *
 * =====================================================================================
 * EXAMPLE: n = 19 → true
 * =====================================================================================
 *
 *   Sequence: 19 → 82 → 68 → 100 → 1 → 1 → 1...
 *   
 *   Step | slow      | fast
 *   -----|-----------|------------------
 *    1   | sq(19)=82 | sq(sq(19))=68
 *    2   | sq(82)=68 | sq(sq(68))=1
 *    3   | sq(68)=100| sq(sq(100))=1
 *    4   | sq(100)=1 | sq(sq(1))=1
 *   
 *   slow == fast == 1 → HAPPY NUMBER
 *
 * =====================================================================================
 * EXAMPLE: n = 2 → false (enters cycle: 2→4→16→37→58→89→145→42→20→4...)
 * =====================================================================================
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(log n) - Number of digits decreases, sequence bounded
 *   Space: O(1) - Only two pointers
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. n = 1 → immediately happy
 *   2. n = 7 → happy (7→49→97→130→10→1)
 */
public class HappyNumber {
    public static void main(String[] args) {
        HappyNumber obj=new HappyNumber();
        int n=19;
        System.out.println(obj.isHappy(n));
    }
    public boolean isHappy(int n) {
        int slow=n, fast=n;
        do{
            slow=square(slow);
            fast=square(square(fast));
        }while(slow!=fast);
        return slow==1;
    }

    private int square(int num){
        int ans=0;
        while(num>0){
            int reminder=num%10;
            ans+=reminder*reminder;
            num/=10;
        }
        return ans;
    }
}
