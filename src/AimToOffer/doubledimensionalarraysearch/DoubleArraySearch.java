package AimToOffer.doubledimensionalarraysearch;

/**
 * ��ά����ÿһ�ж��Ӵ����ҵ�����˳������
 * ÿһ�ж����ϵ��µ�������
 * 1��2��34��56��76��88
 * 3��4��35��57��77��89
 * 4��5��45��58��78��90
 * <p>
 * �ҳ������ҳ�һ����
 * �ȿ��ǲ�������
 * ���target��Ϊint
 * throw�쳣
 * ���target�Ҳ����׳��쳣
 *
 * ˼·�����Ͽ�ʼ��ѯ������ȼ����أ���ǰ���ֱ�targetС�������ƶ���ֱ����һ����target��ʱ����ʼ�����ƶ�
 * @author demo
 */
public class DoubleArraySearch {
    /**
     * ����������
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
                throw new NullPointerException("������ֵ");
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
            throw new IndexOutOfBoundsException("�����˽���");
        }
        return target;
    }

}
