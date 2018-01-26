/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package similarities;

import javax.swing.JFrame;

/**
 *
 * @author Andrés Movilla
 */
public class Window extends JFrame {
    
    Display disp;
    
    public Window() {
	setUndecorated(true);
	setSize(600,600);
	disp = new Display(this);
	add(disp);
	setLocationRelativeTo(null);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setLayout(null);
	setVisible(true);
	new Thread(disp).start();
    }
    
    public void doResize(int width, int height) {
	setSize(width, height);
	disp.doResize(width, height);
    }
    
}
