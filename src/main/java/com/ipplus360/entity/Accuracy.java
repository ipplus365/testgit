package com.ipplus360.entity;

import org.apache.ibatis.type.Alias;

/**
 * Created by 辉太郎
 * 定位精度对应表
 * 创建时间2017年3月7日   
 */
@Alias("accuracy")
public class Accuracy {
	//精度id
	private int id;
	//精度名称
    private String accuracy;
    
    public Accuracy(){
    	
    }
    
    public Accuracy(Integer id){
    	this.id = id;
    }
    
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAccuracy() {
		return accuracy;
	}
	
	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}
    
	@Override
	public String toString(){
		return "Accuracy [id="+ id
				+ ", accuracy="+ accuracy
				+ "]";
	}
    
}
