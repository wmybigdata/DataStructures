package com.wmy.dataStructures.sparsearray;

/**
 * @project_name: DataStructures
 * @package_name: com.wmy.dataStructures.sparsearray
 * @Author: wmy
 * @Date: 2021/9/5
 * @Major: 数据科学与大数据技术
 * @Post：大数据实时开发
 * @Email：wmy_2000@163.com
 * @Desription: 稀疏数组案例演示
 * 要求：
 *  1）在前面的基础上，将稀疏数组保存到磁盘上，比sparseArr.txt
 *  2）恢复原来的数组时，读取sparseArr.txt进行恢复操作
 * @Version: wmy-version-01
 */
public class SparseArray {
    public static void main(String[] args) {

        // 创建一个原始的二维数组 11 * 11
        // 0：表示没有棋子
        // 1：表示黑子
        // 2：表示篮子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;

        // 输出原始的二维数组
        System.out.println("输出原始的二维数组：");
        for (int[] row : chessArr1) {
            for (int chess : row) {
                System.out.printf("%d\t",chess);
            }
            System.out.println();
        }

        // 将二维数组转稀疏数组
        // 1、先遍历二维数组得到非0数据的个数
        int sum = 0; // 记录非零的个数
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1.length; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println("数组中非零的数据的个数为：" + sum);

        // 2、创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        // 给稀疏数组赋值
        sparseArr[0][0]=chessArr1.length; // 去行
        sparseArr[0][1]=chessArr1[0].length; // 去列
        sparseArr[0][2]=sum;

        // 遍历二维数组，将非零的值存放到稀疏数组中去
        int count = 0; // 记录是第几个非零的数据
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1.length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        // 输出稀疏数组的形式
        System.out.println("输出稀疏数组：");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
            System.out.println();
        }

        // 将稀疏数组恢复成原始的二维数组
        // 读取稀疏数组的第一行数据，来进行创建一个二维数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        // 遍历稀疏数组
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        System.out.println("输出转换后的二维数组：");
        for (int[] row : chessArr2) {
            for (int chess : row) {
                System.out.printf("%d\t",chess);
            }
            System.out.println();
        }
    }
}
