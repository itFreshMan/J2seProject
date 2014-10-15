package cn.edu.ahpu.utils.db;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author JHS
 * 驼峰标识
 */
public class CamelCaseUtils {
	private static final char SEPARATOR = '_';

	/**
	public static String toUnderlineName(String s) {
		if (s == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			boolean nextUpperCase = true;
			if (i < (s.length() - 1)) {
				nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
			}
			if ((i >= 0) && Character.isUpperCase(c)) {
				if (!upperCase || !nextUpperCase) {
					if (i > 0)
						sb.append(SEPARATOR);
				}
				upperCase = true;
			} else {
				upperCase = false;
			}
			sb.append(Character.toLowerCase(c));
		}
		return sb.toString();
	}
**/
	
	public static String toCamelCase(String s) {
		if (s == null) {
			return null;
		}
		s = s.toLowerCase();
		StringBuilder sb = new StringBuilder(s.length());
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == SEPARATOR) {
				upperCase = true;
			} else if (upperCase) {
				sb.append(Character.toUpperCase(c));
				upperCase = false;
			} else {
				sb.append(c);
			}
		}
		return sb.toString().trim();
	}

	public static String toCapitalizeCamelCase(String s) {
		if (s == null) {
			return null;
		}
		s = toCamelCase(s);
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
	
	public static String parseSql2CamelCase(String sql){
		System.out.println(sql);
		sql = sql.toLowerCase();
		if(sql.indexOf("union") >= 0){
			String[] sqlArr = sql.split("union");
			sql="";
			for(int k = 0 ; k < sqlArr.length ;k++){
				String sqlItem = sqlArr[k];
				String selectFromStr = sqlItem.substring(sqlItem.indexOf("select")+"select".length(), sqlItem.indexOf("from"));
				if(selectFromStr.contains(",")){
					String[] columnsStrArr = selectFromStr.split(",");
					for(int i = 0 ; i < columnsStrArr.length ; i++){
						String column = columnsStrArr[i];
						if(column.indexOf("as") >= 0 || column.indexOf("*") >= 0 ){
							continue;
						}
						column += " as \""+toCamelCase(column)+"\" ";
						columnsStrArr[i] = column;
					}
					
					String selectFromStrNew = Arrays.toString(columnsStrArr).replace("[","").replace("]", "");
					sqlItem = sqlItem.replace(selectFromStr, selectFromStrNew).trim();
				}
				
				if(k != sqlArr.length -1){
					sql += sqlItem +"\n union \n";
				}else{
					sql += sqlItem;
				}
			}
			
			System.out.println("编辑后sql:\n"+sql);
		}else{
			String selectFromStr = sql.substring(sql.indexOf("select")+"select".length(), sql.indexOf("from"));
			if(selectFromStr.contains(",")){
				String[] columnsStrArr = selectFromStr.split(",");
				for(int i = 0 ; i < columnsStrArr.length ; i++){
					String column = columnsStrArr[i];
					if(column.indexOf("as") >= 0 || column.indexOf("*") >= 0 ){
						continue;
					}
					column += " as \""+toCamelCase(column)+"\" ";
					columnsStrArr[i] = column;
				}
				
				String selectFromStrNew = Arrays.toString(columnsStrArr).replace("[","").replace("]", "");
				sql = sql.replace(selectFromStr, selectFromStrNew);
			}
			System.out.println("编辑后sql:\n"+sql);
		}
		
		
		return sql;
		/*Pattern p = Pattern.compile("select([^;()]*)from");
		Matcher m = p.matcher(sql);
		while(m.find()){
			String selectFromStr = m.group(1);
			System.out.println("select from 之间:"+selectFromStr);
			if(selectFromStr.contains(",")){
				String[] columnsStrArr = selectFromStr.split(",");
				for(int i = 0 ; i < columnsStrArr.length ; i++){
					String column = columnsStrArr[i];
					if(column.indexOf("as") >= 0 || column.indexOf("*") >= 0){
						continue;
					}
					column += " as \""+toCamelCase(column)+"\" ";
					columnsStrArr[i] = column;
				}
				
				String selectFromStrNew = Arrays.toString(columnsStrArr).replace("[","").replace("]", "");
				sql = sql.replace(selectFromStr, selectFromStrNew);
			}
			System.out.println("编辑后sql:"+sql);
			return ;
		}*/
	}

	public static void main(String[] args) {
		String sql1 =" SELECT ID, "+
				" 	USER_CODE,  "+
				" 	USER_NAME,  "+
				" 	PASSWORD,  "+
				" 	SALT,  "+
				" 	AREA_ID,  "+
				" 	ORG_ID,  "+
				" 	BIRTHDAY,  "+
				" 	GENDER,  "+
				" 	PHONE_NO,  "+
				" 	MPHONE_NO, "+
				" 	EMAIL  "+
				" 	FROM  "+
				" 	tpc_user ";
		
		String sql2 ="SELECT "+
				 " t1.id        , "+
				 " (t1.user_code) as \"\", "+
				 " t1.user_name , "+
				 " t1.password  , "+
				 " t1.salt      , "+
				 " t1.area_id  , "+
				 " t1.org_id   , "+
				 " t1.birthday  , "+
				 " t1.gender  , "+
				 " t1.phone_no, "+
				 " t1.mphone_no , "+
				 " t1.email , "+
				 " t1.org_id, "+
				 " t2.name AS orgname "+
				 " FROM tpc_user t1, (SELECT id,name FROM tpc_organization WHERE PARENTID >0) t2 "+
				 " WHERE t1.ORG_ID=t2.id ";

		String unionSql = "SELECT t1.id ,t1.USER_CODE,t1.USER_NAME FROM tpc_user t1 "+
						" UNION "+
						" SELECT t2.id ,t2.code,t2.name FROM tpc_role t2";
		parseSql2CamelCase(unionSql);
	}
}
