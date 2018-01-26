/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package similarities;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Andrés Movilla
 */
public class Display extends Canvas implements Runnable {

    private final Window parent;
    private BufferedImage background;
    private BufferedImage bkgPieces;
    
    public Display(Window parent) {
	this.parent = parent;
	setSize(parent.getWidth(),parent.getHeight());
	try {
	    bkgPieces = ImageIO.read(new File("assets/bkgPieces.png"));
	} catch (Exception e) { }
	createBackground();
    }
    
    public void doResize(int width, int height) {
	setSize(width, height);
	createBackground();
    }
    
    private void createBackground() {
	BufferedImage img = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
	Graphics g = img.getGraphics();
	
	int maxW = (int) Math.ceil(getWidth()/13f);
	int maxH = (int) Math.ceil(getHeight()/13f);
	int currentPieceX = 0;
	int currentPieceY = 0;
	BufferedImage piece = bkgPieces.getSubimage(currentPieceX*14, currentPieceY*14, 13, 13);
	
	for (int i = 0; i < maxW; i++) {
	    for (int j = 0; j < maxH; j++) {
		
		int newPieceX = 0;
		if (i == maxW-1) {
		    currentPieceX += 2;
		} else if (i > 0) {
		    currentPieceX += 1;
		}
		
		int newPieceY = 0;
		if (j == maxH-1) {
		    currentPieceY += 2;
		} else if (j > 0) {
		    currentPieceY += 1;
		}
		if (currentPieceX != newPieceX || currentPieceY != newPieceY) {
		    piece = bkgPieces.getSubimage(currentPieceX*14, currentPieceY*14, 13, 13);
		    currentPieceX = newPieceX;
		    currentPieceY = newPieceY;
		}
		int posX = i == maxW-1? getWidth()-13 : 13*i;
		int posY = i == maxH-1? getHeight()-13 : 13*j;
		g.drawImage(piece, posX, posY, null);
	    }
	}
	
	background = img;
    }

    @Override
    public void run() {
	createBufferStrategy(3);
	while (true) {
	    Graphics g = getBufferStrategy().getDrawGraphics();
	    
	    g.drawImage(background, 0, 0, null);
	    
	    getBufferStrategy().show();
	    
	    try {
		Thread.sleep(100);
	    } catch (Exception e) { }
	}
    }
    
}
