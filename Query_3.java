package dblp;

import java.util.*;
/** Prediction engine!
* Using Apache prediction library
*/
public class Query_3 {
	private ArrayList<String> authors=new ArrayList<String>();
	//make a seperate parser for query3
	private int year;
	public void add_author(String name){
		this.authors.add(name);
	}
	public void update_author(String name,int value){
		
	}
	public void setYear(int year){
		this.year=year;
	}
	public int getYear(){
		return this.year;
	}
}
