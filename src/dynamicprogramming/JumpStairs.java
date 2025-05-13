package dynamicprogramming;
//this is similar to fibonacci, except base condition for 0 steps is 1
public class JumpStairs {
    public static void main(String[] args) {
        System.out.println(jump_ways(5));
    }

    static Long jump_ways(Integer n) {
        // Write your code here.
        Long[] jumpArr=new Long[3];
        jumpArr[0]=1l;
        jumpArr[1]=1l;
        for(int i=2;i<=n;i++){
            if(i%3==0){
                jumpArr[0]=jumpArr[1]+jumpArr[2];
            }else if(i%3==1){
                jumpArr[1]=jumpArr[2]+jumpArr[0];
            }else if(i%3==2){
                jumpArr[2]=jumpArr[0]+jumpArr[1];
            }
        }
        return jumpArr[n%3];
    }

}
