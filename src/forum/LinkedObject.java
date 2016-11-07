package forum;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Collection;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import forum.ContributionWindow.TYPE;
/**
 *
 * @author joe
 */
public class LinkedObject extends JLabel{
	private static Long index = Long.MIN_VALUE;
	private Collection<LinkedObject> qlinks;
	private Collection<LinkedObject> alinks;
	private Long id;
	private String body;
	private String head;
	private TYPE type;
	private Point center;
	private int width = 200;
	private int height = 60;


	LinkedObject(String head, String body, TYPE type){
		this(head, body, type, null);
	}

	LinkedObject(String head, String body, TYPE type, Point center){
		this(head, body, type, center, null, null);
	}

	LinkedObject(String head,
			String body,
			TYPE type,
			Point center,
			Collection<LinkedObject> qlinks,
			Collection<LinkedObject> alinks)
	{
		this(head, body, type, center, index, qlinks, alinks);
		index++;
	}

	LinkedObject(	String head,
			String body,
			TYPE type,
			Point center,
			Long id,
			Collection<LinkedObject> qlinks,
			Collection<LinkedObject> alinks)
	{

		this.qlinks = qlinks;
		this.alinks = alinks;
		this.center = center;
		this.id = id;
		this.head = head;
		this.body = body;
		this.setFont(new Font("Ubuntu",Font.PLAIN, 14));
		this.setText("<html><center><h3>" +
				head + "</h3><body> <body style='width: 200px'" +
				body.replace("\n", "<br>") + "</body></center></html>");
		this.setForeground(new Color(200,150,0));
		this.setBackground(Color.DARK_GRAY);
		this.setOpaque(true);
		this.setHorizontalAlignment(JLabel.LEADING);
		this.setVerticalAlignment(JLabel.TOP);
		this.setBounds(center.x - width/2, center.y - height/2, width, height);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));

	}

	public Point getCenter(){
		return center;
	}

	public void setCenter(Point p){
		center = p;
	}

	public void move(int x, int y){
		center = new Point((int)center.getX() - x, (int)center.getY() - y);
		repaint();
	}

	public boolean contains(int x, int y){
		if(x >= center.x - width/2 && x <= center.x + width/2
				&& y >= center.y - height/2 && y <= center.y + height/2)
			return true;
		return false;
	}

	protected void paintComponent(Graphics g){
		super.setBounds(center.x - width/2, center.y - height/2, width, height);
		super.paintComponent(g);
	}

	public String getBody(){
		return body;
	}

	public void add(LinkedObject lo){
		if(qlinks == null)
			qlinks = new HashSet<LinkedObject>();
		qlinks.add(lo);
	}

	public Collection<LinkedObject> getLinks(){
		return qlinks;
	}
}
