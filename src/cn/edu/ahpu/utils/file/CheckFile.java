package cn.edu.ahpu.utils.file;

import java.io.File;
import java.util.ArrayList;
/**
 * 检测某一文件夹下或者文件 中是否包含关键字
 * @author JHS
 *
 */
public class CheckFile {
	
	private ArrayList<String> sensitiveWords;
	private String path;

	public void doCheck() throws Exception {
		File f = new File(path);
		if(!f.exists()){
			System.out.println(path+">>>>不文件存在");
			return ;
		}else{
			if(f.isFile()){
				readFileAndCheck(f);
			}else{
				File[] files = f.listFiles();
				for(File ff : files){
					if(ff.isFile()){
						readFileAndCheck(ff);
					}else{
						continue;
					}
				}
			}
		}
	}
	private static int fileIndex = 1;
	public void readFileAndCheck(File f) throws Exception{
		String path = f.getParent();
		String file = path+"/"+f.getName();
		String type = file.substring(file.lastIndexOf(".")).toLowerCase();
		System.out.println(fileIndex+"、开始检测文件【"+file+"】");
		String text = null;
		if(type.endsWith(".doc") || type.endsWith(".docx")){
			text = WordFileReadUtils.wordFileRead(file);
		}else if (type.equals("xls") || !type.equals("xlsx")) {
			text = ExcelFileReadUtils.excelFileRead(file);
		}else{
			text = TxtFileReadUtils.txtFileRead(file);
		}
		
		String word = stringContainsWords(text);
		if(word != null){
			System.out.println(" 【warn】包含关键字:"+word);
		}
		System.out.println();
		
		fileIndex++;
		/*
		BufferedReader br=new BufferedReader(new FileReader(f));
		int lineCount = 1;
		while(br.ready()){
			String line = br.readLine();
			String word = stringContainsWords(line);
			if(word != null){
//				System.out.println(" 【"+f.getName()+"】第"+lineCount+" 包含关键字:"+word);
				System.out.println("[warn]第"+lineCount+" 包含关键字:"+word+":"+line);
			}
			lineCount++;
		}
		br.close();
		System.out.println();
		fileIndex++;*/
	}
	
	public String stringContainsWords(String line){
		for(String word : sensitiveWords){
			if(line.contains(word)){
				return word;
			}
		}
		return null;
	}
	
	public CheckFile() {
		super();
	}

	public CheckFile(ArrayList<String> sensitiveWords, String path) {
		super();
		this.sensitiveWords = sensitiveWords;
		this.path = path;
	}
	
	
	public static void main(String[] args) throws Exception {
		ArrayList<String> args0 = new ArrayList<String>();
		args0.add("cz");
		args0.add("jh");
		String args1 = "C:\\check_doc\\******";
		
		
		new CheckFile(args0,args1).doCheck();
	}
}
