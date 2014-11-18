package cn.edu.ahpu.utils.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
  
/** 
 * ��ȡExcel��ͨ���ݸ�ʽ 
 * @author Jack 
 * �Զ�ʶ��Office�汾 
 * =>֧��Office 97-2003�汾 
 * =>֧��Office 2007���ϰ汾 
 */  
public class ExcelFactory {  
      
    private String _filepath;           // �ļ�·��(����-xls|-xlsx��ʽ)  
    private Workbook _workbook;         // Excel������(HSSF*|XSSF*����)  
    private Sheet _sheet;               // Excel������  
    private Row _row;                   // Excel��  
    private Cell _cell;                 // Excel��Ԫ��  
    private int _rows;                  // Excel��ʹ�õ�������(����+1)  
    private int _columns;               // Excel����->�������ļ�ͳ�Ƴ���  
    private int _begin;                 // Excel��ʼ��->�������ļ��л�ȡ  
    private boolean isXLS;              // �ж��Ƿ���{-xls}  
    private final static String XLSX = ".xlsx";  
    
    private FileInputStream fis = null;
      
    public ExcelFactory(String filepath, int columns, int begin) throws FileNotFoundException, IOException {  
        _filepath = filepath;  
        _columns = columns;  
        _begin = begin;  
        isXLS();  
        get();  
        
        closeFile(fis);
    }  
      
    public  void closeFile(InputStream fis) {
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
    private void isXLS() {  
        if (-1 < _filepath.indexOf(XLSX)) {  
            isXLS = true;  
            return ;  
        }  
        isXLS = false;  
    }  
      
    @SuppressWarnings("deprecation")
	private Workbook get() throws FileNotFoundException, IOException {  
    	fis = new FileInputStream(_filepath);
        if (null == _workbook){  
            _workbook = isXLS?new XSSFWorkbook(_filepath):new HSSFWorkbook(fis);  
        }  
        return _workbook;  
    }  
    
    public Sheet getSheet(int index) {
    	return _workbook.getSheetAt(index);
    }
    
    public void Read(int index) {  
        _sheet = _workbook.getSheetAt(index);  
        _rows = _sheet.getLastRowNum();  
        for (int y = _begin; y <= _rows; y ++) {  
            _row = _sheet.getRow(y);  
            if (null == _row){  
                continue;  
            }  
              
            for (int x = 0; x < _columns; x ++){  
                _cell = _row.getCell(x);  
                if (null == _cell){  
                    continue;  
                }  
                String cellValue = getValue(_cell);  
            }  
        }  
    }  
      
    public String getValue(Cell cell){
    	if(cell == null) {
    		return "";
    	}
        switch (cell.getCellType()){  
            case Cell.CELL_TYPE_BLANK:  
                return "";  
            case Cell.CELL_TYPE_BOOLEAN:  
                return String.valueOf(cell.getBooleanCellValue());  
            case Cell.CELL_TYPE_ERROR:  
                break;  
            case Cell.CELL_TYPE_FORMULA:  
                return cell.getCellFormula();  
            case Cell.CELL_TYPE_NUMERIC:  
                return NumberToTextConverter.toText(cell.getNumericCellValue());  
            case Cell.CELL_TYPE_STRING:  
                return cell.getStringCellValue();  
            default :  
                return cell.getStringCellValue();  
        }  
        return cell.getStringCellValue();  
    }  
    
    public Date getDateValue(Cell cell) {
    	Date dt = null;
    	if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
        	if(HSSFDateUtil.isCellDateFormatted(cell)) {
        		dt = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
        	}
    	}
		return dt;
    }
}
