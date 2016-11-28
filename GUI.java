package dblp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame{
	private JFrame mainframe;
	private JComboBox<String> query_options;
	private JComboBox<String> q1_box;
	private JPanel menu;
	private JPanel canvas;
	private JPanel startPanel;
	
	public GUI(){
		mainframe=new JFrame();
		
		startPanel = new JPanel(new BorderLayout(10,10)); //main panel
//		JPanel waiting=new JPanel();
//		waiting.setLayout(new GridBagLayout());
//		waiting.add(new JLabel("Please wait while data loads..."));
//		startPanel.add(waiting,BorderLayout.LINE_END);
		menu = new JPanel(); //left side of window		
		menu.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		startPanel.add(menu, BorderLayout.LINE_START);
		JLabel label1 = new JLabel("DBLP Query Engine", SwingConstants.CENTER);
		label1.setFont(new Font("Serif", Font.PLAIN, 32));
    	label1.setForeground(Color.GRAY);
		startPanel.add(label1, BorderLayout.PAGE_START);
		
//		WwwParser w=new WwwParser("dblp.xml");
		
		canvas = new JPanel();
		canvas.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		canvas.setLayout(new GridLayout(8,21));
		startPanel.add(canvas, BorderLayout.LINE_END);
		
		String[] choice = { "Choose Query","Query 1", "Query 2", "Query 3"};
		this.query_options = new JComboBox<String>(choice);
		String[] q1_options = {"Search by Author Name","Search by Title"};
		q1_box = new JComboBox<String>(q1_options);
		q1_box.setVisible(false);
		JTextField search_query=new JTextField();
		search_query.setVisible(false);
		JLabel helper_query=new JLabel("Enter Search Query");
		helper_query.setVisible(false);
		JTextField year=new JTextField();
		year.setVisible(false);
		JLabel helper_year=new JLabel("Since Year(leave blank for all years)");
		helper_year.setVisible(false);
		JTextField start_year=new JTextField();
		start_year.setVisible(false);
		JTextField end_year=new JTextField();
		end_year.setVisible(false);
		JLabel helper_range_year=new JLabel("Range(leave blank for all years)");
		helper_range_year.setVisible(false);

		this.query_options.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String choice=(String)query_options.getSelectedItem();
				if(choice.equals("Query 1")){
					q1_box.setVisible(true);
					helper_query.setVisible(true);
					search_query.setVisible(true);
				}
			}
		});
		menu.add(query_options);
		menu.add(q1_box);
		menu.add(helper_query);
		menu.add(search_query);
		menu.add(helper_year);
		menu.add(year);
		menu.add(helper_range_year);
		menu.add(start_year);
		menu.add(new JLabel("-"));
		menu.add(end_year);
		mainframe.add(startPanel);
		mainframe.setSize(1200,900);
		mainframe.setLocationRelativeTo(null);
		mainframe.setResizable(false);
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setTitle("AP Project :: DBLP search engine");
		mainframe.setVisible(true);
	}
	public void query_1(){
		Query_1 q=new Query_1();		
	}
	public void query_2(int k){
		Query_2 q=new Query_2(k);
	}
	public void query_3(){
		
	}
}
