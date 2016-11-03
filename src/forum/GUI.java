package forum;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import forum.ContributionWindow.TYPE;

public class GUI extends JFrame{

	Collection<LinkedObject> qacs = new ArrayList<LinkedObject>();
	MainPanel panel;
	public GUI() {

		System.out.println("starting");
		this.setTitle("Forum");
		panel = new MainPanel();

		this.setContentPane(panel);
		this.setSize(500, 500);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(null);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation( (screenSize.width - this.getWidth())/2, 
				(screenSize.height - this.getHeight())/2 );
		this.setVisible(true);
	}
	class ContributeAction extends AbstractAction{

		ContributeAction(){
			this.putValue(NAME, "Contribute");
			this.putValue(SHORT_DESCRIPTION, "Ask new questions or supply answers");
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String[] contr;
			ContributionWindow w = new ContributionWindow();
			w.show();
			contr = w.getText();
			if(contr != null){
				String head = contr[0];
				String body = contr[1];
				TYPE t = w.getContType();
				if(head != null && body != null){
					System.out.println(t.name() + "\nheader: " + head +"\nBody:\n" + body + "\n");
					qacs.add(new LinkedObject(head, body, t));
					
				}
				panel.setQacs(qacs);
				repaint();
			}
		}

	}
	

	

	class OptionPanel extends JPanel{
		OptionPanel(){
			Action contribute = new ContributeAction();
			JButton add = new JButton(contribute);
			JButton edit = new JButton("Edit");
			this.setLayout(new FlowLayout());
			this.add(add);
			this.add(edit);
		}

		
	}
	
	


}



