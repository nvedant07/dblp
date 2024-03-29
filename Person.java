/**
 * Authors:
 * Vedant Nanda 2015114
 * Arpan Mondal 2015132
 */
package dblp;

import java.util.*;

/** A class for keeping names of authors.
* Make a bucket list, Makes it easier for searching
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
