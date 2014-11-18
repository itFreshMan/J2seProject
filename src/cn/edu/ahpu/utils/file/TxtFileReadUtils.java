package cn.edu.ahpu.utils.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 读取txt,sql等常见的文本文件;
 * 
 * @author JHS
 * 
 */
public class TxtFileReadUtils {

	public static String txtFileRead(String file) {
		BufferedReader br = null;
		InputStream in = null;
		StringBuffer text = new StringBuffer();
		try {
			in = new FileInputStream(file);
			br = new BufferedReader(new InputStreamReader(in));
			while (br.ready()) {
				String line = br.readLine();
//				System.out.println(line);
				text.append(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		closeFile(in);
		return text.toString();
	}
	
	public static void closeFile(InputStream in) {
		try {
			if (in != null)
				in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	public static void main(String[] args) {
		String projectPath = System.getProperty("user.dir");
		// txtFileRead(projectPath+"/filesForder/readme.txt");
		txtFileRead(projectPath + "/filesForder/sql.sql");
	}
}
