/**
 * Authors:
 * Vedant Nanda 2015114
 * Arpan Mondal 2015132
 */
package dblp;

import java.util.*;

public class DBLP {
	static ArrayList<Publication> result_publications=new ArrayList<Publication>();
	static String author_to_search;
	static String title_to_search;
	static List<List<String>> same_names=new ArrayList<List<String>>();
	static HashMap<String,Integer> author_count=new HashMap<String,Integer>();
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    return true;
	}
	
	public static void main(String[] args) {
		GUI g = new GUI();
	}

}
