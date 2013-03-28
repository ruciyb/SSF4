package com.lavaspark.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lavaspark.adapter.Move_attr;

import android.app.Application;

public class GlobalVariable  extends Application{

	public  ArrayList<String> nameList = new ArrayList<String>() ;
	public List<String> frameKeyList = new ArrayList<String>();
	public List<HashMap<String, String>> allFrameList = new ArrayList<HashMap<String,String>>();
	public ArrayList<Move_attr> arraymoveList = new ArrayList<Move_attr>();
	
	
	public ArrayList<String> getNameList() {
		return nameList;
	}
	public void setNameList(ArrayList<String> nameList) {
		this.nameList = nameList;
	}
	public List<String> getFrameKeyList() {
		return frameKeyList;
	}
	public void setFrameKeyList(List<String> frameKeyList) {
		this.frameKeyList = frameKeyList;
	}
	public List<HashMap<String, String>> getAllFrameList() {
		return allFrameList;
	}
	public void setAllFrameList(List<HashMap<String, String>> allFrameList) {
		this.allFrameList = allFrameList;
	}
	public ArrayList<Move_attr> getArraymoveList() {
		return arraymoveList;
	}
	public void setArraymoveList(ArrayList<Move_attr> arraymoveList) {
		this.arraymoveList = arraymoveList;
	}
	
}
