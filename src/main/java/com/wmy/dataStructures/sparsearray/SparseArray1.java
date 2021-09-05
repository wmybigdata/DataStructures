package com.wmy.dataStructures.sparsearray;

import java.io.*;

/**
 * @project_name: DataStructures
 * @package_name: com.wmy.dataStructures.sparsearray
 * @Author: wmy
 * @Date: 2021/9/5
 * @Major: 数据科学与大数据技术
 * @Post：大数据实时开发
 * @Email：wmy_2000@163.com
 * @Desription: 要求：
 * 1）在前面的基础上，将稀疏数组保存到磁盘上，比sparseArr.txt
 * 2）恢复原来的数组时，读取sparseArr.txt进行恢复操作
 * @Version: wmy-version-01
 */
public class SparseArray1 {
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
                System.out.printf("%d\t", chess);
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

        int[][] sparseArr = new int[sum + 1][3];
        int num = 1;
        sparseArr[0][0] = chessArr1.length; // 去行
        sparseArr[0][1] = chessArr1[0].length; // 去列
        sparseArr[0][2] = sum;

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

        try {
            System.out.println("创建/打开文档");
            File file = new File("./sparse1.txt");
            FileOutputStream fileStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileStream, "UTF-8");
            System.out.println("写入中………………");
            for (int i = 0; i < sparseArr.length; i++) {
                outputStreamWriter.write(String.valueOf(sparseArr[i][0]));
                outputStreamWriter.write(",");
                outputStreamWriter.write(String.valueOf(sparseArr[i][1]));
                outputStreamWriter.write(",");
                outputStreamWriter.write(String.valueOf(sparseArr[i][2]));
                outputStreamWriter.write(",");

            }
            outputStreamWriter.close();
            fileStream.close();
            System.out.println("写入成功");
            System.out.println("读取中………………");
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            StringBuffer stringBuffer = new StringBuffer();
            while (inputStreamReader.ready()) {
                stringBuffer.append((char) inputStreamReader.read());
            }
            inputStreamReader.close();
            fileInputStream.close();
            System.out.println("读取成功");
            String string = stringBuffer.toString();
            String[] chessFields = stringBuffer.toString().split(",");
            System.out.println("读取数据字符串为：");
            System.out.println(string);

            int cnt = 0;
            int[][] sp = new int[chessFields.length / 3][3];
            sp[0][0] = Integer.parseInt(chessFields[0]);
            sp[0][1] = Integer.parseInt(chessFields[1]);
            sp[0][2] = Integer.parseInt(chessFields[2]);
            for (int i = 3; i < chessFields.length; i += 3) {
                cnt++;
                sp[cnt][0] = Integer.parseInt(chessFields[i]);
                sp[cnt][1] = Integer.parseInt(chessFields[i + 1]);
                sp[cnt][2] = Integer.parseInt(chessFields[i + 2]);
            }
            System.out.println("还原稀疏数组：");
            System.out.println("row" + "\t" + "col" + "\t" + "val");
            for (int[] row : sp) {
                for (int data : row) {
                    System.out.printf("%d\t", data);
                }
                System.out.println();
            }

            System.out.println("还原原始数组：");
            int[][] chessArr2 = new int[sp[0][0]][sp[0][1]];
            for (int i = 1; i < sp.length; i++) {
                chessArr2[sp[i][0]][sp[i][1]] = sp[i][2];
            }

            for (int[] row : chessArr2) {
                for (int data : row) {
                    System.out.printf("%d\t", data);
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
