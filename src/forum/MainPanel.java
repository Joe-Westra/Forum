package forum;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import forum.ContributionWindow.TYPE;

public class MainPanel extends JPanel implements MouseListener, MouseMotionListener{

	Collection<LinkedObject> qacs = new ArrayList<LinkedObject>();
	private int prevX;
	private int prevY;
	private boolean dragging = false;
	Collection<LinkedObject> selected = new ArrayList<LinkedObject>();

	MainPanel(){
		setSize(new Dimension(500,500));
		setVisible(true);
		setLayout(null);
		addMouseListener(this);
		addMouseMotionListener(this);

	}

	public void setQacs(Collection<LinkedObject> qacs){
		this.qacs = (ArrayList<LinkedObject>) qacs;
		//TODO qacs' to the mainpanel
	}


	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		if(dragging){  //drag selected DTI's
			int currX = arg0.getX();
			int currY = arg0.getY();
			for(LinkedObject item : selected){
				//item.setLocation(item.getCenter().x -prevX - currX,item.getCenter().y - prevY - currY);
				//item.setBounds(item.getCenter().x -prevX - currX,item.getCenter().y - prevY - currY,item.WIDTH,item.HEIGHT);
				item.move(prevX - currX, prevY - currY);
				item.repaint();

			}
			prevX = currX;
			prevY = currY;
			repaint();
		}		
	}

	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		this.paintChildren(g);
	}
	
	
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {

		selected.removeAll(selected);
		prevX = e.getX();
		prevY = e.getY();
		for(LinkedObject item : qacs){
			if(item.contains(prevX, prevY))
				selected.add(item);
		}
		System.out.println(selected.toString());
		//check to see if mouse was clicked on 'open space'
		if(selected.isEmpty())
			//if so, allow for dragging of the window
			System.out.println("empty!");
		dragging = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		dragging = false;
		if(selected.isEmpty()){
			if(e.getY() == prevY && e.getX() == prevX){
				String[] contr;
				ContributionWindow w = new ContributionWindow();
				w.show();
				contr = w.getText();
				if(contr != null){
					String head = contr[0];
					String body = contr[1];
					TYPE t = w.getContType();
					if(head != null && body != null){
						LinkedObject thisLO = new LinkedObject(head, body, t, e.getPoint());
						this.add(thisLO);
						qacs.add(thisLO);
					}
				}

			}
		}
		repaint();
	}

}
