package dblp;

import java.util.*;

/**
 * @author vedant
 */
public class Person {
	
	private String authorname;
	public Person(String authorname){
		this.authorname=authorname;
	}
	public List<String> give_bucket(){
		for(int i=0;i<DBLP.same_names.size();i++){
			for(int j=0;j<DBLP.same_names.get(i).size();j++){
				if(authorname.toLowerCase().equals(DBLP.same_names.get(i).get(j).toLowerCase())){
					return DBLP.same_names.get(i);
				}
			}
		}
		ArrayList<String> err=new ArrayList<String>();
		err.add(authorname);
		return err;
	}
}
