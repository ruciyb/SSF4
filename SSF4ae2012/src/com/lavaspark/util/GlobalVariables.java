package com.lavaspark.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lavaspark.adapter.Move_attr;

import android.app.Application;

public class GlobalVariables extends Application {

	public ArrayList<String> nameList ;
	public ArrayList<String> frameKeyList ;
	public List<HashMap<String, String>> allFrameList;
	public ArrayList<Move_attr> arraymoveList;
	
	public String[] Gcharacters;
	
	public String[] getGcharacters() {
		return Gcharacters;
	}
	public void setGcharacters(String[] gcharacters) {
		Gcharacters = gcharacters;
	}
	public ArrayList<String> getNameList() {
		return nameList;
	}
	public void setNameList(ArrayList<String> nameList) {
		this.nameList = nameList;
	}
	public ArrayList<String> getFrameKeyList() {
		return frameKeyList;
	}
	public void setFrameKeyList(ArrayList<String> frameKeyList) {
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
