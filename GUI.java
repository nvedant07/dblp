//package dblp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame{
	
	public GUI(){
		this.setSize(640,480);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		JPanel startPanel = new JPanel();
		BorderLayout layout = new BorderLayout();
		layout.setHgap(10);
		layout.setVgap(10);
		startPanel.setLayout(layout);
		JPanel canvas = new JPanel();
		startPanel.add(canvas, BorderLayout.LINE_END);
		JPanel menu = new JPanel();
		startPanel.add(menu, BorderLayout.LINE_START);
		JLabel label1 = new JLabel("DBLP Query Engine", SwingConstants.CENTER);
		label1.setFont(new Font("Serif", Font.PLAIN, 32));
    	label1.setForeground(Color.GRAY);
		startPanel.add(label1, BorderLayout.PAGE_START);
		JPanel play;
		String[] choice = { "Query 1", "Query 2", "Query 3"};
		JComboBox<String> choiceList = new JComboBox<String>(choice);
		menu.add(choiceList);
		this.add(startPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("VedArpan Project");
		this.setVisible(true);
	}
    
    public static void main(String[] args){
    	GUI g = new GUI();
    }
}