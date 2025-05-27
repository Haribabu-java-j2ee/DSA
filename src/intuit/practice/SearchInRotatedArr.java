package intuit.practice;

public class SearchInRotatedArr {
    public static void main(String[] args) {
        int[] arr = {4, 5 ,6 ,7 ,0, 1 ,2};
        int target = 7;
        SearchInRotatedArr search = new SearchInRotatedArr();
        System.out.println(search.getElementIndex(arr, target));
    }

    int getElementIndex(int[] array, int target) {
        // add your logic here
        int rotatedIndex=binarySearchRotated(array,0,array.length-1);
        if(rotatedIndex!=-1){
            if(target<array[0]){
                return binarySearch(array,rotatedIndex,array.length-1,target);
            }else{
                return binarySearch(array,0,rotatedIndex-1,target);
            }
        }
        return binarySearch(array,0,array.length-1,target);
    }

    int binarySearchRotated(int[] array,int low, int high){

        while(low<=high){
            int mid = low  + ((high - low) / 2);

            if(array[mid]>array[mid+1]){
                return mid+1;
            }else if(array[mid]<array[low]){
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return -1;
    }

    int binarySearch(int[] arr, int low, int high, int target){
        while(low<=high){
            int mid = low  + ((high - low) / 2);
            if(arr[mid]==target){
                return mid;
            }else if(target>arr[mid]){
                low=mid+1;
            }else if(target<arr[mid]){
                high=mid-1;
            }
        }
        return -1;
    }
}
