package kro.frame;

import java.awt.Canvas;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class KCanvas extends Canvas {
	public KCanvas(){
		setFocusable(true);
	}
	
	public void addMouseListener(MouseListener mouseListener){
		addMouseListener(mouseListener);
	}
	
	public void addKeyListener(KeyListener keyListener){
		addKeyListener(keyListener);
	}
	
	public void addMouseMotionListener(MouseMotionListener mouseMotionListener){
		addMouseMotionListener(mouseMotionListener);
	}
	
}
