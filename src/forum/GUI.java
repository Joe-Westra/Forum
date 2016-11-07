package forum;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import forum.ContributionWindow.TYPE;

public class GUI extends JFrame{

	Collection<LinkedObject> qacs = new ArrayList<LinkedObject>();
	MainPanel panel;
	EditPanel edit;
	private boolean editingConnections;
	private LinkedObject currentLO;

	public GUI() {
		this.setTitle("Forum");
		panel = new MainPanel();
		JSplitPane all = new JSplitPane();
		all.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		edit = new EditPanel();
		all.add(edit, JSplitPane.RIGHT);
		all.add(panel, JSplitPane.LEFT);
		all.setResizeWeight(1);
		all.addPropertyChangeListener(JSplitPane.LAST_DIVIDER_LOCATION_PROPERTY, 
				e->{}
				);
		this.setContentPane(all);
		this.setSize(500, 500);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(null);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation( (screenSize.width - this.getWidth())/2, 
				(screenSize.height - this.getHeight())/2 );
		this.setVisible(true);
		currentLO = null;
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
				if(head != null && body != null)
					qacs.add(new LinkedObject(head, body, t));
				panel.setQacs(qacs);
				repaint();
			}
		}
	}

	class MainPanel extends JPanel implements MouseListener, MouseMotionListener{

		Collection<LinkedObject> qacs = new ArrayList<LinkedObject>();
		private int prevX;
		private int prevY;
		private boolean dragging = false;
		ArrayList<LinkedObject> selected = new ArrayList<LinkedObject>();

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
			if(dragging){
				int currX = arg0.getX();
				int currY = arg0.getY();
				if(! selected.isEmpty()){//drag selected LO's
					for(LinkedObject item : selected){
						item.move(prevX - currX, prevY - currY);
//						item.repaint();
					}
				}else{//move the origin
					Graphics g = this.getGraphics();
					g.translate(currX, currY);
				}
				prevX = currX;
				prevY = currY;
				repaint();
			}		
		}


		protected void paintComponent(Graphics g){

			super.paintComponent(g);

			Graphics2D g2d = (Graphics2D) g.create();


			//draw lines between all connections
			if(qacs != null)
				g2d.setColor(new Color(200,0,0));
			g2d.setStroke(new BasicStroke(4,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,4));
			g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON));	
			for(LinkedObject each : qacs){
				Collection<LinkedObject> links = each.getLinks();
				if(links != null){
					for(LinkedObject eachlink : links){
						g2d.drawLine(each.getCenter().x,each.getCenter().y, eachlink.getCenter().x,eachlink.getCenter().y);
					}
				}
				if(! dragging)
					g2d.drawRect(currentLO.getX()-2, currentLO.getY()-2, currentLO.getWidth()+3, currentLO.getHeight()+3);
			}


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
		/**
		 * Mouse events should follow this protocol:
		 * 	-if empty space is clicked, prompt for addition via contribution pop-up
		 * 	-allow for dragging of selected components 
		 * 		-currently drags all under the mouse, might want to just take the top z-level item...
		 */
		@Override
		public void mousePressed(MouseEvent e) {

			selected.removeAll(selected);
			prevX = e.getX();
			prevY = e.getY();
			for(LinkedObject item : qacs){
				if(item.contains(prevX, prevY))
					selected.add(item);
			}
			dragging = true;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			dragging = false;
			if(e.getY() == prevY && e.getX() == prevX){ //if the mouse was not dragged
				if(selected.isEmpty()){	//if the mouse was clicked in open space
					promptForContribution(e.getPoint());
				}else{  //select the topmost item and display the body text in editing panel
					LinkedObject high = getHighest(selected);

					if(! editingConnections){ //if not in editing connections mode
						edit.setText(high.getBody());
						edit.edit.allowEdit(true);
						currentLO = high;
					}else{//if in editing connections mode
						high.add(currentLO);
						currentLO.add(high);
					}
				}//end else
			}
			repaint();
		}

		private LinkedObject getHighest(ArrayList<LinkedObject> selected2) {
			int topmost = Integer.MAX_VALUE;
			int highest = 0;
			for(int i = 0; i < selected.size(); i++){
				int zValue = getComponentZOrder(selected.get(i));
				if(zValue < topmost){
					topmost = zValue;
					highest = i;
				}
			}
			return selected.get(highest);
		}

		private void promptForContribution(Point center) {
			String[] contr;
			ContributionWindow w = new ContributionWindow();
			w.show();
			contr = w.getText();
			if(contr != null){
				String head = contr[0];
				String body = contr[1];
				TYPE t = w.getContType();
				if(head != null && body != null){
					LinkedObject thisLO = new LinkedObject(head, body, t, center);
					this.add(thisLO);
					qacs.add(thisLO);
					edit.setText(thisLO.getBody());
					currentLO = thisLO;
				}
				edit.edit.allowEdit(true);
			}			
		}
	}

	class EditPanel extends JTabbedPane {
		TextView view;
		EditView edit;

		public EditPanel(){
			view = new TextView();
			edit  = new EditView();
			this.addTab("Read", null, view, "Read the details of the contribution");
			this.addTab("Edit", null, edit, "Edit and view advanced aspects of the contribution");
		}

		public void setText(String text){
			view.textBox.setText(text);
		}

		public class TextView extends JPanel{
			JTextArea textBox;
			public TextView(){
				this("");
			}
			public TextView(String text){
				textBox = new JTextArea(text);
				textBox.setEditable(false);
				textBox.setLineWrap(true);
				textBox.setSize(this.getWidth(), this.getHeight());
				this.add(textBox);
			}
			public void setText(String text){
				textBox.setText(text);
			}

			protected void paintComponent(Graphics g){
				textBox.setSize(this.getSize());
				super.paintComponent(g);
			}

		}


		public class EditView extends JPanel{
			JCheckBox editConnections;



			EditView(){
				editConnections = new JCheckBox("Edit Connections");
				editConnections.setSelected(false);
				editConnections.setEnabled(false);
				editConnections.addActionListener(e -> editingConnections = !editingConnections);
				this.add(editConnections);
			}

			public void allowEdit(boolean bool){
				editConnections.setEnabled(bool);
			}
		}
	}

}



