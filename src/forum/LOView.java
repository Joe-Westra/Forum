package forum;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;

public class LOView extends JButton implements ActionListener{

	private static final Font defaultFont = new Font("Ubuntu", Font.PLAIN, 12);




	public LOView() {
		this("");
	}

	public LOView(String text){
		this(text, defaultFont);
	}

	public LOView(String text, Font font){
		FontMetrics fm = getFontMetrics(font);
		int fHeight = fm.getMaxAscent() +
						fm.getDescent() +
						fm.getLeading();
		this.setPreferredSize(new Dimension(200,fHeight));
		this.setBorderPainted(true);
		setText(text);
		setBackground(Color.ORANGE);
		setVisible(true);
		System.out.println(this.getAlignmentX() + " " + this.getAlignmentY());
		
	}
	
	protected void paintComponent(Graphics g){
//		this.paint(g);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
System.out.println("asdfasdf");		
	}


}
