/**
 * Local Quadratic Sieve
 * Yongzhao Mo
 **/
 
import java.io.*;
import java.util.*;
import java.math.*;
import java.lang.Object;


public class matrixElimination 
{
	public static int myRow;
	
	public static int[] elimination1(int[][] v, int r,int c)
	{
		myRow = r;
		int[][]vv = new int[r][c];
		int[][]vvv = new int[c][c];
		int[] a = new int[c];
		int i = 0;
		int j = 0;
		int[] flag1 = new int[c];  
		int s = 0;
		int p = 0;
		int[] zeroRow = new int[c];
		int[] answer = new int[c];
		
		for(i=0;i<c;i++)   //transpose v into vv
		{
			for(j=0;j<r;j++)
			{
				vv[j][i] = v[i][j];
				
			}
		}

		for(i=0;i<c;i++)
		{
			flag1[i] = 0;
			a[i] = 0;
			zeroRow[i] = 0;
			answer[i] = 0;
		}

		vvv = getMatrix(vv,r,c);
		
		
		for(i=0;i<c;i++) //check pivoting
		{
			if(vvv[i][i] == 1)
			{
				flag1[i] = 0;
				
			}
			else
			{
				flag1[i] = 1;
			
				s = s + 1;
				
			}
		}

		Calculating.sss = s;
		
		int[] permute = new int[s];
		
		for(i=0;i<s;i++)
		{
			permute[i] = 0;
		}
		
	

		j = 0;
		for(i=0;i<c;i++)
		{
			if(flag1[i]==1)
			{
				a[i] = permute[j];
				j = j + 1;
			}
		}
		

		answer = backSub(vvv, flag1, c,a);
		permute = permutation(permute,s);
		Calculating.permute = permute;
		
		
		return answer;
		
	}
	
	public static int[] elimination2(int[][] v, int r,int c,int[] permute)
	{
		myRow = r;
		int[][]vv = new int[r][c];
		int[][]vvv = new int[c][c];
		int[] a = new int[c];
		int i = 0;
		int j = 0;
		int[] flag1 = new int[c];  
		int s = 0;
		int p = 0;
		int[] zeroRow = new int[c];
		int[] answer = new int[c];
		
		for(i=0;i<c;i++)   //transpose v into vv
		{
			for(j=0;j<r;j++)
			{
				vv[j][i] = v[i][j];
				
			}
		}

		for(i=0;i<c;i++)
		{
			flag1[i] = 0;
			a[i] = 0;
			zeroRow[i] = 0;
			answer[i] = 0;
		}

		vvv = getMatrix(vv,r,c);
		
		
		
		for(i=0;i<c;i++) //check pivoting
		{
			if(vvv[i][i] == 1)
			{
				flag1[i] = 0;
				
			}
			else
			{
				flag1[i] = 1;
			
				s = s + 1;
				
			}
		}

		j = 0;
		for(i=0;i<c;i++)
		{
			if(flag1[i]==1)
			{
				a[i] = permute[j];
				j = j + 1;
			}
		}
		
		answer = backSub(vvv, flag1, c,a);
		
		permute = permutation(permute,s);
		Calculating.permute = permute;
		
		return answer;
	}
		
	public static int[][] getMatrix(int[][] v, int r,int c)
	{
		int[][]vt = new int[c][c];
		int[] row = new int[c];
		int[][] zeroRow = new int[1][c];
		int i = 0;
		int j = 0;
		int k = 0;
		int pivot = 0;

		
		for(i=0;i<c;i++)
		{
			row[i] = 0;
			zeroRow[0][i] = 0;
		}
		
		for(i=0;i<r;i++)
		{
			vt[i] = v[i];
		}
		for(i=r;i<c;i++)
		{
			vt[i] = zeroRow[0];
		}
		
		for(i=0;i<r;i++)  //pivoting
		{
			
			pivot = vt[i][i];
			if(pivot==0)
			{
				for(j=i+1;j<r;j++)
				{
					if(vt[j][i]==1)
					{
						row = vt[i];
						vt[i] = vt[j];
						vt[j] = row;
						break;
					}
				
				}
				
				
			}
			pivot = vt[i][i];
			
			
			if(pivot==1)
			{
				for(j=i+1;j<r;j++)
				{
					if(vt[j][i]==1)
					{
						vt[j] = arraySubtract(vt[j],vt[i],c);
					}
				
				}
			}
			
		}
		
		myRow = r;
		return vt;
	}
	
		
	public static int[] arraySubtract(int[] a, int[] b, int size)
	{
		int i = 0;
		int[] c = new int[size];
		
		for(i=0;i<size;i++)
		{
			c[i] = (a[i] - b[i]+2)%2;
		}
		return c;
	}
	
	
	
	//This function generates all possible permutations of the values
	public static int[] permutation(int[] v,int t)
	{
   		int i;

   		for(i=0;i<t;i++)
   		{
       		v[i] = (v[i]+1)%2;   //change  to opposite bit
       		if(v[i]==1)    //make sure generate all permutations
       		break;
   		}
   		
   		return v;

	}
	
	//backward substitution
	public static int[] backSub(int[][] v, int[] flag, int c, int[] a)
	{
		int[] solution = a;
		int t = 0;
		int j = 0;
		
		for(j=c-1;j>-1;j--)
		{
			
			if(flag[j] == 0)
			{
				if(j==c-1)
				{
					solution[j] = 0;
				}
				else
				{
					solution[j] = 2*c;
					for(t=j+1;t<c;t++)
					{
						solution[j] = solution[j] - solution[t]*v[j][t];
					}
					solution[j] = solution[j]%2;
				}
				
			}
		}
		

		return solution;
	}
	

}	
	