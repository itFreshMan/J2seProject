package cn.edu.ahpu.utils.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
		System.out.println(fileIndex+"、开始检测文件【"+f.getName()+"】");
		BufferedReader br=new BufferedReader(new FileReader(f));
		int lineCount = 1;
		while(br.ready()){
			String line = br.readLine();
			String word = stringContainsWords(line);
			if(word != null){
//				System.out.println(" 【"+f.getName()+"】第"+lineCount+" 包含关键字:"+word);
				System.out.println("[warn]第"+lineCount+" 包含关键字:"+word);
			}
			lineCount++;
		}
		System.out.println();
		fileIndex++;
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
		args0.add("池州");
		args0.add("九华");
		args0.add("利辛");
		String args1 = "C:\\check_doc\\利辛数据服务管理平台管理办法";
		
		new CheckFile(args0,args1).doCheck();
	}
}
