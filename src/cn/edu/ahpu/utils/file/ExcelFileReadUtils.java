package cn.edu.ahpu.utils.file;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * ms Excel文件读取;
 * @author JHS
 *
 */
public class ExcelFileReadUtils {

	public static String excelFileRead(String file) throws Exception {
		String type = file.substring(file.lastIndexOf(".") + 1).toLowerCase();
		if (!type.equals("xls") && !type.equals("xlsx")) {
			System.out.println("【" + file + "】不是excel文件");
		}
		
		ExcelFactory excelFactory = new ExcelFactory(file,1, 1);
		StringBuffer text = new StringBuffer();
		
		Sheet sheet = excelFactory.getSheet(0); //取得第一个SHEET
		int sheetRowNum = sheet.getLastRowNum();
		for(int i = 1 ; i <= sheetRowNum ;i++ ){
			Row row = sheet.getRow(i);
			if(row == null ){
				System.out.println();
				continue;
			}
			StringBuffer sb = new StringBuffer();
			Iterator<Cell> it = row.cellIterator();
			while(it.hasNext()){
				Cell cell = it.next();
				String cellValue = excelFactory.getValue(cell);
				sb.append(cellValue.trim()+"\t");
			}
//			System.out.println(sb.toString());
			text.append(sb.toString());
		}
		
		return text.toString();
	}

	public static void closeFile(InputStream fis) {
		try {
			if (fis != null)
				fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null)
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	public static void main(String[] args) throws Exception {
		String projectPath = System.getProperty("user.dir");
		excelFileRead(projectPath + "/filesForder/excels/最新电子档报销模板.xls");
	}
}
