package controllers;

import core.IPCore;

public class MainController
{
	IPCore ipcore;
	String currentIP;
	int currentMask;
	public MainController(IPCore ip)
	{
		ipcore = ip;
	}
	
	public String generateQuestion()
	{
		currentIP = ipcore.generateIP();
		currentMask = ipcore.generateIPSubnetMask();
		
	//	System.out.println("subnet: " + ipcore.convertToOldNotation(currentMask) + "\nvan: " + ipcore.firstInSubnet(currentIP, ipcore.convertToOldNotation(currentMask))
	//			+"\ntot: " + ipcore.highestInSubnet(currentIP, ipcore.convertToOldNotation(currentMask)));
		
		return "Het IP adres is " + currentIP + "/" + currentMask;
	}
	
	public boolean subnetIsCorrect(String subnet)
	{
		try
		{
			return subnet.equals(ipcore.convertToOldNotation(currentMask)) ?  true : false;
		}
		catch(Exception ex)
		{
			System.out.println("Not an IP-adress!");
			return false;
		}
	}
	
	public boolean lowestIsCorrect(String IP)
	{
		try
		{
			return (IP.equals(ipcore.firstInSubnet(IP, ipcore.convertToOldNotation(currentMask))) ? true : false);
		}
		catch(Exception ex)
		{
			System.out.println("Not an IP-adress!");
			return false;
		}
	}
	public boolean highestIsCorrect(String IP)
	{
		try
		{
			return (IP.equals(ipcore.highestInSubnet(IP, ipcore.convertToOldNotation(currentMask)))? true : false);
		}
		catch(Exception ex)
		{
			System.out.println("Not an IP-adress!");
			return false;
		}
	}
}
