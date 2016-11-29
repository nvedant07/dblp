/**
 * Authors:
 * Vedant Nanda 2015114
 * Arpan Mondal 2015132
 */
package dblp;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.TableColumn;
/** Query 2, display all authors with more than desired number of publication
* Hashmap is used, by soring authours with no. of published papers
*/
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
	public void print_result(JPanel canvas,JButton next,JButton prev,JLabel ans){
		ArrayList <String> answer=new ArrayList<String>();
		Iterator iter=resolved_count.keySet().iterator();
		while(iter.hasNext()){
			String key=(String)iter.next();
			if(resolved_count.get(key)>=k){
				answer.add(key);
			}
		}
		resolved_count.clear();
		ans.setText("Total Authors: "+answer.size());
		String data[][]=new String[21][2];
		String column[]={"S.No.","Author Name"};
		//print first page
		int end=20;
		if(end>=answer.size()){
			end=answer.size();
			next.setEnabled(false);
		}
		
		String temp[]={"S.no","Author Name"};
		data[0]=temp;
		for(int i=0;i<20;i++){
			data[i+1][0]="";data[i+1][1]="";
		}
		for(int i=0;i<end;i++){
			data[i+1][0]=(i+1)+"";
			data[i+1][1]=answer.get(i);
		}
		for(ActionListener l:next.getActionListeners()){
			next.removeActionListener(l);
		}
		next.addActionListener(new ActionListener(){
			public int get_count(){
				int num=0;
				for(int i=1;i<21;i++){
					if(data[i][0].equals("")||data[i][0]==null){
						num=Integer.parseInt(data[i-1][0]);
						break;
					}
				}
				if(num==0)num=Integer.parseInt(data[20][0]);
				if(num % 20==0)return (num/20 - 1);
				else return num/20;
			}
			public void actionPerformed(ActionEvent e){
				int counter=get_count();
				canvas.removeAll();
				canvas.repaint();
				canvas.revalidate();
				counter++;
				System.out.println(counter);
				int s=counter*20,l;
				if(s+20>=answer.size()){
					l=answer.size();
					next.setEnabled(false);
				}
				else l=s+20;
				prev.setEnabled(true);
				String temp[]={"S.no","Author Name"};
				data[0]=temp;
				for(int i=s;i<s+20;i++){
					data[i+1-(20*counter)][0]="";data[i+1-(20*counter)][1]="";
				}
				for(int i=s;i<l;i++){
					data[i+1-(20*counter)][0]=(i+1)+"";
					data[i+1-(20*counter)][1]=answer.get(i);
				}
				JTable jt=new JTable(data,column);
				jt.setRowHeight(35);
				TableColumn col = null;
				for (int i = 0; i < 2; i++) {
				    col = jt.getColumnModel().getColumn(i);
				    if ( i==1) {
				        col.setPreferredWidth(200); //third column is bigger
				    }
				    else {
				        col.setPreferredWidth(50);
				    }
				}
				canvas.add(jt);
			}
		});
		for(ActionListener l:prev.getActionListeners()){
			prev.removeActionListener(l);
		}
		prev.addActionListener(new ActionListener(){
			public int get_count(){
				int num=0;
				for(int i=1;i<21;i++){
					if(data[i][0].equals("")||data[i][0]==null){
						num=Integer.parseInt(data[i-1][0]);
						break;
					}
				}
				if(num==0)num=Integer.parseInt(data[20][0]);
				if(num % 20==0)return (num/20 - 1);
				else return num/20;
			}
			public void actionPerformed(ActionEvent e){
				int counter=get_count();
				canvas.removeAll();
				canvas.repaint();
				canvas.revalidate();
				counter--;
				System.out.println(counter);
				int s=counter*20,l=s+20;
				if(s==0){
					prev.setEnabled(false);
				}
				next.setEnabled(true);
				String temp[]={"S.no","Author Name"};
				data[0]=temp;
				for(int i=s;i<s+20;i++){
					data[i+1-(20*counter)][0]="";data[i+1-(20*counter)][1]="";
				}
				for(int i=s;i<l;i++){
					data[i+1-(20*counter)][0]=(i+1)+"";
					data[i+1-(20*counter)][1]=answer.get(i);
				}
				JTable jt=new JTable(data,column);
				jt.setRowHeight(35);  
				TableColumn col = null;
				for (int i = 0; i < 2; i++) {
				    col = jt.getColumnModel().getColumn(i);
				    if ( i==1) {
				        col.setPreferredWidth(200); //third column is bigger
				    }
				    else {
				        col.setPreferredWidth(50);
				    }
				}
				canvas.add(jt);
			}
		});
		JTable jt=new JTable(data,column);
		jt.setRowHeight(35);
		TableColumn col = null;
		for (int i = 0; i < 2; i++) {
		    col = jt.getColumnModel().getColumn(i);
		    if ( i==1) {
		        col.setPreferredWidth(200); //third column is bigger
		    }
		    else {
		        col.setPreferredWidth(50);
		    }
		}
		if(answer.size()>0)
		canvas.add(jt);
		else{
			JLabel nf=new JLabel("NO RESULT FOUND!");
			nf.setFont(new Font("Serif", Font.PLAIN, 32));
			canvas.add(nf);
		}
	}
	public Query_2(int k){
		this.k=k;
		AuthorParser par=new AuthorParser("dblp.xml");
		this.resolve();
	}
}
