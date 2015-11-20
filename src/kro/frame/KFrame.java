package kro.frame;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class KFrame {
	private JFrame jFrame;
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
		this.title = title;
		this.numBuffers = numBuffers;
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.paintable = paintable;
		
		init();
	}
	
	private void init(){
		jFrame = new JFrame(title);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setResizable(false);
		
		kCanvas = new KCanvas();
		kCanvas.setSize(WIDTH, HEIGHT);
		
		bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		
		jFrame.add(kCanvas);
		
		jFrame.pack();
		jFrame.setLocationRelativeTo(null);
		
	}
	
	public void show(){
		jFrame.setVisible(true);
		
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

	public JFrame getjFrame() {
		return jFrame;
	}

	public KCanvas getkCanvas() {
		return kCanvas;
	}

	public BufferStrategy getBufferStrategy() {
		return bufferStrategy;
	}

	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public Graphics2D getCanvasGraphics() {
		return canvasGraphics;
	}

	public Graphics2D getBufferStrategyGraphics() {
		return bufferStrategyGraphics;
	}

	public Graphics2D getBufferedImageGraphics() {
		return bufferedImageGraphics;
	}

	public String getTitle() {
		return title;
	}

	public int getWIDTH() {
		return WIDTH;
	}

	public int getHEIGHT() {
		return HEIGHT;
	}

	public void setjFrame(JFrame jFrame) {
		this.jFrame = jFrame;
	}

	public void setkCanvas(KCanvas kCanvas) {
		this.kCanvas = kCanvas;
	}

	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}
	
}
