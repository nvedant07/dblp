/**
 * Authors:
 * Vedant Nanda 2015114
 * Arpan Mondal 2015132
 */
package dblp;

import java.util.*;
/** Prediction engine!
* Using Apache prediction library
*/
public class Query_3 {
	private HashMap<Integer,Integer> numbers=new HashMap<Integer,Integer>();
	//make a seperate parser for query3
	private int year;
	private float a;
	private float b;
	private int x;
	private int real_count=0;
	private int predicted;
	
	public Query_3(String query,int year){
		this.year=year;
		CustomParser p=new CustomParser("dblp.xml",query);
		for(Publication pub : DBLP.result_publications){
			if(pub.getYear()<year){
				if(numbers.containsKey(pub.getYear())){
					int value=numbers.get(pub.getYear());
					numbers.put(pub.getYear(), value+1);
				}
				else
					numbers.put(pub.getYear(), 1);
			}
			else if(pub.getYear()==year){
				real_count++;
			}
		}
		Set<Integer> s=numbers.keySet();
		int n=s.size();
		int sum_x=0;
		int sum_xy=0;
		int sum_y=0;
		int sum_xx=0;
		for(Integer num : s){
			sum_x+=num;
			sum_xx+=(num*num);
			sum_y+=numbers.get(num);
			sum_xy+=(numbers.get(num)*num);
		}
		a=(n*sum_xy - sum_x*sum_y)/(n*sum_xx-sum_x*sum_x);
		b=(sum_y-a*sum_x)/n;
		predicted=(int)(a+b*(year+1));
	}
	public int getPredicted(){
		return this.predicted;
	}
	public int getReal(){
		return this.real_count;
	}
}
