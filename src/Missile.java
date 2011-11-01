/*
Solution to the Gild Missile Defense Coding Puzzle
Copyright 2011 Justin Cardoza
*/

public class Missile
{
	public static void main(String[] args) throws java.io.IOException
	{
		java.io.FileInputStream input = new java.io.FileInputStream(args[0]);
		byte[] buffer = new byte[4096];
		float[] altitudes = new float[1600002];
		int[] chainEnd = new int[3000];
		float altitude = 0.0f;
		int n = 0, maxChain = 1, capacity = 4096;
		int i, buflen, low, mid, high;
		
		while((buflen = input.read(buffer)) > 0)
		{
			for(i = 0; i < buflen; i++)
			{
				if(buffer[i] == 10)
				{
					altitudes[n++] = altitude;
					altitude = 0.0f;
				}
				else
				{
					altitude *= 10.0f;
					altitude += buffer[i] - 0x30;
				}
			}
		}
		
		input.close();
		
		for(i = 0; i < n; i++)
		{
			if(altitudes[chainEnd[maxChain - 1]] < altitudes[i])
				chainEnd[maxChain++] = i;
			else
			{
				for(low = 0, high = maxChain - 1; low < high; )
				{
					mid = (low + high) >> 1;
					if(altitudes[chainEnd[mid]] < altitudes[i])
						low = mid + 1;
					else
						high = mid;
				}
				
				if(altitudes[i] < altitudes[chainEnd[low]])
					chainEnd[low] = i;
			}
		}
		
		System.out.println(maxChain);
	}
}
