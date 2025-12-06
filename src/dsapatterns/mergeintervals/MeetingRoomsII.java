package dsapatterns.mergeintervals;

import java.util.Arrays;

//https://www.geeksforgeeks.org/dsa/meeting-rooms-find-minimum-meeting-rooms/
public class MeetingRoomsII {
    public static void main(String[] args) {
        MeetingRoomsII obj=new MeetingRoomsII();
        int[] start={2, 9, 6};
        int[] end={4, 12, 10};
        System.out.println(obj.minMeetingRooms(start,end));
        System.out.println(obj.minMeetingRoomsCountingSort(start,end));
    }
    int minMeetingRooms(int[] start, int[] end) {
        Arrays.sort(start);
        Arrays.sort(end);
        int i=0,j=0;
        int room=0;
        int result=0;
        while(i<start.length){
            if(start[i]<end[j]){
                room++;
                i++;
            }else{
                room--;
                j++;
            }
            result=Math.max(result,room);
        }
        return result;
    }

    int minMeetingRoomsCountingSort(int[] start, int[] end) {
        int maxEnd=end[0];
        for(int i=1;i<end.length;i++){
            maxEnd=Math.max(maxEnd,end[i]);
        }

        int[] room=new int[maxEnd+2];
        int result=0;
        for(int i=0;i<start.length;i++){
            room[start[i]]++;
            room[end[i]]--;
        }
        int temp=0;
        for(int i=0;i<=maxEnd+1;i++){
            temp+=room[i];
            result=Math.max(result,temp);
        }
        return result;
    }
}
