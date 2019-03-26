package cn.edu.tju.ex02;

import java.io.*;
import java.util.*;
import java.util.Collections;


public class FrequentSearch extends Search implements Readable, Writable{
	
	/*
	 * ʵ����Search�����������ݵ����࣬��ʵ���˶�д�ļ���Readable��Writable�ӿ�
	 */
	
	public FrequentSearch() {
		
	}
	
	@Override	//���������ʴ�Ƶ�����
	public void search(String filename, String target) throws Exception{
		try {
			String content = read(filename); //���ļ��ж�ȡ���ݣ������ַ�����ʽ�洢
		
			content = deal(content);	//�����ݽ��й���
			
			String[] words = content.split(" +");	//��һ�������ո�ָ��ַ������õ����ʵ�����
			
			Map<String, Integer> map = new HashMap<String, Integer>();	//����ͳ�ƴ�Ƶ
			for (int i = 0 ; i < words.length ; i++) {	//������������
				if (map.keySet().contains(words[i])) {	//�Ѵ��ڣ�Ƶ��+1
					map.put(words[i], map.get(words[i])+1 );
				}
				else {									//�����ڣ������¼
					map.put(words[i], 1);
				}
			}
			
			List<Word> list = new ArrayList<Word>();	//��������
			
			list = sortMap(map);						//��mapת��Ϊlist������
			
			write(list, target);						//����������Ŀ���ļ�
			
		}catch(Exception e){	//�����쳣
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override	//ʵ����Readable�ӿڣ����ļ��ж�ȡ���ݲ������ַ���
	public String read(String filename) throws Exception{	
		File file = null;
		InputStream is = null;	//����inputstream�ֽ�����ȡ
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
			
			return new String(data, "utf-16le");	//Դ�ļ�ΪUTF-16E����
			
		}finally {
			is.close();
		}
		
	}
	
	private String deal(String content) {	//���ļ��е����ݽ��й���
		StringBuffer temp = new StringBuffer(content);	//����StringBuffer�������˵Ľ��
		for (int i = 0 ; i < temp.length() ; i++) {
			char c = temp.charAt(i);
			if (isUpper(c)) {	//ȫ����д��ΪСд
				c += 32;
				temp.setCharAt(i, c);
			}
			if (Useless(c)) {	//�����ַ��ÿո��滻
				temp.setCharAt(i, ' ');
			}
		}
		
		return new String(temp);
	}
	
	
	private boolean Useless(char c) {	//�ж�һ���ַ��Ƿ�Ϊ�����ַ�
		if (c >= 48 && c <= 57 ) {	//����0-9����
			return false;
		}else if (c >= 65 && c <= 90) {		//��ĸA-Z����
			return false;
		}else if (c >= 97 && c <= 122) {	//��ĸa-z����
			return false;
		}else if (c == '-') {		//���ַ�-����
			return false;
		}else {						//�����Ϊ����
			return true;
		}
	}
	
	private boolean isUpper(char c) {	//�ж�һ���ַ��Ƿ�Ϊ��д�ַ�
		if (c >= 'A' && c <= 'Z') {
			return true;
		}else {
			return false;
		}
		
	}

	private List<Word> sortMap(Map<String, Integer> map) {	//��mapתΪlist������
		List<Word> list = new ArrayList<Word>();
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {		//������Ϊmap�е�ÿ����¼����Word���󣬴���list��
			String temp = it.next();
			list.add(new Word(temp, map.get(temp)));
		}
		
		Collections.sort(list);			//����list������word��compareTo������˳��Ϊ����		
		return list;
	}
	
	@Override	//ʵ����writable�ӿڣ��������ļ������list�Ľ��
	public void write(List<Word> list, String target) throws Exception{
		File file = null;
		PrintStream os = null;	//����printStream��������
		try {
			file = new File("output" + File.separator + target);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			
			if (!file.exists()) {
				file.createNewFile();
			}
			os = new PrintStream(file);
			
			String[] result = new String[list.size()];	//��list�еĽ������String���飬��ʽΪkey�� value
			Iterator<Word> it = list.iterator();
			int i = 0;
			while (it.hasNext()) {
				Word temp = it.next();
				result[i] = temp.getKey() + ": " + temp.getTimes();	
				i++;
			}
			
			for (int j = 0 ; j < list.size() ; j++){	//�����Ŀ���ļ�
				os.println(result[j]);
			}
			
		}finally {
			os.close();
		}
		
	}
}
