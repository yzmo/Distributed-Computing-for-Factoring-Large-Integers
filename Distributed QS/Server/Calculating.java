/**
 * Distributed Quadratic Sieve Server
 * Yongzhao Mo
 **/
import java.io.*;
import java.util.*;
import java.math.*;
import java.lang.Object;
//do the maind calculation
public class Calculating {
	
	public static int sss = 0;
	public static int[] permute = new int[sss];
  
    public static BigInteger getCalculating(BigInteger n,int t) 
    {

        String inputLine = null;
    	BigInteger 	   m;
    	int			   i = 0;
    	int			   j = 0;
    	BigInteger x = BigInteger.valueOf(0);
    	BigInteger y = BigInteger.valueOf(0);
    	int			   z = 0;
    	BigInteger temp = BigInteger.valueOf(0);
    	BigInteger factor = BigInteger.valueOf(0);
    	BigInteger multiple = BigInteger.valueOf(0);
    	BigInteger multipleY = BigInteger.valueOf(1);
    	int		primemod = 0;
    	boolean	 bSmooth = false;
    	boolean		flag = false;
    	int			prime= 2;
    	BigInteger miOne = BigInteger.valueOf(-1);
    	BigInteger zero = BigInteger.valueOf(0);
    	BigInteger one = BigInteger.valueOf(1);
    	BigInteger two = BigInteger.valueOf(2);
    	BigDecimal num = new BigDecimal (n);
		BigDecimal guess;
		BigDecimal quotient;
		BigDecimal average;
		BigDecimal twoDe = BigDecimal.valueOf(2);
		BigDecimal half = num.divide(twoDe);
		int scale = 10;
		
		BigInteger[] s  = new BigInteger[t];
		int[][]v = new int[t+1][t];
		int[][]e = new int[t+1][t];
		BigInteger[] a  = new BigInteger[t+1];
    	BigInteger[] b  = new BigInteger[t+1];
    	int[] zeroRow  = new int[t+1];
    	int[] l  = new int[t+1];
    	int[] anAnswer = new int [t+1];
    	BigInteger controlSize = BigInteger.valueOf(0);
    	int replace = 0;
		i = 0;
		guess = half;
		do
		{
			quotient = num.divide(guess,scale, BigDecimal.ROUND_HALF_UP);
			average = (quotient.add(guess)).divide(twoDe);
			guess = average;
			i = i + 1;
		}
		while(i<100);
	
		guess = guess.setScale(0, BigDecimal.ROUND_DOWN);
		m = new BigInteger (String.valueOf(guess));
    	
    	for(i=0;i<t+1;i++)
		{
			for(j=0;j<t;j++)
			{
				v[i][j] = 0;
				e[i][j] = 0;
			}
				
		}
		for(i=0;i<t;i++)
		{
			s[i] = BigInteger.valueOf(0);
			
		}
		for(i=0;i<t+1;i++)
		{
			a[i] = BigInteger.valueOf(0);
			b[i] = BigInteger.valueOf(0);

			l[i] = 0;
		}
		
		//Select the factor base s
		i = 0;
		j = 1;
		s[0] = BigInteger.valueOf(-1);
		while(j<t)
		{
			while(isPrime(prime) == false)
			{
				prime = prime + 1;
			}
			BigInteger step1 = BigInteger.valueOf(prime);
			BigInteger step2 = n.mod(step1);
			BigInteger step3 = step2.pow((prime-1)/2);
			BigInteger step4 = step3.mod(step1);
			if(step4.equals(one))
			{
				s[j]= BigInteger.valueOf(prime);
				i   = i + 1;
				j 	= j + 1;
			}
			else
			{
				i 	= i + 1;
			}
			prime = prime + 1;
		}
		
		
		z = 0;
		do
		{
			if(z>0)
			{
				flag=true;
			}
			else
			{
				flag=false;
			}
			
			multiple = BigInteger.valueOf(0);
			multipleY = BigInteger.valueOf(1);
			//Collect t + 1 pairs(ai,bi)
			for(i=0;i<t+1;i++)
			{
				do   //pick a z to make sure s[t-1]-smooth
				{   
					v[i][0] = 0;
					e[i][0] = 0;
					b[i] = ((m.add(new BigInteger (String.valueOf(z)))).pow(2)).subtract(n);
					temp = b[i].abs();
        				
					if((b[i].compareTo(zero))==0)
					{
						if(flag==false)
						{
							z = -z;
							z = z + 1;
							flag = true;
						}
						else
						{
							z = -z;
							flag = false;
						}
					}
					else
					{
						if((b[i].compareTo(zero))==-1)
						{
							v[i][0] = 1;
							e[i][0] = 1;
						}
					
				
						for(j=1;j<t;j++)
						{	
							while((temp.equals(zero)==false) &&((temp.mod(s[j])).equals(zero)) )
							{
								temp = temp.divide(s[j]) ;
							}
						}
				
					
						if(temp.equals(one)==true)
						{
							break;
						}
						
						
						if(temp.equals(one)==false);
						{
							if(flag==false)
							{
								z = -z;
								z = z + 1;
								flag = true;
							}
							else
							{
								z = -z;
								flag = false;
							}
						}
					}
	
				}while(temp.equals(one)==false);
				
				
				a[i] = m.add(new BigInteger (String.valueOf(z)));
				
				//set v values
				for(j=1;j<t;j++)
				{
					v[i][j] = 0;
					e[i][j] = 0;
				}
				temp = b[i].abs();
				for(j=1;j<t;j++)
				{	
					while((temp.equals(zero)==false) &&(temp.mod(s[j])).equals(zero))
					{
						temp = temp.divide(s[j]);
						v[i][j] = (v[i][j] + 1)%2;
						e[i][j] = e[i][j] + 1;
					}
				}
			
				if(flag==false)
				{
					z = -z;
					z = z + 1;
					flag = true;
				}
				else
				{
					z = -z;
					flag = false;
				}
			
			}
					
			
			controlSize = BigInteger.valueOf(0);
			do
			{
				
				//Find T in set {1,2,...,t+1} such that sum of vi = 0, where i is in set T  prefer Lanczos algorithm
				if(controlSize.equals(zero)==true)
				{
					anAnswer = matrixElimination.elimination1(v,t,t+1);  //matrix, column, row
				}
				else
				{
					anAnswer = matrixElimination.elimination2(v,t,t+1,permute);  //matrix, column, row
				}

							
				flag = false;
				for(i=0;i<t+1;i++)
				{
					l[i] = 0;
				
				}

				for(i=0;i<t+1;i++)
				{
					if(anAnswer[i] == 1)
					{

						if(flag == false)
						{
							multiple = a[i];
							flag = true;
						}
						else
						{
							multiple = multiple.multiply(a[i]);
						
						}
				
					}
				}
			
				x = multiple.mod(n);

				for(i=0;i<t;i++)
				{
					for(j=0;j<t + 1;j++)
					{
						if(anAnswer[j] == 1)
						{
							l[i] = l[i] + e[j][i];
						}	
					}
					l[i] = l[i]/2;
							
				}
			
				flag = false;
				for(i=0;i<t;i++)
				{
					if(flag == false)
					{
						multipleY = s[i].pow(l[i]);
						flag = true;
					}
					else
					{
						multipleY = multipleY.multiply(s[i].pow(l[i]));
					}
				}
				multipleY = multipleY.mod(n);
				
				if(multipleY.compareTo(zero)==-1)
				{
					multipleY = multipleY.add(n);
				}
				
				y = multipleY;
				
				factor = ((x.subtract(y)).abs()).gcd(n);

       			controlSize = controlSize.add(one);

       			if(controlSize.compareTo((two.pow(sss)).subtract(one)) >0)
       			{
       				factor = miOne;
       				break;
       			}
			}while(x == y.mod(n) || x == (y.negate()).mod(n) || factor.equals(one)|| factor.equals(one.negate())|| factor.equals(n)|| factor.equals(n.negate())); 
			
			
			if(factor.equals(miOne) == false)
			{
				break;
			} 
			
			z = z + 5;
			replace = replace + 1;
		}while(replace < 7);
		

		return factor;

    }
    
