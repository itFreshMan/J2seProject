package cn.edu.ahpu.utils.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import org.apache.poi.POIXMLDocument;  
import org.apache.poi.POIXMLTextExtractor;  
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;  
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;  
import org.apache.xmlbeans.XmlException;

/**
 * ms word文件读取;
 * @author JHS
 *
 */
public class WordFileReadUtils {

	public static String wordFileRead(String file) throws Exception {
		String type = file.substring(file.lastIndexOf(".") + 1).toLowerCase();
		if(type.equals("doc")){
			return wordRead2003(file);
		}else if(type.equals("docx")){
			return wordRead2007(file);
		}else{
			System.out.println("【" + file + "】不是word文件");
			return null;
		}
	}

	public static String wordRead2003(String file) {
		FileInputStream is = null;
		String text2003 = null;
		try {
			is = new FileInputStream(file);
			WordExtractor ex = new WordExtractor(is);
			text2003 = ex.getText();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}// 载入文档
		catch (IOException e) {
			e.printStackTrace();
		}

		closeFile(is);
//		System.out.println(text2003);
		return text2003;
	}
	

	public static String wordRead2007(String file) {
		OPCPackage opcPackage;
		String text2007 = null;
		try {
			opcPackage = POIXMLDocument.openPackage(file);
			POIXMLTextExtractor ex = new XWPFWordExtractor(opcPackage); 
			text2007 = ex.getText();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlException e) {
			e.printStackTrace();
		} catch (OpenXML4JException e) {
			e.printStackTrace();
		}
//		System.out.println(text2007);
		return text2007;
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
		wordFileRead(projectPath + "/filesForder/docs/**********8.doc");
	}
}
