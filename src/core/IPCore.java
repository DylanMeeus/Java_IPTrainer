package core;


import java.util.ArrayList;
import java.util.Random;

public class IPCore 
{
	public IPCore()
	{
		
	}
	
	public String generateIP()
	{
		String IP = "";
		Random rand = new Random();
		for(int i = 0; i < 4; i++)
		{
			IP += rand.nextInt(256) + ".";
		}
		
		return IP.substring(0,IP.length()-1);
	}
	
	public int generateIPSubnetMask()
	{
		Random rand = new Random();
		return rand.nextInt(33);
	}
	
	private ArrayList<String> toByteGroups(String bits)
	{
		return null;
	}
	
	private String toBinaryGroups(String binary)
	{
		ArrayList<String> dividedBinary = new ArrayList<String>();
		for(int i = 1; i < 5; i++)
		{
			dividedBinary.add(binary.substring(i*8-8,8*i));
		}
		
		String result = "";
		for(String group : dividedBinary)
		{
			result += Integer.parseInt(group,2) + ".";
		}
		
		return result;
	}
	public String convertToOldNotation(int newNotation)
	{
		// To binary string with 32 bits
		String binary = "";
		for(int i = 0; i < newNotation; i++)
		{
			binary += 1;
		}
		while(binary.length() < 32)
		{
			binary += 0;
		}
		
		// divide in bytes
		String result = toBinaryGroups(binary);
		return result.substring(0,result.length()-1);
	}
	
	private String toCorrectBinaryFormat(String IP)
	{
		String[] IPParts = IP.split("\\.");
		String IPbits = "";
		for(String s : IPParts)
		{
			String addPart = Integer.toBinaryString(Integer.parseInt(s));
			if(addPart.length()<8)
			{
				for(int i = addPart.length(); i < 8; i++)
				{
					addPart = "0"+addPart;
				}
			}
			IPbits += addPart+".";
		}
		
		return IPbits;		
	}
	
	public String firstInSubnet(String IP, String subnet)
	{
		// Find out which bits of the IP need to stay the same.

		IP = toCorrectBinaryFormat(IP);
		subnet = toCorrectBinaryFormat(subnet);
		
		String first ="";
		for(int bit = 0; bit < subnet.length(); bit++)
		{
			if(subnet.charAt(bit)=='1')
			{
				first += IP.charAt(bit);
			}
		}
		while(first.length() < 31)
		{
			first += "0";
		}
		first+="1";
		first = toBinaryGroups(first);
		return first.substring(0,first.length()-1);
	}
	
	public String highestInSubnet(String IP, String subnet)
	{
		IP = toCorrectBinaryFormat(IP);
		subnet = toCorrectBinaryFormat(subnet);
		
		// Find what doesn't change in the subnet.
		String last ="";
		for(int bit = 0; bit < subnet.length(); bit++)
		{
			if(subnet.charAt(bit)=='1')
			{
				last += IP.charAt(bit);
			}
		}
		while(last.length() < 31)
		{
			last += "1";
		}
		last+="0";
		last = toBinaryGroups(last);
		return last.substring(0,last.length()-1);
	}
}
