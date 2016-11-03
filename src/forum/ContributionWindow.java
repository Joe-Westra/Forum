package forum;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class ContributionWindow{


	private String title;
	private JTextField descText;
	private JTextArea bodyText;
	private JPanel panel, wholePanel;
	private JButton[] selection = new JButton[2];
	private JRadioButton[] type = new JRadioButton[3];
	private String[] text;
	private TYPE contType = TYPE.QUESTION;
	public enum TYPE{QUESTION,ANSWER,COMMENT};

	public ContributionWindow(){


		wholePanel = new JPanel(new BorderLayout());
		panel = new JPanel(new GridLayout(0,1));
		descText = new JTextField("");
		bodyText = new JTextArea("");
		selection[0] = new JButton("OK");
		selection[1] = new JButton("Cancel");
	}

	public void setTitle(String title){
		this.title = title;
	}

	public void show(){
		text = new String[2];
		JDialog jd = new JDialog();

		panel.add(new JLabel("Explain yourself:"));
		panel.add(new JScrollPane(bodyText));
		wholePanel.add(panel, BorderLayout.CENTER);

		JPanel north1 = new JPanel();
		north1.setLayout(new FlowLayout());		
		type[0] = new JRadioButton("Question");
		type[1] = new JRadioButton("Answer");
		type[2] = new JRadioButton("Comment");
		type[0].setSelected(true);
		type[0].addActionListener(e-> contType = TYPE.QUESTION);
		type[1].addActionListener(e-> contType = TYPE.ANSWER);
		type[2].addActionListener(e-> contType = TYPE.COMMENT);
		
		
		ButtonGroup bg = new ButtonGroup();
		for(JRadioButton j : type){
			bg.add(j);
			north1.add(j);
		}
		
		JPanel north2 = new JPanel();
		north2.setLayout(new FlowLayout());
		north2.add(new JLabel("provide a short description:"));
		//descText.setMinimumSize(new Dimension(descText.getHeight(), 100));
		descText.setPreferredSize(new Dimension(300, 20));
		north2.add(descText);
		
		JPanel north = new JPanel();
		north2.setPreferredSize(new Dimension(300,50));
		north.setLayout(new GridLayout(0,1));
		north.add(north1);
		north.add(north2);
		
		
		JPanel south = new JPanel(new FlowLayout());
		for(JButton j : selection)
			south.add(j);
		selection[0].addActionListener(e ->{
			text[0] = descText.getText().equals("")
					? getAutoHeader(bodyText.getText())
					: descText.getText();

			text[1] = bodyText.getText() != ""
					? bodyText.getText()
					: "";

					jd.setVisible(false);
					jd.dispose();

		});
		
		selection[1].addActionListener(e ->{
			text = null;
			jd.setVisible(false);
			jd.dispose();
		});
		
		wholePanel.add(south, BorderLayout.SOUTH);
		wholePanel.add(north, BorderLayout.NORTH);
		jd.setContentPane(wholePanel);
		jd.setModal(true);
		jd.setName(title);
		jd.setSize(300,600);
		jd.setVisible(true);
	}

	public TYPE getContType(){
		return contType;
	}
	
	public String getAutoHeader(String input){
		int sentenceEnd = Integer.max(input.indexOf('.'), Integer.max(input.indexOf('!'),input.indexOf('?')) );
		if(sentenceEnd == -1)
			return "No Description";
		if(sentenceEnd > 50)
			return input.substring(0, 30) + "...";
		return input.substring(0, sentenceEnd + 1);
	}
	public static String getLineBreak()
	{
		return "<br>";
	}

	public String[] getText(){
		return text;
	}
}