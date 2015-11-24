package kro.frame;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.sun.xml.internal.ws.db.glassfish.BridgeWrapper;

public class KFrame extends JFrame{
	private KCanvas kCanvas;
	
	private BufferStrategy bufferStrategy;
	private BufferedImage bufferedImage;
	
	private Graphics2D canvasGraphics;
	private Graphics2D bufferStrategyGraphics;
	private Graphics2D bufferedImageGraphics;
	
	private int numBuffers;
	
	private String title;
	
	private Paintable paintable;
	
	private int WIDTH, HEIGHT;
	
	public KFrame(int WIDTH, int HEIGHT, String title, int numBuffers, Paintable paintable) {
		super(title);
		
		this.title = title;
		this.numBuffers = numBuffers;
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.paintable = paintable;
		
		init();
	}
	
	private void init(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		kCanvas = new KCanvas();
		kCanvas.setSize(WIDTH, HEIGHT);
		
		bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		
		add(kCanvas);
		
		pack();
		setLocationRelativeTo(null);
		
	}
	
	
	public void display(){
		super.setVisible(true);
		
		kCanvas.createBufferStrategy(numBuffers);
		bufferStrategy = kCanvas.getBufferStrategy();
		
		bufferStrategyGraphics = (Graphics2D) bufferStrategy.getDrawGraphics();
		canvasGraphics = (Graphics2D) kCanvas.getGraphics();
		bufferedImageGraphics = bufferedImage.createGraphics();
	}
	
	public void paint(){
		bufferStrategyGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		paintable.paint(bufferStrategyGraphics);
		
		bufferStrategy.show();
	}

	
	
	
	
	public KCanvas getKCanvas(){
		return kCanvas;
	}

	public BufferStrategy getBufferStrategy(){
		return bufferStrategy;
	}

	public int getNumBuffers(){
		return numBuffers;
	}

	public String getTitle(){
		return title;
	}

	public Paintable getPaintable(){
		return paintable;
	}

	public int getWIDTH(){
		return WIDTH;
	}

	public int getHEIGHT(){
		return HEIGHT;
	}
	

	public void setKCanvas(KCanvas kCanvas){
		this.kCanvas = kCanvas;
	}

	public void setBufferStrategy(BufferStrategy bufferStrategy){
		this.bufferStrategy = bufferStrategy;
	}

	public void setNumBuffers(int numBuffers){
		this.numBuffers = numBuffers;
		
		try{
			kCanvas.createBufferStrategy(numBuffers);
			bufferStrategy = kCanvas.getBufferStrategy();
			
			bufferStrategyGraphics = (Graphics2D) bufferStrategy.getDrawGraphics();
			canvasGraphics = (Graphics2D) kCanvas.getGraphics();
			bufferedImageGraphics = bufferedImage.createGraphics();
		}catch(Exception ex){
		}
	}

	public void setTitle(String title){
		this.title = title;
		super.setTitle(title);
	}

	public void setPaintable(Paintable paintable){
		this.paintable = paintable;
	}

	public void setWIDTH(int WIDTH){
		this.WIDTH = WIDTH;
		kCanvas.setSize(WIDTH, HEIGHT);
	}

	public void setHEIGHT(int HEIGHT){
		this.HEIGHT = HEIGHT;
		kCanvas.setSize(WIDTH, HEIGHT);
	}

	public void addMouseListener(MouseListener mouseListener){
		kCanvas.addMouseListener(mouseListener);
	}
	
	public void addKeyListener(KeyListener keyListener){
		kCanvas.addKeyListener(keyListener);
	}
	
	public void addMouseMotionListener(MouseMotionListener mouseMotionListener){
		kCanvas.addMouseMotionListener(mouseMotionListener);
	}
}
