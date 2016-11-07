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
		JPanel startPanel = new JPanel();
		startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.PAGE_AXIS));
		JLabel label1 = new JLabel("DBLP Query Engine");
		label1.setFont(new Font("Serif", Font.PLAIN, 14));
    	label1.setForeground(Color.GRAY);
		label1.setAlignmentX(startPanel.CENTER_ALIGNMENT);
		//label1.setText("Tic Tac Toe");
		startPanel.add(label1);

		this.add(startPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("VedArpan Project");
		this.setVisible(true);
	}
    
    public static void main(String[] args){
    	GUI g = new GUI();
    }
}