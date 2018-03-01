/**
 * @author demo
 * 二分查找算法实践
 */
public class BinarySearch {
    public static void main(String[] args) {
        System.out.println( binarySearch());
        int a = 5,b=6,c=2;
        int z = (a*b-c)/a;
        System.out.println(z);
    }


    private static String binarySearch() {
        int numbers[] = {1,2,3,4,5,6,7,8,9};
        int target = 3;
        int length = numbers.length;
        int start = 0;
        int end = length - 1;
        int midId = -1;
        if (target < numbers[start] || target > numbers[end]){
            return "target不在范围内";
        }else{
            while (start <= end){
                midId =(start+end)/2;
                if(target == numbers[midId]){
                    return Integer.toString(target);
                }
                if (numbers[midId] > target){
                    end = midId-1;
                }else {
                    start = midId +1;
                }
            }

        }
        return "不存在值";
    }
}
