
import java.util.Random;


public class PSOTest {

	//local 竚
	static double[] px;
	static double[] py;
	
	//xsin(4x)+1.1ysin(2y)
	static double[] cost;
	
	//xi,yi程ㄎlocal竚
	static double[] Bpx;
	static double[] Bpy;
	
	//簿
	static double[] vx;
	static double[] vy;
	
	//程ㄎglobal竚
	static double BGx;
	static double BGy;
	
	//程ㄎglobal
	static double BGlobal;
	static double k1;
	static double k2;
	
	static final int Swarm = 30; //50-100
	static final int TestNumber = 200; //key 1
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//﹍て
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
		/* 材 */
		

		for(int j = 0; j<TestNumber; j++)
		{
			countAndfindBest();
			velocity();
			nextPosition();		
			// k=k+0.1;
		}
		
		countAndfindBest();
		
		System.out.printf("X: %f \n", BGx);
		System.out.printf("Y: %f \n", BGy);
		System.out.printf("程: %f \n", BGlobal);
		
	}
	
	//璸衡xsin(4x)+1.1ysin(2y)т xi,yi LocalのGlobal程
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
		
	//衡簿
	public static void velocity()
	{
		// Math.random 盽计
		for(int i = 0; i < px.length; i++)
		{  //key 1
			//vx[i] = (0.8*vx[i])+(2.1*Math.random()*(Bpx[i]-px[i]))+(2.1*Math.random()*(BGx-px[i]));
			//vy[i] = (0.8*vy[i])+(2.1*Math.random()*(Bpy[i]-py[i]))+(2.1*Math.random()*(BGy-py[i]));				
			vx[i] = (k1*vx[i])+(k2*Math.random()*(Bpx[i]-px[i]))+(k2*Math.random()*(BGx-px[i]));
			vy[i] = (k1*vy[i])+(k2*Math.random()*(Bpy[i]-py[i]))+(k2*Math.random()*(BGy-py[i]));				

		}	
	}
	
	//竚
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
			if(temp >= 0 && temp <= 10) //禬絛瞅
			{
				py[i] = temp;
			}
		}
	}

}

