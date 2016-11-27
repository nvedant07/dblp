package dblp;

public class Query_3 {
	private HashMap<String,ArrayList<Integer>> authors=new HashMap<String,ArrayList<Integer>>();
	//make a seperate parser for query3

	private int year;
	public add_author(String name){
		this.authors.add(name);
	}
	public update_author(String name,int value){
		
	}
	public setYear(int year){
		this.year=year;
	}
	public int getYear(){
		return this.year;
	}
}
