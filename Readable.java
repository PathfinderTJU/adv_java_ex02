package cn.edu.tju.ex02;

public interface Readable {
	
	/*
	 * 用于从一个文件中读取数据，并以字符串形式返回的接口
	 */
	
	public String read(String filename) throws Exception;
	
}
