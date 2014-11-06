package cn.edu.ahpu.utils.db;

import java.io.BufferedReader;
import java.io.FileReader;

public class MultiInsertSql {
	private static String fileAbstPath = new MultiInsertSql().getClass().getResource("").getPath();
	private static Integer id = 1224;
	private static Integer dictTypeId = 10221;
	private static Integer orderIndex = 81;

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new FileReader(fileAbstPath + "/sql.txt"));
		while(br.ready()){
			String code = br.readLine();
			if(code != null && code.trim().length() > 0){
				id++;
				orderIndex++;
//				System.out.println(br.readLine());
				String name = "";
				if(code.trim().length() != 4){
					System.out.println(code+"的长度不为4>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					continue;
				}
				if(code.trim().equals("D000")){
					name="活期";
				}else if(code.trim().equals("M000")){
					name="小于一个月";
				}else {
					String suffix = "";
					if(code.startsWith("Y")){
						suffix = "年";
					}else if(code.startsWith("M")){
						suffix = "月";
					}else if(code.startsWith("D")){
						suffix = "日";
					}
					
					String numberPart = code.trim().substring(1);
					name = Integer.parseInt(numberPart)+suffix;
				}
				
				String template = "INSERT INTO UNTECK_DICT_ENTRY "+
						          " (ID,CODE, ENABLED, NAME,ORDER_INDEX,DICT_TYPE_ID,DEL_FLAG,CREATE_TIME,CREATE_USER) "+
								  " VALUES("+        
							    	 id+","+ 
									 "'"+code+"',"+ 
									 "'Y',"+ 
									 "'"+name+"',"+ 
									 orderIndex+","+ 
									 dictTypeId+","+ 
									 "0,"+ 
									 "'2014-11-06 10:02:06',"+ 
									 "'admin');";
									        
			   System.out.println(template.trim());     
									       
			}
		}
	}
}
