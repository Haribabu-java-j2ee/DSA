package recursion.problemset;

//not working with recursive approach need to check with support

public class Power {
    public static void main(String[] args) {
        long a=10000,b=10000000;
        System.out.println(calculate_power1(a,b));
        long result=1;
        System.out.println(calculate_power(a,b,result));

    }

    static final int i= 1000000007;
    static Integer calculate_power(Long a, Long b, Long result) {
        // Write your code here.
        if(b==0){
            return 1;
        }
        if(b==1){
             return (int)(a%MOD);
        }
        a=a%MOD;

        if(b%2==1){
            return (int)(a*result)%MOD;
        }
        return ((calculate_power(a,b/2,result)%MOD)*(calculate_power(a,b/2, result)%MOD))%MOD;


    }


    /*
     * Asymptotic complexity in terms of `b`:
     * Time: O(log(b)).
     * Auxiliary space: O(1).
     * Total space: O(1).
     */
    static final int MOD = (int)1e9 + 7;

    static Integer calculate_power1(Long a, Long b) {
        // stores final evaluated power a^b
        long result = 1;
        // stores a^(power of two)
        // initialized with a^(2^0)
        long powerOfTwo = a % i;

        // iterate over all bits of b
        while (b != 0) {
            // if current bit is set
            if (b % 2 == 1) {
                // multiply the current power in result
                result = (result * powerOfTwo) % i;
            }
            // double the power of two
            // a^i * a^i = a^(2*i)
            powerOfTwo = (powerOfTwo * powerOfTwo) % i;
            // move to next bit of b
            b = b / 2;
        }
        return (int)result;
    }

    static Integer calculate_power3(Long a, Long b) {
        // stores final evaluated value of a^b % MOD
        long result = 1;
        // iterate 1 to b
        for (int i = 0; i < b; i++) {
            // keep multiplying a
            result = (result * a) % MOD;
        }
        return (int)result;
    }

    static Integer calculate_power2(Long a, Long b) {
        if(b==0){
            return 1;
        }
        if(b==1){
            return (int)(a%MOD);
        }
        a=a%MOD;
        long temp=calculate_power2(a,b/2);
        if(b%2==0){
            return (int)(temp*temp)%MOD;
        }else{
            return (int)((((temp*temp)%MOD)*a)%MOD);
        }
    }
}
