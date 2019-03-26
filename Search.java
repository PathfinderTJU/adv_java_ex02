package cn.edu.tju.ex02;

public abstract class Search {
	
	/*
	 * 用于搜索一个文件，并向另一个文件输出搜索结果的抽象父类
	 * 具体搜索的内容由子类实现决定
	 */
	
	public abstract void search(String filename, String target) throws Exception;
	
}
