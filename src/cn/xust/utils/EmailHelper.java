package cn.xust.utils;

import java.util.HashMap;

public class EmailHelper {

	public final static HashMap<Character,String> map = new HashMap<>();
	static {
		map.put('0',"ABCW");
		map.put('1',"WJUFN");
		map.put('2',"ASDA");
		map.put('3',"MMJI");
		map.put('4',"OLJGB");
		map.put('5',"TYYBVBNJ");
		map.put('6',"EDGBAU");
		map.put('7',"QUSBA");
		map.put('8',"UYANNF");
		map.put('9',"UNNGG");
		
		map.put('A',"WYBFA");
		map.put('B',"POLMBV");
		map.put('C',"UYBBN");
		map.put('D',"UGBNJ");
		map.put('E',"AUDNU");
		map.put('F',"AUNBA");
		map.put('G',"MNVCK");
		map.put('H',"PLOUAN");
		map.put('I',"ZCXZX");
		map.put('J',"POLZA");
		map.put('K',"MNVC");
		map.put('L',"SHIRO");
		map.put('M',"MVC");
		map.put('N',"SPRING");
		map.put('O',"OISNA");
		map.put('P',"ASUNF");
		map.put('Q',"LINH");
		map.put('R',"YABBF");
		map.put('S',"POISA");
		map.put('T',"MNGS");
		map.put('U',"KKKJH");
		map.put('V',"GHJKHG");
		map.put('W',"LKJJHG");
		map.put('X',"IUTB");
		map.put('Y',"AGSYHF");
		map.put('Z',"TYSBA");
	}
	
	
	
	public static String encryption(String code) {
		char[] arr = code.toCharArray();
		StringBuffer sb = new StringBuffer();
		for(char item : arr) {
			sb.append(map.get(item));
		}
		
		return sb.toString();
	}
	
	
	
}
