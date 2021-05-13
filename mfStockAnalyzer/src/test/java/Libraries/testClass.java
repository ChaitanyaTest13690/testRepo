package Libraries;

public class testClass {

	public static void main(String[] args)
	{
		String s = "Standard_A2_v2 CPU::2 RAM::4";
		//String s1 = s.substring(s.indexOf(" ")+1);
		//s1.trim();
		String s1 = s.split(" ")[0];
		
		System.out.println(s1);
	}

}
