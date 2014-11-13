package cn.edu.ahpu.utils.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * ���ĳһ�ļ����»����ļ� ���Ƿ�����ؼ���
 * @author JHS
 *
 */
public class CheckFile {
	
	private ArrayList<String> sensitiveWords;
	private String path;

	public void doCheck() throws Exception {
		File f = new File(path);
		if(!f.exists()){
			System.out.println(path+">>>>���ļ�����");
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
		System.out.println(fileIndex+"����ʼ����ļ���"+f.getName()+"��");
		BufferedReader br=new BufferedReader(new FileReader(f));
		int lineCount = 1;
		while(br.ready()){
			String line = br.readLine();
			String word = stringContainsWords(line);
			if(word != null){
//				System.out.println(" ��"+f.getName()+"����"+lineCount+" �����ؼ���:"+word);
				System.out.println("[warn]��"+lineCount+" �����ؼ���:"+word);
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
		args0.add("����");
		args0.add("�Ż�");
		args0.add("����");
		String args1 = "C:\\check_doc\\�������ݷ������ƽ̨����취";
		
		new CheckFile(args0,args1).doCheck();
	}
}