    // A method to check an integer prime or not
    public static boolean isPrime (int p)
	{
        boolean checkPrime = true;
        int sRoot = (int) Math.sqrt ( p ); 
        for ( int i = 2; i <= sRoot; i++ )
       {
            if ( p % i == 0 )
			{
                checkPrime = false;
                break;
            }
       }

        return checkPrime;
    }
    
    //To improve the speed for numbers have small factors
    public static BigInteger getCalculating2(BigInteger n,int t) 
    {

        String inputLine = null;
    	BigInteger 	   m;
    	int			   i = 0;
    	int			   j = 0;
    	BigInteger x = BigInteger.valueOf(0);
    	BigInteger y = BigInteger.valueOf(0);
    	int			   z = 0;
    	BigInteger temp = BigInteger.valueOf(0);
    	BigInteger factor = BigInteger.valueOf(0);
    	BigInteger multiple = BigInteger.valueOf(0);
    	BigInteger multipleY = BigInteger.valueOf(1);
    	int		primemod = 0;
    	boolean	 bSmooth = false;
    	boolean		flag = false;
    	int			prime= 2;
    	BigInteger miOne = BigInteger.valueOf(-1);
    	BigInteger zero = BigInteger.valueOf(0);
    	BigInteger one = BigInteger.valueOf(1);
    	BigInteger two = BigInteger.valueOf(2);
    	BigDecimal num = new BigDecimal (n);
		BigDecimal guess;
		BigDecimal quotient;
		BigDecimal average;
		BigDecimal twoDe = BigDecimal.valueOf(2);
		BigDecimal half = num.divide(twoDe);
		int scale = 10;
		
		BigInteger[] s  = new BigInteger[t];
		int[][]v = new int[t+1][t];
		int[][]e = new int[t+1][t];
		BigInteger[] a  = new BigInteger[t+1];
    	BigInteger[] b  = new BigInteger[t+1];
    	int[] zeroRow  = new int[t+1];
    	int[] l  = new int[t+1];
    	int[] anAnswer = new int [t+1];
    	BigInteger controlSize = BigInteger.valueOf(0);
    	int replace = 0;
    	
    	
		i = 0;
		guess = half;
		do
		{
			quotient = num.divide(guess,scale, BigDecimal.ROUND_HALF_UP);
			average = (quotient.add(guess)).divide(twoDe);
			guess = average;
			i = i + 1;
		}
		while(i<100);
	
		guess = guess.setScale(0, BigDecimal.ROUND_DOWN);
		m = new BigInteger (String.valueOf(guess));//Compute m = floor of square root of n
    	
    	for(i=0;i<t+1;i++)
		{
			for(j=0;j<t;j++)
			{
				v[i][j] = 0;
				e[i][j] = 0;
			}
				
		}
		for(i=0;i<t;i++)
		{
			s[i] = BigInteger.valueOf(0);
			
		}
		for(i=0;i<t+1;i++)
		{
			a[i] = BigInteger.valueOf(0);
			b[i] = BigInteger.valueOf(0);

			l[i] = 0;
		}
		
		//Select the factor base s
		i = 0;
		j = 1;
		s[0] = BigInteger.valueOf(-1);
		while(j<t)
		{
			while(isPrime(prime) == false)
			{
				prime = prime + 1;
			}
			BigInteger step1 = BigInteger.valueOf(prime);
			BigInteger step2 = n.mod(step1);
			BigInteger step3 = step2.pow((prime-1)/2);
			BigInteger step4 = step3.mod(step1);

			if(step4.equals(one))
			{
				s[j]= BigInteger.valueOf(prime);
				i   = i + 1;
				j 	= j + 1;
			}
			else
			{
				i 	= i + 1;
			}
			prime = prime + 1;
		}
		

		
		z = 0;
		do
		{
			if(z>0)
			{
				flag=true;
			}
			else
			{
				flag=false;
			}
			
			multiple = BigInteger.valueOf(0);
			multipleY = BigInteger.valueOf(1);
			//Collect t + 1 pairs(ai,bi)
			for(i=0;i<t+1;i++)
			{
					v[i][0] = 0;
					e[i][0] = 0;
					b[i] = ((m.add(new BigInteger (String.valueOf(z)))).pow(2)).subtract(n);
					temp = b[i].abs();
        				
					if((b[i].compareTo(zero))==0)
					{
						if(flag==false)
						{
							z = -z;
							z = z + 1;
							flag = true;
						}
						else
						{
							z = -z;
							flag = false;
						}
					}
					else
					{
						if((b[i].compareTo(zero))==-1)
						{
							v[i][0] = 1;
							e[i][0] = 1;
						}
					
				
					}

				
				
				a[i] = m.add(new BigInteger (String.valueOf(z)));
				
				//set v values
				for(j=1;j<t;j++)
				{
					v[i][j] = 0;
					e[i][j] = 0;
				}
				temp = b[i].abs();
				for(j=1;j<t;j++)
				{	
					while((temp.equals(zero)==false) &&(temp.mod(s[j])).equals(zero))
					{
						temp = temp.divide(s[j]);
						v[i][j] = (v[i][j] + 1)%2;
						e[i][j] = e[i][j] + 1;
					}
				}
			
				if(flag==false)
				{
					z = -z;
					z = z + 1;
					flag = true;
				}
				else
				{
					z = -z;
					flag = false;
				}
			
			}
			
			
			
			
			controlSize = BigInteger.valueOf(0);
			do
			{
				
				//Find T in set {1,2,...,t+1} such that sum of vi = 0, where i is in set T  prefer Lanczos algorithm
				if(controlSize.equals(zero)==true)
				{
					anAnswer = matrixElimination.elimination1(v,t,t+1);  //matrix, column, row
				}
				else
				{
					anAnswer = matrixElimination.elimination2(v,t,t+1,permute);  //matrix, column, row
				}

							
				flag = false;
				for(i=0;i<t+1;i++)
				{
					l[i] = 0;
				
				}

				for(i=0;i<t+1;i++)
				{
					if(anAnswer[i] == 1)
					{
						if(flag == false)
						{
							multiple = a[i];
							flag = true;
						}
						else
						{
							multiple = multiple.multiply(a[i]);
						
						}
				
					}
				}
			
				x = multiple.mod(n);

				for(i=0;i<t;i++)
				{
					for(j=0;j<t + 1;j++)
					{
						if(anAnswer[j] == 1)
						{
							l[i] = l[i] + e[j][i];
						}	
					}
					l[i] = l[i]/2;		
				}
			
				flag = false;
				for(i=0;i<t;i++)
				{
					if(flag == false)
					{
						multipleY = s[i].pow(l[i]);
						flag = true;
					}
					else
					{
						multipleY = multipleY.multiply(s[i].pow(l[i]));
					}
				}
				multipleY = multipleY.mod(n);
				
				if(multipleY.compareTo(zero)==-1)
				{
					multipleY = multipleY.add(n);
				}
				
				y = multipleY;
				factor = ((x.subtract(y)).abs()).gcd(n);


       			controlSize = controlSize.add(one);
       			if(controlSize.compareTo((two.pow(sss)).subtract(one)) >0)
       			{
       				factor = miOne;
       				break;
       			}
			}while(x == y.mod(n) || x == (y.negate()).mod(n) || factor.equals(one)|| factor.equals(one.negate())|| factor.equals(n)|| factor.equals(n.negate())); 
			
			
			if(factor.equals(miOne) == false)
			{
				break;
			} 
			
			z = z + 5;
			replace = replace + 1;
		}while(replace < 3);
		

		return factor;

    }
    
   

}