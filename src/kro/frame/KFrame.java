package kro.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.swing.JFrame;

public class KFrame extends JFrame{
	KPanel kPanel;

	private Graphics2D gr;


	private String title;

	private Paintable paintable;

	private boolean isShowing = false;


	private int WIDTH, HEIGHT;
	private BufferedImage bufferedImage;

	private boolean skipPainting = false;



	public KFrame(int WIDTH, int HEIGHT, String title, Paintable paintable){
		super(title);

		this.title = title;
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.paintable = paintable;

		init();
	}

	private void init(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		kPanel = new KPanel();
		kPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		gr = bufferedImage.createGraphics();

		add(kPanel);

		pack();
		setLocationRelativeTo(null);
	}


	private void changeSize(int width, int height){
		WIDTH = width;
		HEIGHT = height;

		kPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		BufferedImage bufferedImage2 = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		gr = bufferedImage2.createGraphics();
		gr.drawImage(bufferedImage, 0, 0, WIDTH, HEIGHT, null);

		bufferedImage = bufferedImage2;

		pack();
		setLocationRelativeTo(null);
	}

	public void setVisible(boolean b){
		super.setVisible(b);
		isShowing = b;
	}




	public void paint(){
		if(isShowing){
			Runnable runnable = new Runnable(){
				public void run(){
					gr.setColor(new Color(0xff000000));
					gr.fillRect(0, 0, WIDTH, HEIGHT);

					gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					paintable.paint(gr);

					kPanel.getGraphics().drawImage(bufferedImage, 0, 0, null);
				}
			};

			if(skipPainting){
				new Thread(runnable).start();
			}else{
				runnable.run();
			}
		}
	}




	public KPanel getKPanel(){
		return kPanel;
	}

	public Graphics2D getGraphics(){
		return gr;
	}

	public String getTitle(){
		return title;
	}

	public Paintable getPaintable(){
		return paintable;
	}

	public boolean isShowing(){
		return isShowing;
	}

	public int getWidth(){
		return WIDTH;
	}

	public int getHeight(){
		return HEIGHT;
	}

	public BufferedImage getBufferedImage(){
		return bufferedImage;
	}

	public boolean isSkipPainting(){
		return skipPainting;
	}

	
	
	
	public void setKPanel(KPanel kPanel){
		this.kPanel = kPanel;
	}

	public void setGraphics(Graphics2D gr){
		this.gr = gr;
	}

	public void setTitle(String title){
		this.title = title;
		super.setTitle(title);
	}

	public void setPaintable(Paintable paintable){
		this.paintable = paintable;
	}

	public void setWidth(int width){
		changeSize(width, HEIGHT);
	}

	public void setHeight(int height){
		changeSize(WIDTH, height);
	}

	public void setSize(int width, int height){
		changeSize(width, height);
	}

	public void setBufferedImage(BufferedImage bufferedImage){
		this.bufferedImage = bufferedImage;
	}

	public void setSkipPainting(boolean skipPainting){
		this.skipPainting = skipPainting;
	}





	public void addKeyListener(KeyListener keyListener){
		kPanel.addKeyListener(keyListener);
	}


	public void addMouseListener(MouseListener mouseListener){
		kPanel.addMouseListener(mouseListener);
	}

	public void addMouseMotionListener(MouseMotionListener mouseMotionListener){
		kPanel.addMouseMotionListener(mouseMotionListener);
	}

}
