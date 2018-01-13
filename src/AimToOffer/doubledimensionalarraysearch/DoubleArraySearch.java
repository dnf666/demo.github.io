package AimToOffer.doubledimensionalarraysearch;

/**
 * 二维数组每一列都从从左到右递增的顺序排序
 * 每一列都从上到下递增排序
 * 1，2，34，56，76，88
 * 3，4，35，57，77，89
 * 4，5，45，58，78，90
 * <p>
 * 找出从中找出一个数
 * 先考虑测试用例
 * 如果target不为int
 * throw异常
 * 如果target找不到抛出异常
 *
 * 思路从左上开始查询，当相等即返回，当前数字比target小，向右移动。直到下一个比target大时，开始向下移动
 * @author demo
 */
public class DoubleArraySearch {
    /**
     * 测试用数组
     */
    private static final int[][] test = {{1, 2, 34, 56, 76, 88}, {3, 4, 35, 57, 77, 89},
            {4, 5, 45, 58, 78, 90}};
    private static Integer target = 4;
    public static void main(String[] args) {
        search(target);
    }

    private static void search(Integer target) {
        try {
            Integer integer1 = testTargetIndex(target);
            Integer integer2 = testTargetSearch(integer1,test);
            if(integer2 == 0){
                throw new NullPointerException("不存在值");
            }
            System.out.println("result:"+integer2);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }


    private static Integer testTargetSearch(Integer integer,int[][] test) {
        int j = 5;
        int i = 0;

         while (i < 3){
                while(j >= 0)
                {
                    int a = test[i][j];
                    if(a != integer)
                    {
                        if(a > integer) {
                            j--;
                            continue;
                        }else{
                            i++;
                            break;
                        }
                    }else {
                        return test[i][j];
                    }

            }
            
        }
        return 0;

    }

    private static Integer testTargetIndex(Integer target) throws IndexOutOfBoundsException {
        if (target <= 0 || target > Integer.MAX_VALUE) {
            throw new IndexOutOfBoundsException("超过了界限");
        }
        return target;
    }

}
