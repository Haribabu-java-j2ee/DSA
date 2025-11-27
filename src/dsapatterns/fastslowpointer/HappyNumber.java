package dsapatterns.fastslowpointer;

//https://leetcode.com/problems/happy-number
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
