package woody;

import java.util.Random;

public class M1040424 {
		static String[] p = new String[50];// 基因chromose
	    static double[] E = new double[50];// 權重
	    static Random rd = new Random();
	    static double min = Double.MAX_VALUE;
	    static double min_x = 0;
	    static double min_y = 0;
	    static int min_g = 0;
	    public static void main(String[] args)
	    {
	        createP();
	        for (int i = 0; i < 100; i++)// 世代
	        {
	            Calculate(i);
	            New_generation();
	            Crossover();
	            Mutation();
	        }
	        System.out.print("最小值:"+min+"\n在第"+min_g+"代\n x = "+min_x+"\n y = "+min_y);
	    }
	    public static void createP()// 產生基因
	    {
	        for (int i = 0; i < 50; i++)
	        {
	            p[i] = "";
	            for (int j = 0; j < 20; j++)
	            {
	                if (rd.nextDouble() >= 0.5)
	                    p[i] += "1";
	                else
	                    p[i] += "0";
	            }
	        }
	    }
	    public static void Calculate(int count)// 計算各點值
	    {
	        double x = 0;
	        double y = 0;
	        double total = 0;
	        for (int i = 0; i < 50; i++)
	        {
	            for (int j = 0; j < 20; j++)
	            {
	                if (j < 10)// x轉換
	                {
	                    if (p[i].charAt(j) == '1')// 二進位轉十進位 1010=
	                        x = x * 2 + 1;
	                    else
	                        x *= 2;
	                }
	                else if (j < 20)// y轉換
	                {
	                    if (p[i].charAt(j) == '1')// 二進位轉十進位
	                        y = y * 2 + 1;
	                    else
	                        y *= 2;
	                }
	            }
	            x = x / 1023.0 * 10;
	            y = y / 1023.0 * 10;
	            E[i] = -(x * Math.sin(4 * x) + 1.1 * y * Math.cos(2 * y)) + 20;// 線性權重比
	            if (x * Math.sin(4 * x) + 1.1 * y * Math.cos(2 * y) < min)//最小值
	            {
	                min = x * Math.sin(4 * x) + 1.1 * y * Math.cos(2 * y);
	                min_x = x;
	                min_y = y;
	                min_g = count;
	            }
	            total += E[i];
	            x = 0;
	            y = 0;
	        }
	        System.out.println(min);
	        for (int i = 0; i < 50; i++)
	        {
	            E[i] /= total;// 相對全體比例
	        }
	    }
	    public static void New_generation()// 產生新世代
	    {
	        String[] pass = new String[50];
	        for (int i = 1; i < 49; i++)
	        {
	            E[i] += E[i - 1];
	        }
	        E[49] = 1;
	        for (int i = 0; i < 50; i++)
	        {
	            double x = rd.nextDouble();
	            for (int j = 0; j < 50; j++)
	            {
	                if (E[j] > x)
	                {
	                    pass[i] = p[j];
	                    break;
	                }
	            }
	        }
	        for (int i = 0; i < 50; i++)
	        {
	            p[i] = pass[i];
	        }
	    }
	    public static void Crossover()// 交配
	    {
	        String x = "";
	        for (int i = 0; i < 50; i += 2)
	        {
	            x = p[i].substring(10, 20);
	            p[i] = p[i].substring(0, 10) + p[i + 1].substring(10, 20);
	            p[i + 1] = p[i + 1].substring(0, 10) + x;
	        }
	    }
	    public static void Mutation()// 突變
	    {
	        int count = (int) (rd.nextDouble() * 100);
	        int x = (int) (rd.nextDouble() * 50);
	        int y = (int) (rd.nextDouble() * 20);
	        for (int i = 0; i < count; i++)
	        {
	            if (p[x].charAt(y) == '1')
	                p[x] = p[x].substring(0, y) + "0" + p[x].substring(y + 1, 20);
	            else
	                p[x] = p[x].substring(0, y) + "1" + p[x].substring(y + 1, 20);
	        }
	    }
}