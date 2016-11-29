/**
 * Authors:
 * Vedant Nanda 2015114
 * Arpan Mondal 2015132
 */
package dblp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

public class GUI extends JFrame{
	private JFrame mainframe;
	private JComboBox<String> query_options;
	private JComboBox<String> q1_box;
	private JPanel menu;
	private JPanel canvas;
	private JPanel topPanel;
	private JPanel lowerPanel;
	private JLabel answer;
	private JButton prev;
	private JButton next;
	private String data[][]=new String[21][9];	
	private String column[]={"S.no","authors","title","pages","year","volume","journal","booktitle","url"};
	
	public GUI(){
		mainframe=new JFrame();
		
		topPanel = new JPanel(); //main panel
		menu = new JPanel(new GridBagLayout()); //left side of window		
		menu.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		menu.setBackground(Color.white);
		JLabel label1 = new JLabel("DBLP Query Engine", SwingConstants.CENTER);
		label1.setFont(new Font("Serif", Font.PLAIN, 32));
    	label1.setForeground(Color.BLACK);
		topPanel.add(label1, BorderLayout.PAGE_START);
		topPanel.setBackground(Color.RED);
		
		WwwParser w=new WwwParser("dblp.xml");
		
		canvas = new JPanel();
		canvas.setBackground(Color.YELLOW);
		
		lowerPanel=new JPanel();
		lowerPanel.setBackground(Color.CYAN);
		answer=new JLabel("Total results: ");
		prev=new JButton("Prev");
		next=new JButton("Next");
		prev.setBackground(Color.BLACK);
	    prev.setForeground(Color.WHITE);
	    next.setBackground(Color.BLACK);
	    next.setForeground(Color.WHITE);
		lowerPanel.add(prev);
		lowerPanel.add(answer);
		lowerPanel.add(next);
		
		String[] choice = { "Choose Query","Query 1", "Query 2", "Query 3"};
		this.query_options = new JComboBox<String>(choice);
		String[] q1_options = {"Choose option","Search by Author Name","Search by Title"};
		q1_box = new JComboBox<String>(q1_options);
		q1_box.setVisible(false);
		JTextField search_query=new JTextField(12);
		search_query.setVisible(false);
		JLabel helper_query=new JLabel("Enter Search Query");
		helper_query.setVisible(false);
		helper_query.setFont(new Font("Serif", Font.PLAIN, 18));
    	helper_query.setForeground(Color.BLACK);
		JTextField year=new JTextField(4);
		year.setVisible(false);
		JLabel helper_year=new JLabel("Since Year(leave blank for all years)");
		helper_year.setVisible(false);
		helper_year.setFont(new Font("Serif", Font.PLAIN, 18));
    	helper_year.setForeground(Color.BLACK);
		JTextField start_year=new JTextField(4);
		start_year.setVisible(false);
		JTextField end_year=new JTextField(4);
		end_year.setVisible(false);
		JLabel helper_range_year=new JLabel("Range(leave blank for all years)");
		helper_range_year.setFont(new Font("Serif", Font.PLAIN, 18));
    	helper_range_year.setForeground(Color.BLACK);
		helper_range_year.setVisible(false);
		JRadioButton sort_by_date=new JRadioButton("Sort By Date");
		sort_by_date.setSelected(true);
		sort_by_date.setVisible(false);
		JRadioButton sort_by_relevance=new JRadioButton("Sort By Relevance");
		sort_by_relevance.setSelected(false);
		sort_by_relevance.setVisible(false);
		ButtonGroup group = new ButtonGroup();
	    group.add(sort_by_relevance);
	    group.add(sort_by_date);
	    JLabel dash=new JLabel("to    ");
		dash.setFont(new Font("Serif", Font.PLAIN, 18));
    	dash.setForeground(Color.BLACK);
	    dash.setVisible(false);
	    JButton submit=new JButton("Submit");
	    submit.setEnabled(false);
	    submit.setBackground(Color.BLACK);
	    submit.setForeground(Color.WHITE);
	    JButton refresh=new JButton("Refresh");
	    refresh.setBackground(Color.BLACK);
	    refresh.setForeground(Color.WHITE);
	    JLabel helper_query2=new JLabel("Enter k");
		helper_query2.setFont(new Font("Serif", Font.PLAIN, 24));
    	helper_query2.setForeground(Color.BLACK);
	    helper_query2.setVisible(false);
	    JTextField query2=new JTextField(5);
	    query2.setVisible(false);
	    
	    refresh.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		year.setText("");
	    		start_year.setText("");
	    		end_year.setText("");
	    		search_query.setText("");
	    		sort_by_date.setSelected(true);
	    		query2.setText("");
	    	}
	    });
		this.query_options.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String choice=(String)query_options.getSelectedItem();
				if(choice.equals("Query 1")){
					q1_box.setVisible(true);
					query2.setVisible(false);
					helper_query2.setVisible(false);
				}
				else if(choice.equals("Query 2")){
					query2.setVisible(true);
					helper_query2.setVisible(true);
					submit.setEnabled(true);
					q1_box.setVisible(false);
					helper_query.setVisible(false);
					search_query.setVisible(false);
					helper_year.setVisible(false);
					year.setVisible(false);
					helper_range_year.setVisible(false);
					start_year.setVisible(false);
					end_year.setVisible(false);
					sort_by_date.setVisible(false);
					sort_by_relevance.setVisible(false);
					dash.setVisible(false);
				}
				else if(choice.equals("Query 3")){
					query2.setVisible(false);
					helper_query2.setVisible(false);
					submit.setEnabled(true);
					q1_box.setVisible(false);
					helper_query.setVisible(true);
					search_query.setVisible(true);
					helper_year.setVisible(true);
					year.setVisible(true);
					helper_range_year.setVisible(false);
					start_year.setVisible(false);
					end_year.setVisible(false);
					sort_by_date.setVisible(false);
					sort_by_relevance.setVisible(false);
					dash.setVisible(false);
				}
			}
		});
		this.q1_box.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String choice=(String)q1_box.getSelectedItem();
				if(choice.equals("Search by Author Name")){
					helper_query.setVisible(true);
					search_query.setVisible(true);
					helper_year.setVisible(true);
					year.setVisible(true);
					helper_range_year.setVisible(true);
					start_year.setVisible(true);
					end_year.setVisible(true);
					sort_by_date.setVisible(true);
					sort_by_date.setSelected(true);
					sort_by_relevance.setVisible(false);
					dash.setVisible(true);
					submit.setEnabled(true);
				}
				else if(choice.equals("Search by Title")){
					helper_query.setVisible(true);
					search_query.setVisible(true);
					helper_year.setVisible(true);
					year.setVisible(true);
					helper_range_year.setVisible(true);
					start_year.setVisible(true);
					end_year.setVisible(true);
					sort_by_date.setVisible(true);
					sort_by_relevance.setVisible(true);
					dash.setVisible(true);
					submit.setEnabled(true);
				}
			}
		});
		submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int flag=0;
				if(query_options.getSelectedItem().equals("Query 1")){
					Query_1 q = new Query_1();
					if(q1_box.getSelectedItem().equals("Search by Author Name")){
						q.setSearch_by_authorname(true);
						q.setSearch_by_title(false);
						if(year.getText().equals("")&& (start_year.getText().equals("")||end_year.getText().equals(""))){
							q.setSort_by_date(true);
							q.setBw_two_years(false);
							q.setSince_some_year(false);
						}
						else if(year.getText().equals("") && !start_year.getText().equals("") && !end_year.getText().equals("")){
							q.setBw_two_years(true);
							q.setSince_some_year(false);
							q.setSort_by_date(false);
							if(DBLP.isInteger(start_year.getText()) && DBLP.isInteger(end_year.getText())){
								q.setStart_year(Integer.parseInt(start_year.getText()));
								q.setEnd_year(Integer.parseInt(end_year.getText()));
							}
							else flag=1;
						}
						else if(!year.getText().equals("")){
							q.setBw_two_years(false);
							q.setSince_some_year(true);
							q.setSort_by_date(false);
							if(DBLP.isInteger(year.getText())){
								q.setYear(Integer.parseInt(year.getText()));
							}
							else flag=1;
						}
					}
					else if(q1_box.getSelectedItem().equals("Search by Title")){
						q.setSearch_by_authorname(false);
						q.setSearch_by_title(true);
						
						if(sort_by_date.isSelected()){
							q.setSort_by_date(true);
							q.setSort_by_relevance(false);
						}
						else{
							q.setSort_by_date(false);
							q.setSort_by_relevance(true);
						}
						if(year.getText().equals("")&& (start_year.getText().equals("")||end_year.getText().equals(""))){
							q.setBw_two_years(false);
							q.setSince_some_year(false);
						}
						else if(year.getText().equals("") && !start_year.getText().equals("") && !end_year.getText().equals("")){
							q.setBw_two_years(true);
							q.setSince_some_year(false);
							if(DBLP.isInteger(start_year.getText()) && DBLP.isInteger(end_year.getText())){
								q.setStart_year(Integer.parseInt(start_year.getText()));
								q.setEnd_year(Integer.parseInt(end_year.getText()));
							}
							else flag=1;
						}
						else if(!year.getText().equals("")){
							q.setBw_two_years(false);
							q.setSince_some_year(true);
							if(DBLP.isInteger(year.getText())){
								q.setYear(Integer.parseInt(year.getText()));
							}
							else flag=1;
						}
					}
					if(search_query.getText().equals("")){
						JOptionPane.showMessageDialog(null,"Please enter a search query");
					}
					else if(flag==1){
						JOptionPane.showMessageDialog(null,"Please enter an integer where expected");
					}
					else{
						q.return_query(search_query.getText());
						prev.setEnabled(false);
						if(DBLP.result_publications.size()>20)
						next.setEnabled(true);
						query_1();
					}
				}
				else if(query_options.getSelectedItem().equals("Query 2")){
					if(query2.getText().equals("")){
						JOptionPane.showMessageDialog(null,"Please enter a search query");
					}
					else if(!DBLP.isInteger(query2.getText())){
						JOptionPane.showMessageDialog(null,"Please enter an integer where expected");
					}
					else{
						Query_2 q=new Query_2(Integer.parseInt(query2.getText()));
						query_2(q);
					}
				}
				else if(query_options.getSelectedItem().equals("Query 3")){
					if(search_query.getText().equals("")){
						JOptionPane.showMessageDialog(null,"Please enter a search query");
					}
					else if(!DBLP.isInteger(year.getText())){
						JOptionPane.showMessageDialog(null,"Please enter an integer where expected");
					}
					else{
						DBLP.author_to_search=search_query.getText();
						Query_3 q=new Query_3(search_query.getText(),Integer.parseInt(year.getText()));
						canvas.removeAll();
						canvas.repaint();
						canvas.revalidate();
						JLabel predicted=new JLabel("Predicted Value: "+Integer.toString(q.getPredicted()));
						JLabel real=new JLabel("Real Value: "+Integer.toString(q.getReal()));
						canvas.add(predicted);
						canvas.add(real);
					}
				}
			}
		});
		GridBagConstraints c=new GridBagConstraints();
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=0;
		c.gridy=0;
		menu.add(query_options,c);
		c=new GridBagConstraints();
		c.fill=GridBagConstraints.NONE;
		c.gridx=1;
		c.gridy=0;
		c.weighty=0.1;
		menu.add(q1_box,c);
		c=new GridBagConstraints();
		c.fill=GridBagConstraints.NONE;
		c.gridx=0;
		c.gridy=1;
		c.weighty=0.1;
		menu.add(helper_query,c);
		c=new GridBagConstraints();
		c.fill=GridBagConstraints.NONE;
		c.gridx=1;
		c.gridy=1;
		c.weighty=0.1;
		menu.add(search_query,c);
		c=new GridBagConstraints();
		c.fill=GridBagConstraints.NONE;
		c.gridx=0;
		c.gridy=2;
		c.weighty=0.1;
		menu.add(new JLabel("  "),c);
		c=new GridBagConstraints();
		c.fill=GridBagConstraints.NONE;
		c.gridx=0;
		c.gridy=3;
		c.weighty=0.1;
		menu.add(helper_year,c);
		c=new GridBagConstraints();
		c.fill=GridBagConstraints.NONE;
		c.gridx=1;
		c.gridy=3;
		c.weighty=0.1;
		menu.add(year,c);
		c=new GridBagConstraints();
		c.fill=GridBagConstraints.NONE;
		c.gridx=0;
		c.gridy=4;
		c.weighty=0.1;
		menu.add(new JLabel("  "),c);
		c=new GridBagConstraints();
		c.fill=GridBagConstraints.NONE;
		c.gridx=0;
		c.gridy=5;
		c.weighty=0.1;
		menu.add(helper_range_year,c);
		c=new GridBagConstraints();
		c.fill=GridBagConstraints.NONE;
		c.gridx=1;
		c.gridy=5;
		c.weighty=0.1;
		menu.add(start_year,c);
		c=new GridBagConstraints();
		c.fill=GridBagConstraints.NONE;
		c.gridx=2;
		c.gridy=5;
		c.weighty=0.1;
		menu.add(dash,c);
		c=new GridBagConstraints();
		c.fill=GridBagConstraints.NONE;
		c.gridx=3;
		c.gridy=5;
		c.weighty=0.1;
		menu.add(end_year,c);
		c=new GridBagConstraints();
		c.fill=GridBagConstraints.NONE;
		c.gridx=0;
		c.gridy=6;
		c.weighty=0.1;
		menu.add(sort_by_date,c);
		c=new GridBagConstraints();
		c.fill=GridBagConstraints.NONE;
		c.gridx=0;
		c.gridy=7;
		c.weighty=0.1;
		menu.add(sort_by_relevance,c);
		c=new GridBagConstraints();
		c.fill=GridBagConstraints.NONE;
		c.gridx=1;
		c.gridy=7;
		c.weighty=0.1;
		menu.add(helper_query2,c);
		c=new GridBagConstraints();
		c.fill=GridBagConstraints.NONE;
		c.gridx=2;
		c.gridy=7;
		c.weighty=0.1;
		menu.add(query2,c);
		c=new GridBagConstraints();
		c.fill=GridBagConstraints.NONE;
		c.gridx=0;
		c.gridy=8;
		c.weighty=0.1;
		menu.add(submit,c);
		c=new GridBagConstraints();
		c.fill=GridBagConstraints.NONE;
		c.gridx=1;
		c.gridy=8;
		c.weighty=0.1;
		menu.add(refresh,c);
		mainframe.add(menu,BorderLayout.WEST);
		mainframe.add(topPanel, BorderLayout.NORTH);
		mainframe.add(canvas, BorderLayout.EAST);
		mainframe.add(lowerPanel, BorderLayout.SOUTH);
		JPanel placeholder=new JPanel();
		placeholder.setBackground(Color.white);
		mainframe.add(placeholder, BorderLayout.CENTER);
