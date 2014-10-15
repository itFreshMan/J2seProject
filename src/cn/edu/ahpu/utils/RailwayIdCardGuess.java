package cn.edu.ahpu.utils;

import org.junit.Test;

/**
 * 
 * @author JHS
 * ���ݻ�Ʊ�ϵ���Ϣ���²����֤����
 */
public class RailwayIdCardGuess {

	private String prefixNum;
	private String suffixNum;
	
	@Test
	public void testGuess(){
		prefixNum = "3401031993";
		suffixNum ="0517";
		
		
		String idCardStr = "";
		for(int i = 1 ; i <= 12 ; i++){
			String birthDateMonth = "";
			if(i < 10){
				birthDateMonth = "0"+i;
			}else{
				birthDateMonth = ""+i;
			}
			for(int j = 1; j <= 31 ; j++){
				String birthDateDate = "";
				if(i < 10){
					birthDateDate = "0"+j;
				}else{
					birthDateDate = ""+j;
				}
				
				idCardStr = prefixNum + birthDateMonth+birthDateDate+suffixNum;
				System.out.println("----->"+idCardStr);
				if(IdCardUtils.validateCard(idCardStr)){
					System.out.println("���֤����:"+idCardStr);
					return ;
				}
			}
			
		}
	}
}
