package cn.edu.tju.ex02;

import java.io.*;
import java.util.*;
import java.util.Collections;


public class FrequentSearch extends Search implements Readable, Writable{
	
	/*
	 * 实现了Search具体搜索内容的子类，并实现了读写文件的Readable、Writable接口
	 */
	
	public FrequentSearch() {
		
	}
	
	@Override	//搜索各单词词频并输出
	public void search(String filename, String target) throws Exception{
		try {
			String content = read(filename); //从文件中读取数据，并以字符串形式存储
		
			content = deal(content);	//对数据进行过滤
			
			String[] words = content.split(" +");	//以一个或多个空格分割字符串，得到单词的数组
			
			Map<String, Integer> map = new HashMap<String, Integer>();	//用于统计词频
			for (int i = 0 ; i < words.length ; i++) {	//遍历单词数组
				if (map.keySet().contains(words[i])) {	//已存在，频数+1
					map.put(words[i], map.get(words[i])+1 );
				}
				else {									//不存在，新添记录
					map.put(words[i], 1);
				}
			}
			
			List<Word> list = new ArrayList<Word>();	//用于排序
			
			list = sortMap(map);						//将map转换为list并排序
			
			write(list, target);						//将结果输出到目标文件
			
		}catch(Exception e){	//处理异常
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override	//实现了Readable接口，从文件中读取数据并返回字符串
	public String read(String filename) throws Exception{	
		File file = null;
		InputStream is = null;	//采用inputstream字节流读取
		try {
			file = new File("data" + File.separator + filename);
			
			if (!file.exists()) {
				System.out.println("File not found.");
			}else if(file.isDirectory()){
				System.out.println("File is a directory.");
			}
			
			is = new FileInputStream(file);
			Long lengthOfData = file.length();
			byte[] data = new byte[lengthOfData.intValue()];
			is.read(data);
			
			return new String(data, "utf-16le");	//源文件为UTF-16E编码
			
		}finally {
			is.close();
		}
		
	}
	
	private String deal(String content) {	//对文件中的数据进行过滤
		StringBuffer temp = new StringBuffer(content);	//采用StringBuffer保留过滤的结果
		for (int i = 0 ; i < temp.length() ; i++) {
			char c = temp.charAt(i);
			if (isUpper(c)) {	//全部大写变为小写
				c += 32;
				temp.setCharAt(i, c);
			}
			if (Useless(c)) {	//无用字符用空格替换
				temp.setCharAt(i, ' ');
			}
		}
		
		return new String(temp);
	}
	
	
	private boolean Useless(char c) {	//判断一个字符是否为无用字符
		if (c >= 48 && c <= 57 ) {	//数字0-9有用
			return false;
		}else if (c >= 65 && c <= 90) {		//字母A-Z有用
			return false;
		}else if (c >= 97 && c <= 122) {	//字母a-z有用
			return false;
		}else if (c == '-') {		//连字符-有用
			return false;
		}else {						//其余均为无用
			return true;
		}
	}
	
	private boolean isUpper(char c) {	//判断一个字符是否为大写字符
		if (c >= 'A' && c <= 'Z') {
			return true;
		}else {
			return false;
		}
		
	}

	private List<Word> sortMap(Map<String, Integer> map) {	//将map转为list并排序
		List<Word> list = new ArrayList<Word>();
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {		//遍历，为map中的每条记录创建Word对象，存入list中
			String temp = it.next();
			list.add(new Word(temp, map.get(temp)));
		}
		
		Collections.sort(list);			//排序list，根据word的compareTo方法，顺序为降序		
		return list;
	}
	
	@Override	//实现了writable接口，用于向文件中输出list的结果
	public void write(List<Word> list, String target) throws Exception{
		File file = null;
		PrintStream os = null;	//采用printStream流输出结果
		try {
			file = new File("output" + File.separator + target);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			
			if (!file.exists()) {
				file.createNewFile();
			}
			os = new PrintStream(file);
			
			String[] result = new String[list.size()];	//将list中的结果存入String数组，形式为key： value
			Iterator<Word> it = list.iterator();
			int i = 0;
			while (it.hasNext()) {
				Word temp = it.next();
				result[i] = temp.getKey() + ": " + temp.getTimes();	
				i++;
			}
			
			for (int j = 0 ; j < list.size() ; j++){	//输出到目标文件
				os.println(result[j]);
			}
			
		}finally {
			os.close();
		}
		
	}
}
