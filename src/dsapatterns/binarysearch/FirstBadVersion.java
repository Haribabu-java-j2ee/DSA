package dsapatterns.binarysearch;

//https://leetcode.com/problems/first-bad-version/description/
public class FirstBadVersion {
    public static void main(String[] args) {
        FirstBadVersion obj = new FirstBadVersion();
        int n = 5;
        System.out.println(obj.firstBadVersion(n));
    }

    //dummy API
    boolean isBadVersion(int version) {
        return version >= 4; //assuming version 4 and onwards are bad
    }

    public int firstBadVersion(int n) {
        int start=1;
        int end=n;
        while(start<=end){
            int mid=start+(end-start)/2;
            boolean badVersionFlag=isBadVersion(mid);
            if(badVersionFlag){
                end=mid-1;
            }else{
                start=mid+1;
            }
        }
        return start;
    }
}
