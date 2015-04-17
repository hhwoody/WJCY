import java.util.ArrayList;

public class Main
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		int sum = 0;
		int nextPoint = 0;
		int minPath = 0;
		
		int initialPoint = 6; //depart from which point
		System.out.println("Depart from node"+(initialPoint+1) + "\n");
		ArrayList<Integer> passed = new ArrayList<Integer>();
		passed.add(initialPoint);
		int[][] points = { {100, 19, 92, 29, 49, 78, 6 },
				{ 19, 100, 21, 85, 45, 16, 26 }, { 92, 21, 100, 24, 26, 87, 47 },
				{ 29, 85, 24, 100, 76, 17, 8 }, { 49, 45, 26, 76, 100, 90, 27 },
				{ 78, 16, 87, 17, 90, 100, 55 }, { 6, 26, 47, 8, 27, 55, 100 } };
		
		try
		{
			minPath = points[initialPoint][0];
			for(int i=0;i < points[initialPoint].length;i++)			
			{
				if(points[initialPoint][i] < minPath)
				{
					minPath = points[initialPoint][i];
					nextPoint = i;	
				}
				else
				{
					continue;
				}
			}
			System.out.println("下一節點:"+(nextPoint+1)+" 路徑長:" + minPath + " 路徑總和:"+ minPath);
			passed.add(nextPoint);
		} 
		catch (ArrayIndexOutOfBoundsException e)
		{	}
		
		
		try
		{
			while(passed.size() != 7)
			{
				int min = points[nextPoint][0];
				int localMin = min ,pointer = 0;
				for(int i = 0; i<points[nextPoint].length;i++)
				{
					if(points[nextPoint][i] <= min && passed.contains(i))
					{
						if(i+1 != points[nextPoint].length)
							min = points[nextPoint][i+1];
						else
							continue;
						continue;
					}
					else if(points[nextPoint][i] <= min )
					{
						min = points[nextPoint][i];
						pointer = i;
						localMin = min;
					}
					else
					{
						continue;
					}
				}
				minPath += localMin;
				nextPoint = pointer;	
				System.out.println("下一節點:"+(nextPoint+1)+" 路徑長:" + localMin + " 路徑總和:"+ minPath);
				passed.add(nextPoint);
				if(passed.size() == 7)
				{
					System.out.println("下一節點:"+(initialPoint + 1)+ " 路徑長:" + (points[nextPoint][initialPoint]) + " 路徑總和:" + (minPath + points[nextPoint][initialPoint]));
				}
			}
		}
		catch (ArrayIndexOutOfBoundsException e)
		{	}

		
		
		
		
	}
}
