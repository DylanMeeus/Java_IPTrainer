package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controllers.MainController;


public class MainView extends JFrame
{
	int correct;
	MainController controller;
	JPanel taskPanel;
	JPanel userInputPanel;
	JButton generateQuestion;
	
	JButton smButton;
	JButton lowestButton;
	JButton highestButton;
	
	JTextField smField;
	JTextField lowestField;
	JTextField highestField;
	
	JTextArea task;
	
	JLabel smLabel;
	JLabel lowestLabel;
	JLabel highestLabel;
	
	public MainView(MainController mc)
	{
		controller = mc;
		setup();
	}
	
	public void setup()
	{
		this.setTitle("Java - IPBETA");
		this.setSize(400,250);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		
		task = new JTextArea();
		task.setText("");
		taskPanel = new JPanel();
		taskPanel.setLayout(new FlowLayout());
		generateQuestion = new JButton("Nieuw IP");
		taskPanel.add(task);
		taskPanel.add(generateQuestion);
		this.add(taskPanel,BorderLayout.CENTER);
		this.setVisible(true);
		
		userInputPanel = new JPanel();
		userInputPanel.setLayout(new GridLayout(3,2));
		JPanel sm = new JPanel();
		sm.setLayout(new GridLayout(1,2));
		smLabel = new JLabel("Subnetmasker");
		smField = new JTextField(10);
		sm.add(smLabel);sm.add(smField);
		userInputPanel.add(sm);
		smButton = new JButton("Controleer");
		userInputPanel.add(smButton);
		
		lowestLabel = new JLabel("van");
		lowestField = new JTextField(10);
		lowestButton = new JButton("Controleer");
		JPanel lowest = new JPanel();
		lowest.setLayout(new GridLayout(1,2));
		lowest.add(lowestLabel); lowest.add(lowestField);
		userInputPanel.add(lowest);
		userInputPanel.add(lowestButton);
		this.add(userInputPanel,BorderLayout.SOUTH);
	
		
		highestLabel = new JLabel("tot");
		highestField = new JTextField(10);
		highestButton = new JButton("Controleer");
		JPanel highest = new JPanel();
		highest.setLayout(new GridLayout(1,2));
		highest.add(highestLabel);highest.add(highestField);
		userInputPanel.add(highest);
		userInputPanel.add(highestButton);
		addActions();
	}
	
	private void addActions()
	{
		generateQuestion.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				correct = 0;
				task.setText(controller.generateQuestion());
				smField.setEditable(true);
				lowestField.setEditable(true);
				highestField.setEditable(true);
				smField.setForeground(Color.BLACK);
				lowestField.setForeground(Color.BLACK);
				highestField.setForeground(Color.BLACK);
				
				smField.setText("");
				lowestField.setText("");
				highestField.setText("");
			}	
		});
		
		smButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(controller.subnetIsCorrect(smField.getText()))
				{
					correct++;
					smField.setForeground(Color.GREEN);
					smField.setEditable(false);
				}
			}
		});
		
		lowestButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(controller.lowestIsCorrect(lowestField.getText()))
				{
					correct++;
					lowestField.setForeground(Color.GREEN);
					lowestField.setEditable(false);
				}
				else
				{
					System.out.println("wrong");
				}
			}
		});
		
		highestButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(controller.highestIsCorrect(highestField.getText()))
				{
					correct++;
					highestField.setForeground(Color.GREEN);
					highestField.setEditable(false);
				}
				
				if(correct==3)
				{
					System.out.println("ALL CORRECT");
				}
				
				
			}
		});
	}
}
