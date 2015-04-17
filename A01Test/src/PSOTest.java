
import java.util.Random;


public class PSOTest {

	//各個local 位置
	static double[] px;
	static double[] py;
	
	//xsin(4x)+1.1ysin(2y)值
	static double[] cost;
	
	//各個xi,yi最佳local位置
	static double[] Bpx;
	static double[] Bpy;
	
	//各個位移
	static double[] vx;
	static double[] vy;
	
	//最佳global位置
	static double BGx;
	static double BGy;
	
	//最佳global值
	static double BGlobal;
	
	static final int Swarm = 30;
	static final int TestNumber = 200;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//初始化
		px = new double[Swarm];
		py = new double[Swarm];
		cost = new double[Swarm];
		
		Bpx = new double[Swarm];
		Bpy = new double[Swarm];
		
		vx = new double[Swarm];
		vy = new double[Swarm];
		
		
		for(int i = 0; i < Swarm; i++)
		{
			px[i] = (int)(Math.random()*10);
			py[i] = (int)(Math.random()*10);
			
			Bpx[i] = px[i];
			Bpy[i] = py[i];
			
			cost[i] = px[i]*Math.sin(4*px[i])+1.1*py[i]*Math.sin(2*py[i]);

			vx[i] = Math.random();
			vy[i] = Math.random();		
		}
		
		for(int j = 0; j<TestNumber; j++)
		{
			countAndfindBest();
			velocity();
			nextPosition();				
		}
		
		countAndfindBest();
		
		System.out.printf("X: %f \n", BGx);
		System.out.printf("Y: %f \n", BGy);
		System.out.printf("最小值: %f \n", BGlobal);
	}
	
	//計算xsin(4x)+1.1ysin(2y)值，並找出 xi,yi Local及Global最小值
	public static void countAndfindBest()
	{
		double temp = 0;	
		BGlobal = cost[1];
		
		for(int i = 0; i < Swarm; i++)
		{
			
			temp = px[i]*Math.sin(4*px[i])+1.1*py[i]*Math.sin(2*py[i]);
			
			if(temp < cost[i]){
				cost[i] = temp;
				Bpx[i] = px[i];
				Bpy[i] = py[i];
			}
			
			if(cost[i]<BGlobal)
			{
				BGlobal = cost[i];
				BGx = px[i];
				BGy = py[i];
			}
		}
	}
		
	//算位移
	public static void velocity()
	{
		for(int i = 0; i < px.length; i++)
		{
			vx[i] = (0.8*vx[i])+(2.1*Math.random()*(Bpx[i]-px[i]))+(2.1*Math.random()*(BGx-px[i]));
			vy[i] = (0.8*vy[i])+(2.1*Math.random()*(Bpy[i]-py[i]))+(2.1*Math.random()*(BGy-py[i]));				
		}	
	}
	
	//下個位置
	public static void nextPosition()
	{
		double temp = 0;
		
		for(int i = 0; i < px.length; i++)
		{
			temp = px[i]+vx[i];
			if(temp >= 0 && temp <= 10)
			{
				px[i] = temp;
			}
			temp = py[i]+vy[i];
			if(temp >= 0 && temp <= 10)
			{
				py[i] = temp;
			}
		}
	}

}

