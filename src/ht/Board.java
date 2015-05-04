package ht;

import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Herendi Tibor
 */

public class Board extends JPanel{
        private final int level[] = {
            0,0,0,0,0,0,0,0,0,0,0,0,
            0,1,1,0,0,1,1,1,1,1,1,0,
            0,1,1,0,0,1,1,1,0,1,1,0,
            0,1,1,0,0,1,0,0,0,0,0,0,
            0,1,1,1,1,1,0,0,0,0,0,0,
            0,1,1,1,1,1,1,1,1,1,1,0,
            0,1,1,1,1,1,1,1,1,1,1,0,
            0,1,1,1,1,1,1,1,1,1,1,0,
            0,1,1,1,1,1,1,1,1,1,1,0,
            0,1,1,1,1,1,1,1,1,1,1,0,
            0,1,1,1,1,1,1,1,1,1,1,0,
            0,0,0,0,0,0,0,0,0,0,0,0
            
    };
        private PacMan pacman;
        private ArrayList<Ghost> ghosts;
        
        public Board(){
            setupMovables();
        }
        
        private void setupMovables(){
            this.pacman = new PacMan();
            for(int i = 0;i<6;++i) ghosts.add(new Ghost());
        }
        
}