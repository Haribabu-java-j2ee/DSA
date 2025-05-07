package recursion.problemset;

//try to learn catalan numbers
public class FindBSTCount {
    public static void main(String[] args) {
        int n = 3;
        System.out.println(how_many_bsts1(3));
    }

    //recursice solution
    static Long how_many_bsts(Integer n) {
        // Write your code here.
        return countBsts(n);
    }

    static Long countBsts(int n) {
        if (n == 0 || n == 1) {
            return 1l;
        } else {
            Long ans = 0l;
            for (int i = 1; i <= n; i++) {
                ans += countBsts(i - 1) * countBsts(n - i);
            }
            return ans;
        }
    }

    //catalan approach
    //https://www.geeksforgeeks.org/program-nth-catalan-number/
    //https://www.geeksforgeeks.org/binomial-coefficient-dp-9/
    //there is iterative approach with o(1) space , checkout
    static Long how_many_bsts1(Integer n) {
        // Write your code here.
        long ncr=nChooseR(2*n,(long)n);
        return ncr/((long)n+1l);
    }

    private static Long nChooseR(long n, long r) {
        if(r>n-r){
            r=n-r;
        }
        long result=1l;
        for(long i=0l;i<r;i++){
            result*=n-i;
            result/=i+1l;
        }
        return result;
    }

}
