package dblp;

import java.util.*;

public class DBLP {
	static ArrayList<Publication> result_publications=new ArrayList<Publication>();
	static String author_to_search;
	static String title_to_search;
	static List<List<String>> same_names=new ArrayList<List<String>>();
	static HashMap<String,Integer> author_count=new HashMap<String,Integer>();
	
	public static void main(String[] args) {
		//parse www records
		System.out.println("Loading Data...");
		WwwParser www=new WwwParser("dblp.xml");
		AuthorParser par=new AuthorParser("dblp.xml");
		System.out.println("Data successfully loaded!");
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
						System.out.print("Enter year\n");
						int year=in.nextInt();
						q.setYear(year);
					}
					else if(opt==3){
						q.setBw_two_years(true);
						q.setSince_some_year(false);
						q.setSort_by_date(false);
						System.out.print("Enter start year\n");
						int start_year=in.nextInt();
						q.setStart_year(start_year);
						System.out.print("Enter end year\n");
						int end_year=in.nextInt();
						q.setEnd_year(end_year);
					}
				}
				else if(opt==2){
					q.setSearch_by_authorname(false);
					q.setSearch_by_title(true);
					System.out.print("1.Sort by date\n2.Sort by relevance\n3.Since some year\n4.Between two years\n");
					opt=in.nextInt();
					if(opt==1){
						q.setSort_by_date(true);
						q.setSort_by_relevance(false);
						q.setBw_two_years(false);
						q.setSince_some_year(false);
					}
					else if(opt==2){
						q.setSort_by_date(false);
						q.setSort_by_relevance(true);
						q.setBw_two_years(false);
						q.setSince_some_year(false);
					}
					else if(opt==3){
						q.setSince_some_year(true);
						q.setSort_by_date(false);
						q.setBw_two_years(false);
						System.out.print("Enter year\n");
						int year=in.nextInt();
						q.setYear(year);
					}
					else if(opt==4){
						q.setBw_two_years(true);
						q.setSince_some_year(false);
						q.setSort_by_date(false);
						System.out.print("Enter start year\n");
						int start_year=in.nextInt();
						q.setStart_year(start_year);
						System.out.print("Enter end year\n");
						int end_year=in.nextInt();
						q.setEnd_year(end_year);
					}
				}
				q.return_query();
			}
			else if(opt==2){
				System.out.println("Enter k:");
				int k=in.nextInt();
				Query_2 q=new Query_2(k);
				q.print_result();
			}
		}
	}

}
