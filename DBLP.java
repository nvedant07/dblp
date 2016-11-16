package dblp;

import java.util.*;

public class DBLP {
	static ArrayList<Publication> result_publications=new ArrayList<Publication>();
	static String author_to_search;
	static List<List<String>> same_names=new ArrayList<List<String>>();
	
	public static void main(String[] args) {
		//parse www records
		WwwParser www=new WwwParser("dblp.xml");
		//to be moved to GUI
		Scanner in=new Scanner(System.in);
		while(true){
			System.out.print("Choose an option:\n1.Query1\n2.Query2\n3.Query3\n4.exit\n");
			int opt=in.nextInt();
			if(opt==4)break;
			else if(opt==1){
				Query_1 q=new Query_1();
				System.out.print("1.Search by authorname\n2.Search by title\n");
				opt=in.nextInt();
				if(opt==1){
					q.setSearch_by_authorname(true);
					q.setSearch_by_title(false);
					System.out.print("1.Sort by date\n2.Since some year\n3.Between two years\n");
					opt=in.nextInt();
					if(opt==1){
						q.setSort_by_date(true);
						q.setBw_two_years(false);
						q.setSince_some_year(false);
					}
					else if(opt==2){
						q.setSince_some_year(true);
						q.setSort_by_date(false);
						q.setBw_two_years(false);
					}
					else if(opt==3){
						q.setBw_two_years(true);
						q.setSince_some_year(false);
						q.setSort_by_date(false);
					}
					
				}
				q.return_query();
			}
		}
	}

}
