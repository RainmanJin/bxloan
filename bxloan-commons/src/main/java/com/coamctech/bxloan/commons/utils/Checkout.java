package com.coamctech.bxloan.commons.utils;

import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * 校验码生成器
 *
 * @author zhoufeng, 3-21
 *
 *
 *
 * 3-21,MQC:5338 -- 根据证件号码生成校验位时，忽略所有的非字母和非数字，小写字母转换成大写字母计算。
 */
public class Checkout 
{
	
	private static int Mod = 36;
	
	private static int mod = 35;
	
	private static HashMap char2int = new HashMap();
	
	public static char padding = '#';
	public static char padding1 = '-';
	
	private static char[] int2char = 
			{'0','1','2','3','4','5','6','7','8','9',
			'A','B','C','D','E','F','G','H','I','J',
			'K','L','M','N','O','P','Q','R','S','T',
			'U','V','W','X','Y','Z'};
	
	static
	{
		char2int.put("0",new Integer(0));
		char2int.put("1",new Integer(1));
		char2int.put("2",new Integer(2));
		char2int.put("3",new Integer(3));
		char2int.put("4",new Integer(4));
		char2int.put("5",new Integer(5));
		char2int.put("6",new Integer(6));
		char2int.put("7",new Integer(7));
		char2int.put("8",new Integer(8));
		char2int.put("9",new Integer(9));
		char2int.put("A",new Integer(10));
		char2int.put("B",new Integer(11));
		char2int.put("C",new Integer(12));
		char2int.put("D",new Integer(13));
		char2int.put("E",new Integer(14));
		char2int.put("F",new Integer(15));
		char2int.put("G",new Integer(16));
		char2int.put("H",new Integer(17));
		char2int.put("I",new Integer(18));
		char2int.put("J",new Integer(19));
		char2int.put("K",new Integer(20));
		char2int.put("L",new Integer(21));
		char2int.put("M",new Integer(22));
		char2int.put("N",new Integer(23));
		char2int.put("O",new Integer(24));
		char2int.put("P",new Integer(25));
		char2int.put("Q",new Integer(26));
		char2int.put("R",new Integer(27));
		char2int.put("S",new Integer(28));
		char2int.put("T",new Integer(29));
		char2int.put("U",new Integer(30));
		char2int.put("V",new Integer(31));
		char2int.put("W",new Integer(32));
		char2int.put("X",new Integer(33));
		char2int.put("Y",new Integer(34));
		char2int.put("Z",new Integer(35));
		
	}
	
	public static char generate(String originalStr)
	throws Exception
	{
		//检查输入参数
		if(originalStr==null || originalStr.length()==0)
		{
			throw new Exception("the input paramter is null or empty!");
		}
		
		//检查输入参数格式
//		if(!checkStrForm(originalStr))
//		{
//			throw new Exception("please check the form of the input parameter!");
//		}
		
		//去除填充符
		String shortStr = deletePadding(originalStr);

		//开始运算
		char C = generateChecksum(shortStr);
		
		return C;
	}
	
	public static String check(String checksumStr)
	throws Exception
	{
		if(checksumStr==null || checksumStr.length()==0)
		{
			throw new Exception("the input paramter is null or empty!");
		}
		
		//检查输入参数格式
//		if(!checkStrForm(checksumStr))
//		{
//			throw new Exception("please check the form of the input parameter!");
//		}
		
		//去除填充符
		String shortStr = deletePadding(checksumStr);
		
		
		String originalStr = shortStr.substring(0,shortStr.length()-1);
		
		char checksum = shortStr.charAt(shortStr.length()-1);
		
		char result = generateChecksum(originalStr);
		
		if(result == checksum)
		{
			return checksumStr.substring(0,checksumStr.length()-1);
		}
		else
		{
			throw new Exception();
		}
		
	}
	
	private static String deletePadding(String str)
	{
		String newStr = "";

		for(int i=0 ; i<str.length(); i++)
		{
            char c = str.charAt(i);
			if( c>='a' && c <='z' ) c = Character.toUpperCase(c);
            if( (c>='0' && c<='9') || (c>='A' && c <='Z') ) newStr = newStr+c;
		}

		return newStr;
	}

    /**
     * @deprecated for 忽略所有的非字母和非数字. 3-21,MQC:5338
     * @param str
     * @return
     */
	private static boolean checkStrForm(String str)
	{
		for(int i=0; str!=null && i<str.length(); i++)
		{
			char c = str.charAt(i);
			if((c<'0' || c>'Z' || (c>'9'&&c<'A')) && c!=padding && c!=padding1 )
			{
				return false;
			}
		}
		
		return true;
	}
	
	private static char generateChecksum(String str)
	{
		//开始运算
		int p=Mod;
		for(int i=0; i<str.length(); i++)
		{
			char c = str.charAt(i);
			
			char[] d = {c};
			
			int a = ((Integer)char2int.get(new String(d))).intValue();
			
			p = (((p+a)%mod)*2)%Mod;
		}
		
		int result = mod - p;
		
		return int2char[result];
	}
	
	
	public static String getAlgorithName()
	{
		return "ISO 7064-1983";
	}
	
	public static char getPadding() 
	{
		return padding;
	}

	public static void setPadding(char padding) 
	{
		Checkout.padding = padding;
	}

    public static void main(String[] args)
    {
        try
        {
            System.out.println( "|" + Checkout.generate( "1234@#$#=454ad") );
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
