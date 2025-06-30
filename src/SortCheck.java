public class SortCheck {
    public static void main(String[] args) {
        int arr[] = {12, 54, 19, 67, 40};
        int arr1[]={12, 19, 40, 54, 67};
        int arr2[]={67, 54, 40, 19, 12};
        int arr3[]={12, 12, 12, 12, 12};
       /* Array 2 = [12, 19, 40, 54, 67]
        Array 3 = [67, 54, 40, 19, 12]
        Array 4 = [12, 12, 12, 12, 12]*/

        System.out.println(checkSorted(arr));
        System.out.println(checkSorted(arr1));
        System.out.println(checkSorted(arr2));
        System.out.println(checkSorted(arr3));
    }
    public static boolean checkSorted(int[] arr){
        boolean ascendingSort=true;
        boolean descendingSort=true;
        for(int i=0;i<arr.length-1;i++){
            if(arr[i]==arr[i+1]){
                continue;
            }
            if(!(arr[i]<arr[i+1])){
                ascendingSort=false;
            }
        }
        for(int i=0;i<arr.length-1;i++){
            if(arr[i]==arr[i+1]){
                continue;
            }
            if(!(arr[i]>arr[i+1])){
                descendingSort=false;
            }
        }

        return ascendingSort || descendingSort;
    }
}
