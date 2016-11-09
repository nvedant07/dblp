//package dblp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame{
	
	public GUI(){
		this.setSize(640,480);
		this.setLocationRelativeTo(null);
		//Toolkit tk = Toolkit.getDefaultToolkit();
		this.setResizable(false);
		JPanel startPanel = new JPanel(new GridBagLayout());
		startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.PAGE_AXIS));
		JLabel label1 = new JLabel("DBLP Query Engine");
		label1.setFont(new Font("Serif", Font.PLAIN, 32));
    	label1.setForeground(Color.GRAY);
		label1.setAlignmentX(startPanel.CENTER_ALIGNMENT);
		//label1.setText("Tic Tac Toe");
		startPanel.add(label1);
		JButton button1 = new JButton("Start Game");
		button1.setAlignmentX(startPanel.CENTER_ALIGNMENT);
		startPanel.add(button1);
		JButton button2 = new JButton("Exit");
		button2.setAlignmentX(startPanel.CENTER_ALIGNMENT);
		startPanel.add(button2);
		String[] choice = { "Query 1", "Query 2", "Query 3"};
		JComboBox choiceList = new JComboBox(choice);
		this.add(startPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("VedArpan Project");
		this.setVisible(true);
	}
    
    public static void main(String[] args){
    	GUI g = new GUI();
    }
}