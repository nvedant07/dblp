package dblp;

import java.util.*;

public class DBLP {
	static ArrayList<Publication> result_publications=new ArrayList<Publication>();
	static String author_to_search;
	
	public void print_authors(ArrayList<String> arr){
		for(int j=0;j<arr.size();j++){
			if(j==arr.size()-1){
				System.out.print(arr.get(j));
			}
			else{
				System.out.print(arr.get(j)+",");
			}
			
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Parser p = new Parser("dblp.xml");
		Scanner in=new Scanner(System.in);
		System.out.println("Enter author name");
		String author=in.nextLine();
		author_to_search=author;
//		System.out.println(author_to_search);
		CustomParser p = new CustomParser("dblp.xml",author);
		DBLP dblp=new DBLP();
		for(int i=0;i<result_publications.size();i++){
			System.out.print((i+1)+" | ");
			dblp.print_authors(result_publications.get(i).getAuthors());
			if(result_publications.get(i).getTitle()!=null){
				System.out.print(" | "+result_publications.get(i).getTitle());
			}
			else{
				System.out.print(" | NO TITLE");
			}
			if(result_publications.get(i).getPages()!=null){
				System.out.print(" | "+result_publications.get(i).getPages());
			}
			else{
				System.out.print(" | NO PAGES");
			}
			if(result_publications.get(i).getYear()!=0){
				System.out.print(" | "+result_publications.get(i).getYear());
			}
			else{
				System.out.print(" | NO YEAR");
			}
			if(result_publications.get(i).getVolume()!=null){
				System.out.print(" | "+result_publications.get(i).getVolume());
			}
			else{
				System.out.print(" | NO VOLUME");
			}
			if(result_publications.get(i).getJournal()!=null){
				System.out.print(" | "+result_publications.get(i).getJournal());
			}
			else{
				System.out.print(" | NO JOURNAL");
			}
			if(result_publications.get(i).getBooktitle()!=null){
				System.out.print(" | "+result_publications.get(i).getBooktitle());
			}
			else{
				System.out.print(" | NO BOOKTITLE");
			}
			if(result_publications.get(i).getUrl()!=null){
				System.out.println(" | "+result_publications.get(i).getUrl());
			}
			else{
				System.out.println(" | NO URL");
			}
		}
	}

}
