package display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import util.FontAwesome;

public class PrintTicket extends JFrame {
    JFrame Pframe;
    
    PrintTicket(BookingPage obj) {
        Pframe=new JFrame("Ticket");
        Pframe.setSize(500,300);
        
        FontAwesome f=new FontAwesome();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - Pframe.getWidth()) / 2;
        int y = (screenSize.height - Pframe.getHeight()) / 2;
        Pframe.setLocation(x, y);
        Pframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        
        Icon img1 = new ImageIcon("Images\\logo-without-bg.png");
        JLabel LLogo = new JLabel("", img1, SwingConstants.LEFT);
        LLogo.setBounds(10,0,150,70);
        
        //JLabel LHeading=new JLabel("<html><font size=\"3\"color=\"#B20000\">Thanks for booking with us.Happy Journey!</font></html>");
        JLabel LName=new JLabel("<html><B><font size=\"2\"color=\"1abc9c\">Booked by "+obj.TxtName.getText()+"</font> </B></html>");
       
        
        
        JButton BBook=new JButton("\uf02f");
        BBook.setFont(f.m);
        BBook.setForeground(Color.decode("#2c3e50"));
        
        JLabel LDummy=new JLabel("<html><B> </B></html>");
        JLabel LDate=new JLabel("<html><B><font size=\"2\"color=\"white\">Successfully Booked Flight!"+"\t\t\t"+obj.objsearch.CBDated.getSelectedItem().toString()+" Oct 2014</font></B></html>");
        JLabel LPerson=new JLabel("<html><B><font size=\"4\"color=\"#1abc9c\">Boarding pass for "+obj.objsearch.SlidePerson.getValue()+ "</font></B></html>");
        JLabel LFlight1=new JLabel("<html><B><font size=\"2\"color=\"1abc9c\">"+obj.flight1+" Flight from "+obj.objsearch.CBPlace.getSelectedItem().toString()+" to "+obj.inter+" .</font></B></html>");
        JLabel LFlight2=new JLabel("<html><B><font size=\"2\"color=\"1abc9c\">"+obj.flight2+" Flight from "+obj.inter+" to SINGAPORE .</font></B></html>");
        JLabel LTime=new JLabel("<html><B><font size=\"2\"color=\"blue\">Time</font></B></html>");
        JTextField TxtName=new JTextField();        
        
        JLabel barcode=new JLabel("\uf02a");
        barcode.setFont(f.lg);
       
        LDate.setBounds(200, 20, 240, 30);
        LPerson.setBounds(150, 90, 200, 30); 
        
        LFlight1.setBounds(30,150,250,30);
        //LHeading.setBounds(20,10,250,30);
        LFlight2.setBounds(30,180,250,30);
        LTime.setBounds(30,120,70,30);
        BBook.setBounds(30,240,50,30);
        barcode.setBounds(400,240,450,30);
        //TxtName.setBounds(130, 50, 100, 30);
        
        LName.setBounds(300, 220, 240, 30);
         
        
        JPanel pane=new JPanel();
        pane.add(LDate);
        pane.setBackground(Color.decode("#2c3e50"));
        pane.setBounds(0, 0, 500, 80);
        
        
       // Pframe.add(LHeading);
        Pframe.add(LLogo);
        Pframe.add(barcode);
        //Pframe.add(LTime);
        Pframe.add(LName);
        Pframe.add(BBook);
        Pframe.add(LFlight1);
        Pframe.add(LFlight2);
        Pframe.add(LDate);
        Pframe.add(LPerson);
        Pframe.add(pane);
        Pframe.setResizable(false);
        Pframe.add(LDummy);
        Pframe.setVisible(true);
        
        
        
        BBook.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent e) {
               JOptionPane.showMessageDialog(null, "Thank You!\nYour booking has been confirmed.\nHave a safe journey.");
               Pframe.dispose();               
           }           
        });
    }
}
