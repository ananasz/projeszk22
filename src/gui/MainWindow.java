package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static projeszk22.Consts.*;
import szd.SzdMain;
import ht.HtMain;
import ib.IbMain;
import kg.*;

public class MainWindow extends JFrame implements ActionListener{

    private JButton exitBtn, htBtn, ibBtn, kgBtn, pdBtn, szdBtn;
    
    public MainWindow() {
        setupFrame();
        setupLayout();
        setupListeners();
    }
    
    private void setupFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(MW_TITLE);
    }
    
    private void setupLayout(){
        htBtn = new CButton(MW_BTN_1);
        ibBtn = new CButton(MW_BTN_2);
        kgBtn = new CButton(MW_BTN_3);
        pdBtn = new CButton(MW_BTN_4);
        szdBtn = new CButton(MW_BTN_5);
        exitBtn = new CButton(MW_BTN_EXIT);
        
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));
        
        buttons.add(Box.createRigidArea(new Dimension(0, 20)));
        buttons.add(htBtn);
        buttons.add(Box.createRigidArea(new Dimension(0, 10)));
        buttons.add(ibBtn);
        buttons.add(Box.createRigidArea(new Dimension(0, 10)));
        buttons.add(kgBtn);
        buttons.add(Box.createRigidArea(new Dimension(0, 10)));
        buttons.add(pdBtn);
        buttons.add(Box.createRigidArea(new Dimension(0, 10)));
        buttons.add(szdBtn);
        buttons.add(Box.createRigidArea(new Dimension(0, 30)));
        buttons.add(exitBtn);
        buttons.add(Box.createRigidArea(new Dimension(0, 20)));
        
        setContentPane(buttons);
        pack();
    }
    
    private void setupListeners(){
        htBtn.addActionListener(this);
        ibBtn.addActionListener(this);
        kgBtn.addActionListener(this);
        pdBtn.addActionListener(this);
        szdBtn.addActionListener(this);
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if( src.equals(htBtn) ){
            new HtMain().setVisible(true);
            //System.out.println("HT");
        }else if( src.equals(ibBtn) ){
            new IbMain().setVisible(true);
        }else if( src.equals(kgBtn) ){
            ColorGameLogic cgl = new ColorGameLogic();
            new ColorGameFrame(cgl).setVisible(true);
        }else if( src.equals(pdBtn) ){
            System.out.println("PD");
        }else if( src.equals(szdBtn) ){
            new SzdMain().setVisible(true);
        }
    }
    
    private class CButton extends JButton{
        public CButton(String str){
            super(str);
            setAlignmentX(Component.CENTER_ALIGNMENT);
            setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
        }
    }
}
