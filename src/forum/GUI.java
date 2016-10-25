package forum;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class GUI extends JFrame implements ActionListener{
    
    
    JPanel panel;
    JButton btn;
    JLabel txt;
            final String s = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean eu nulla urna. Donec sit amet risus nisl, a porta enim. Quisque luctus, ligula eu scelerisque gravida, tellus quam vestibulum urna, ut aliquet sapien purus sed erat. Pellentesque consequat vehicula magna, eu aliquam magna interdum porttitor. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Sed sollicitudin sapien non leo tempus lobortis. Morbi semper auctor ipsum, a semper quam elementum a. Aliquam eget sem metus.";
        final String html1 = "<html><body style='width: ";
        final String html2 = "px'>";
    public void ShowGUI(){
        //testDisplayPanel();
        testLayout();
    }
    
    public void testLayout(){
        panel = new JPanel(new BorderLayout());
        this.setSize(500,500);
        btn = new JButton("dude");
        txt = new JLabel();
        btn.addActionListener(this);
        panel.add(txt, BorderLayout.CENTER);
        panel.add(btn, BorderLayout.SOUTH);
        HamletQuote ham = new HamletQuote();
    	Question q = new Question(null,null,ham.getQuoteFromHamlet());  

        panel.add(q,BorderLayout.WEST);
        this.add(panel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void testDisplayPanel(){
        DisplayPanel p = new DisplayPanel(14, "This is my test string, it's long and has"
                + " lots of spaces which might not be ideal... we will see",300,400);
        
        this.setSize(300,400);
        this.add(p);
        this.revalidate();
        this.repaint();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
     txt.setText(html1 + "2" + html2 + s); 
    }
    
    
    
    
    
}
