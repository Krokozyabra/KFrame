package kro.frame;

import java.awt.Canvas;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class KCanvas extends Canvas {
	public KCanvas(){
		setFocusable(true);
	}
	
	public void addMouseListener(MouseListener mouseListener){
		super.addMouseListener(mouseListener);
	}
	
	public void addKeyListener(KeyListener keyListener){
		super.addKeyListener(keyListener);
	}
	
	public void addMouseMotionListener(MouseMotionListener mouseMotionListener){
		super.addMouseMotionListener(mouseMotionListener);
	}
	
}
