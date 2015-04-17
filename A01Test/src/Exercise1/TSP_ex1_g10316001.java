package Exercise1;

import java.util.ArrayList;

public class TSP_ex1_g10316001 {

	public static void main(String[] args)
	{
		
		int [][] node = { {9999,19,92,29,49,78,6},
						  {19,9999,21,85,45,16,26},
						  {92,21,9999,24,26,87,47},
						  {29,85,24,9999,76,17,8},
						  {49,45,26,76,9999,90,27},
						  {78,16,87,17,90,9999,55},
						  {6,26,47,8,27,55,9999}
						};
		int temp = node[0][0];
		int path = 0;
		int i,p,count;
		ArrayList<Integer> clist = new ArrayList<Integer>();
		
		i = 0;
		p = 0;
		count = 0;
		
		while( count < node.length)
		{
			if( count == node.length-1)
			{
				path = path + node[0][p];
			}
			else
			{
				i = p;
				clist.add(i);
				temp = 9999;
				
				for(int j = 0; j < 7; j++)
				{
					for(int b = 0; b <clist.size(); b++)
					{
						if(j == clist.get(b) ) break;
						else if(node[i][j] < temp && b == (clist.size()-1) )
						{
								temp = node[i][j];	
								p = j;
						}
					}
				}
				path = path + temp;
			}
			count++;
		}
		System.out.println(path);
	}
}