//		mainframe.add(startPanel);
		mainframe.setSize(1300,900);
		mainframe.setLocationRelativeTo(null);
		mainframe.setResizable(false);
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setTitle("AP Project :: DBLP search engine");
		mainframe.setVisible(true);
	}
	public void query_1(){
		
		for(int i=0;i<21;i++){
			for(int j=0;j<9;j++){
				data[i][j]=null;
			}
		}

		answer.setText("Number of Results: "+Integer.toString(DBLP.result_publications.size()));
		int start=0,end;
		if(start+20>=DBLP.result_publications.size()){
			end=DBLP.result_publications.size();
			next.setEnabled(false);
		}
		else end=start+20;
		
		display_q1(start,end,0);
		for(ActionListener l:next.getActionListeners()){
			next.removeActionListener(l);
		}
		next.addActionListener(new ActionListener(){
//			int counter=count;
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
				if(s+20>=DBLP.result_publications.size()){
					l=DBLP.result_publications.size();
					next.setEnabled(false);
				}
				else l=s+20;
				prev.setEnabled(true);
				display_q1(s,l,counter);
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
				display_q1(s,l,counter);
			}
		});		
	}
	public void display_q1(int start,int end,int count){
		canvas.removeAll();
		canvas.repaint();
		canvas.revalidate();
		
		String temp[]={"S.no","authors","title","pages","year","volume","journal","booktitle","url"};
		data[0]=temp;
		for(int i=start;i<start+20;i++){
			data[i+1-(20*count)][0]="";data[i+1-(20*count)][1]="";data[i+1-(20*count)][2]="";data[i+1-(20*count)][3]="";data[i+1-(20*count)][4]="";data[i+1-(20*count)][5]="";data[i+1-(20*count)][6]="";data[i+1-(20*count)][7]="";data[i+1-(20*count)][8]="";
		}
		for(int i=start;i<end;i++){
			data[i+1-(20*count)][0]=(i+1)+"";
			data[i+1-(20*count)][1]=DBLP.result_publications.get(i).getAuthors().toString();
			if(DBLP.result_publications.get(i).getTitle()!=null){
				data[i+1-(20*count)][2]=DBLP.result_publications.get(i).getTitle();
			}
			else{
				data[i+1-(20*count)][2]="NO TITLE";
			}
			if(DBLP.result_publications.get(i).getPages()!=null){
				data[i+1-(20*count)][3]=DBLP.result_publications.get(i).getPages();
			}
			else{
				data[i+1-(20*count)][3]="NO PAGES";
			}
			if(DBLP.result_publications.get(i).getYear()!=0){
				data[i+1-(20*count)][4]=Integer.toString(DBLP.result_publications.get(i).getYear());
			}
			else{
				data[i+1-(20*count)][4]="NO YEAR";
			}
			if(DBLP.result_publications.get(i).getVolume()!=null){
				data[i+1-(20*count)][5]=DBLP.result_publications.get(i).getVolume();
			}
			else{
				data[i+1-(20*count)][5]="NO VOLUME";
			}
			if(DBLP.result_publications.get(i).getJournal()!=null){
				data[i+1-(20*count)][6]=DBLP.result_publications.get(i).getJournal();
			}
			else{
				data[i+1-(20*count)][6]="NO JOURNAL";
			}
			if(DBLP.result_publications.get(i).getBooktitle()!=null){
				data[i+1-(20*count)][7]=DBLP.result_publications.get(i).getBooktitle();
			}
			else{
				data[i+1-(20*count)][7]="NO BOOKTITLE";
			}
			if(DBLP.result_publications.get(i).getUrl()!=null){
				data[i+1-(20*count)][8]=DBLP.result_publications.get(i).getUrl();
			}
			else{
				data[i+1-(20*count)][8]="NO URL";
			}
		}
		JTable jt=new JTable(data,column);
		//
		jt.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            String sno="S.No."+(String)jt.getValueAt(jt.getSelectedRow(), 0)+"\n";
	        	String authors="Authors: "+(String)jt.getValueAt(jt.getSelectedRow(), 1)+"\n";
	        	String title="Title: "+(String)jt.getValueAt(jt.getSelectedRow(), 2)+"\n";
	        	String pages="Pages: "+(String)jt.getValueAt(jt.getSelectedRow(), 3)+"\n";
	        	String year="Year: "+(String)jt.getValueAt(jt.getSelectedRow(), 4)+"\n";
	        	String volume="Volume: "+(String)jt.getValueAt(jt.getSelectedRow(), 5)+"\n";
	        	String journal="Journal: "+(String)jt.getValueAt(jt.getSelectedRow(), 6)+"\n";
	        	String booktitle="BookTitle: "+(String)jt.getValueAt(jt.getSelectedRow(), 7)+"\n";
	        	String url="Url: "+(String)jt.getValueAt(jt.getSelectedRow(), 8)+"\n";
	        	JOptionPane.showMessageDialog(null, sno+authors+title+pages+year+volume+journal+booktitle+url);
	        }
	    });
		//
		TableColumn column = null;
		jt.setRowHeight(35);
		for (int i = 0; i < 9; i++) {
		    column = jt.getColumnModel().getColumn(i);
		    if ( i==1) {
		        column.setPreferredWidth(150); //third column is bigger
		    }
		    else if(i==2){
		    	column.setPreferredWidth(100);
		    }
		    else {
		        column.setPreferredWidth(50);
		    }
		}
		if(DBLP.result_publications.size()>0)
	    canvas.add(jt);
		else{
			JLabel nf=new JLabel("NO RESULT FOUND!");
			nf.setFont(new Font("Serif", Font.PLAIN, 32));
			canvas.add(nf);
		}
	}
	public void query_2(Query_2 q){
		canvas.removeAll();
		canvas.repaint();
		canvas.revalidate();
		q.print_result(canvas,next,prev,answer);
	}
	public void query_3(){
		
	}
}
