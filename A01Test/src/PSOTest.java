
import java.util.Random;


public class PSOTest {

	//�U��local ��m
	static double[] px;
	static double[] py;
	
	//xsin(4x)+1.1ysin(2y)��
	static double[] cost;
	
	//�U��xi,yi�̨�local��m
	static double[] Bpx;
	static double[] Bpy;
	
	//�U�Ӧ첾
	static double[] vx;
	static double[] vy;
	
	//�̨�global��m
	static double BGx;
	static double BGy;
	
	//�̨�global��
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
		
		//��l��
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
		/* �ĤG�N */
		

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
		System.out.printf("�̤p��: %f \n", BGlobal);
		
	}
	
	//�p��xsin(4x)+1.1ysin(2y)�ȡA�ç�X xi,yi Local��Global�̤p��
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
		
	//��첾
	public static void velocity()
	{
		// Math.random �`��
		for(int i = 0; i < px.length; i++)
		{  //key 1
			//vx[i] = (0.8*vx[i])+(2.1*Math.random()*(Bpx[i]-px[i]))+(2.1*Math.random()*(BGx-px[i]));
			//vy[i] = (0.8*vy[i])+(2.1*Math.random()*(Bpy[i]-py[i]))+(2.1*Math.random()*(BGy-py[i]));				
			vx[i] = (k1*vx[i])+(k2*Math.random()*(Bpx[i]-px[i]))+(k2*Math.random()*(BGx-px[i]));
			vy[i] = (k1*vy[i])+(k2*Math.random()*(Bpy[i]-py[i]))+(k2*Math.random()*(BGy-py[i]));				

		}	
	}
	
	//�U�Ӧ�m
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
			if(temp >= 0 && temp <= 10) //�W�X�d��
			{
				py[i] = temp;
			}
		}
	}

}

