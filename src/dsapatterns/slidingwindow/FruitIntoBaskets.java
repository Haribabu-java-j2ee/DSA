package dsapatterns.slidingwindow;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/fruit-into-baskets/
public class FruitIntoBaskets {
    public static void main(String[] args) {
        int[] fruits={1,2,1,2,3};
        FruitIntoBaskets obj=new FruitIntoBaskets();
        System.out.println(obj.totalFruit(fruits));
    }
    public int totalFruit(int[] fruits) {
        Map<Integer,Integer> fruitMap=new HashMap<>();
        int left=0;
        int maxFruits=0;
        for(int right=0;right<fruits.length;right++){
            int rightFruit=fruits[right];
            fruitMap.put(rightFruit,fruitMap.getOrDefault(rightFruit,0)+1);
            while(fruitMap.size()>2){
                int leftFruit=fruits[left];
                left++;

                if(fruitMap.merge(leftFruit,-1,Integer::sum)==0){
                    fruitMap.remove(leftFruit);
                }
            }
            maxFruits=Math.max(maxFruits,right-left+1);
        }
        return maxFruits;
    }
}
