package cn.edu.tju.ex02;

public class Word implements Comparable{
	
	/*
	 * 用于存放单词及出现频率的对象，便于排序
	 */
	
	private String key;	//单词名
	private Integer times;	//出现频率
	
	public Word() {
		
	}
	
	public Word(String key, Integer times) {
		this.key = key;
		this.times = times;
	}

	@Override	//重写equals方法，当且仅当两个word对象key与times均相等时返回true
	public boolean equals(Object o) {
		if (o == null) {
			throw new NullPointerException();
		}
		
		if (o instanceof Word) {
			Word that = (Word) o;
			if (this.times.equals(that.times) && this.key.equals(that.key)) {
				return true;
			}else {
				return false;
			}
		}else {
			throw new IllegalArgumentException();
		}
	}
	
	/*
	 * 重写compareTo方法，便于排序
	 * 依据times大小排序，且为降序排列
	 */
	@Override	
	public int compareTo(Object o) {
		if (o == null) {
			throw new NullPointerException();
		}
		
		if (o instanceof Word) {
			Word that = (Word) o;
			if (this.times.equals(that.times) && this.key.equals(that.key)) {
				return 0;
			}else {
				return -Integer.valueOf(this.times.compareTo(that.times));
			}
		}else {
			throw new IllegalArgumentException();
		}
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}
	
}
