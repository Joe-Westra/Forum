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
	private int height = 65;


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
		this.type = type;
		this.setFont(new Font("Ubuntu",Font.PLAIN, 16));
		this.setText("<html>" + head); /*"<html><center><h3>" +
				head + "</h3><body> <body style='width: 200px'" +
				body.replace("\n", "<br>") + "</body></center></html>");*/
		
		this.setForeground(new Color(200,150,0));
		this.setBackground(Color.DARK_GRAY);
		this.setOpaque(true);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setVerticalAlignment(JLabel.CENTER);
		this.setBounds(center.x - width/2, center.y - height/2, width, height);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));

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

	public void add(LinkedObject lo){
		if(qlinks == null)
			qlinks = new HashSet<LinkedObject>();
		qlinks.add(lo);
	}

	
	
	/**
	 * @return the index
	 */
	public static Long getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public static void setIndex(Long index) {
		LinkedObject.index = index;
	}

	/**
	 * @return the qlinks
	 */
	public Collection<LinkedObject> getQlinks() {
		return qlinks;
	}

	/**
	 * @param qlinks the qlinks to set
	 */
	public void setQlinks(Collection<LinkedObject> qlinks) {
		this.qlinks = qlinks;
	}

	/**
	 * @return the alinks
	 */
	public Collection<LinkedObject> getAlinks() {
		return alinks;
	}

	/**
	 * @param alinks the alinks to set
	 */
	public void setAlinks(Collection<LinkedObject> alinks) {
		this.alinks = alinks;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the type
	 */
	public TYPE getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(TYPE type) {
		this.type = type;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * @param head the head to set
	 */
	public void setHead(String head) {
		this.head = head;
	}

	/**
	 * @return the center
	 */
	public Point getCenter() {
		return center;
	}

	/**
	 * @param center the center to set
	 */
	public void setCenter(Point center) {
		this.center = center;
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @return the head
	 */
	public String getHead() {
		return head;
	}
}
