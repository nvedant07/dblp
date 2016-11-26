package dblp;

import java.util.*;

public class Publication {
//    static String author_to_search;
    private String title;
    private String pages;
    private int year;
    private String volume;
    private String booktitle;
    private String journal;
    private String url;
    private ArrayList<String> authors;
    private float matchRatio;
    
    public Publication(ArrayList<String> authors) {
        this.authors=new ArrayList<String>();
    	for(int i=0;i<authors.size();i++){
    		this.authors.add(authors.get(i));
    	}
    }
    public float getMatch_ratio(){
    	return this.matchRatio;
    }
    public void setMatch_ratio(float f){
    	this.matchRatio=f;
    }
    public ArrayList<String> getAuthors(){
    	return this.authors;
    }
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getBooktitle() {
		return booktitle;
	}

	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}

	public String getJournal() {
		return journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
    
}
