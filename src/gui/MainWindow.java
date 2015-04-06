package gui;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static projeszk22.Consts.*;

public class MainWindow extends JFrame{

    private JButton exitBtn, htBtn, kgBtn, pdBtn, szdBtn;
    
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
        kgBtn = new CButton(MW_BTN_2);
        pdBtn = new CButton(MW_BTN_3);
        szdBtn = new CButton(MW_BTN_4);
        exitBtn = new CButton(MW_BTN_EXIT);
        
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));
        
        buttons.add(Box.createRigidArea(new Dimension(0, 20)));
        buttons.add(htBtn);
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
        
    }
    
    private class CButton extends JButton{
        public CButton(String str){
            super(str);
            setAlignmentX(Component.CENTER_ALIGNMENT);
            setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
        }
    }
}
