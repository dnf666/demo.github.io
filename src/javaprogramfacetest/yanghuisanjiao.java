package javaprogramfacetest;

/**
 * @author demo
 */
public class yanghuisanjiao {
    static int n = 10;//层数
    static int[][] number = new int[n][n];

    public static void main(String[] args) {
        for (int i = 0; i < number.length; i++) {
            number[i] = new int[i+1];
        }
        for (int i = 0; i < number.length; i++) {
            //给对角线和首位置赋值
            number[i][0] = 1;
            number[i][i] = 1;
            for (int j = 1; j < i ; j++) {
                number[i][j] = number[i-1][j] + number[i-1][j-1];
            }
        }
            //开始遍历输出
            for (int i = 0; i < n; i++) {
            //根据层数
                for (int k = 0; k < n - i; k++) {
                    System.out.print(" ");
                }
                for (int j = 0; j < number[i].length; j++) {
                    System.out.print(number[i][j] + " ");

                }
                System.out.println();
        }
    }
}
