package cn.edu.ahpu.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

public class PatternTest {

	private String webLogicLog = "172.26.155.244 - - [26/Feb/2001:10:56:03 -5000]	"
			+ "\"GET /IsAlive.htm HTTP/1.0\" 200 15";

	@Test
	public void testWebLogicLog() {
		Pattern p = Pattern.compile("a*b");
		Matcher m = p.matcher("aaaaab");
		boolean b = m.matches();
		
		Assert.assertTrue("≤ª∆•≈‰", b);
	}
	
	@Test
	public void testIp(){
		String regex = "(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})" +
				"\\s-\\s-\\s" +
				"\\[([^]]+)\\]." ;
//		
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(webLogicLog);
		while(m.find()){
			System.out.println("IPADDRESS = "+m.group(1));
			System.out.println("TIMESTAMP = "+m.group(2));
//			System.out.println(m.group(3));
		}
		
//		boolean b = Pattern.matches(regexp,	webLogicLog);
		
		
	}
}
