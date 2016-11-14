package dblp;

import java.util.*;

/**
 * @author ley
 */
public class Person {
	
	static ArrayList<ArrayList<String>> same_names=new ArrayList<ArrayList<String>>();
	
	private String authorname;
//	private ArrayList
	public Person(String authorname){
		this.authorname=authorname;
	}
	public ArrayList<String> give_bucket(){
		for(int i=0;i<same_names.size();i++){
			for(int j=0;j<same_names.get(i).size();j++){
				if(authorname.toLowerCase().equals(same_names.get(i).get(j).toLowerCase())){
					return same_names.get(i);
				}
			}
		}
		ArrayList<String> err=new ArrayList<String>();
		err.add(authorname);
		return err;
	}
}
