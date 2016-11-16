package dblp;

import java.util.*;

public class Query_1 {
	private boolean sort_by_date;
	private boolean sort_by_relevance;
	private boolean search_by_title;
	private boolean search_by_authorname;
	private boolean since_some_year;
	private int year;
	private boolean bw_two_years;
	private int start_year;
	private int end_year;
	public boolean isSort_by_date() {
		return sort_by_date;
	}
	public void setSort_by_date(boolean sort_by_date) {
		this.sort_by_date = sort_by_date;
	}
	public boolean isSort_by_relevance() {
		return sort_by_relevance;
	}
	public void setSort_by_relevance(boolean sort_by_relevance) {
		this.sort_by_relevance = sort_by_relevance;
	}
	public boolean isSearch_by_title() {
		return search_by_title;
	}
	public void setSearch_by_title(boolean search_by_title) {
		this.search_by_title = search_by_title;
	}
	public boolean isSearch_by_authorname() {
		return search_by_authorname;
	}
	public void setSearch_by_authorname(boolean search_by_authorname) {
		this.search_by_authorname = search_by_authorname;
	}
	public boolean isSince_some_year() {
		return since_some_year;
	}
	public void setSince_some_year(boolean since_some_year) {
		this.since_some_year = since_some_year;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public boolean isBw_two_years() {
		return bw_two_years;
	}
	public void setBw_two_years(boolean bw_two_years) {
		this.bw_two_years = bw_two_years;
	}
	public int getStart_year() {
		return start_year;
	}
	public void setStart_year(int start_year) {
		this.start_year = start_year;
	}
	public int getEnd_year() {
		return end_year;
	}
	public void setEnd_year(int end_year) {
		this.end_year = end_year;
	}
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
	public void return_query(){
		Scanner in=new Scanner(System.in);
		if(this.search_by_authorname){
			System.out.println("Enter author name");
			String author=in.nextLine();
			DBLP.author_to_search=author;
			CustomParser p = new CustomParser("dblp.xml",author);
		}
		else if(this.search_by_title){
			System.out.println("Enter title");
			String title=in.nextLine();
			DBLP.title_to_search=title;
			TitleParser t=new TitleParser("dblp.xml",title);
		}
		
		if(!this.sort_by_relevance){
			Collections.sort(DBLP.result_publications,new Comparator<Publication>(){
				public int compare(Publication p1,Publication p2){
					return p2.getYear()-p1.getYear();
				}
			});
		}
		if(this.since_some_year){
			for(int i=0;i<DBLP.result_publications.size();i++){
				if(DBLP.result_publications.get(i).getYear()<this.year){
					DBLP.result_publications.remove(i);
					i--;
				}
			}
		}
		else if(this.bw_two_years){
			for(int i=0;i<DBLP.result_publications.size();i++){
				if(DBLP.result_publications.get(i).getYear()<this.start_year || DBLP.result_publications.get(i).getYear()>this.end_year){
					DBLP.result_publications.remove(i);
					i--;
				}
			}
		}
		for(int i=0;i<DBLP.result_publications.size();i++){
			System.out.print((i+1)+" | ");
			this.print_authors(DBLP.result_publications.get(i).getAuthors());
			if(DBLP.result_publications.get(i).getTitle()!=null){
				System.out.print(" | "+DBLP.result_publications.get(i).getTitle());
			}
			else{
				System.out.print(" | NO TITLE");
			}
			if(DBLP.result_publications.get(i).getPages()!=null){
				System.out.print(" | "+DBLP.result_publications.get(i).getPages());
			}
			else{
				System.out.print(" | NO PAGES");
			}
			if(DBLP.result_publications.get(i).getYear()!=0){
				System.out.print(" | "+DBLP.result_publications.get(i).getYear());
			}
			else{
				System.out.print(" | NO YEAR");
			}
			if(DBLP.result_publications.get(i).getVolume()!=null){
				System.out.print(" | "+DBLP.result_publications.get(i).getVolume());
			}
			else{
				System.out.print(" | NO VOLUME");
			}
			if(DBLP.result_publications.get(i).getJournal()!=null){
				System.out.print(" | "+DBLP.result_publications.get(i).getJournal());
			}
			else{
				System.out.print(" | NO JOURNAL");
			}
			if(DBLP.result_publications.get(i).getBooktitle()!=null){
				System.out.print(" | "+DBLP.result_publications.get(i).getBooktitle());
			}
			else{
				System.out.print(" | NO BOOKTITLE");
			}
			if(DBLP.result_publications.get(i).getUrl()!=null){
				System.out.println(" | "+DBLP.result_publications.get(i).getUrl());
			}
			else{
				System.out.println(" | NO URL");
			}	
		}
	}
}
