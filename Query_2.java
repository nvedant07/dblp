package dblp;

import java.util.*;

public class Query_2 {
	private int k;
	private HashMap<String,Integer> resolved_count=new HashMap<String,Integer>();
	
	public void resolve(){
		for(int i=0;i<DBLP.same_names.size();i++){
			int new_count=0;
			for(int j=0;j<DBLP.same_names.get(i).size();j++){
				if(DBLP.author_count.containsKey(DBLP.same_names.get(i).get(j)))
					new_count+=DBLP.author_count.get(DBLP.same_names.get(i).get(j));
					if(j!=0){
						DBLP.author_count.replace(DBLP.same_names.get(i).get(j), 0);
					}
			}
			resolved_count.put(DBLP.same_names.get(i).get(0), new_count);
		}
	}
	public void print_result(){
		Iterator iter=resolved_count.keySet().iterator();
		while(iter.hasNext()){
			String key=(String)iter.next();
			if(resolved_count.get(key)>=k){
				System.out.println(key);
			}
		}
		resolved_count.clear();
	}
	public Query_2(int k){
		this.k=k;
		AuthorParser par=new AuthorParser("dblp.xml");
		//int c=DBLP.author_count.get("Chin-Chen Chang");
		//System.out.println(c);
		this.resolve();
		//System.out.println(this.resolved_count.get("Chin-Chen Chang"));
	}
	
}
