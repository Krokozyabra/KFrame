package kro.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class KFrame extends JFrame{
	private KPanel kPanel;

	private int WIDTH, HEIGHT;

	private Paintable paintable;
	private BufferedImage bufferedImage;
	private Graphics2D gr;


	private String title;

	private boolean skipPainting = false;
	private boolean isShowing = false;
	private boolean clearScreenBeforePaintint = true;




	public KFrame(int width, int height, String title, Paintable paintable){
		WIDTH = width;
		HEIGHT = height;
		setTitle(title);
		this.paintable = paintable;

		init();
	}

	private void init(){
		setFocusable(false);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		kPanel = new KPanel();
		kPanel.setFocusable(true);
		kPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		gr = bufferedImage.createGraphics();
		kPanel.setBufferedImage(bufferedImage);

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

		gr.drawImage(bufferedImage, 0, 0, null);
		bufferedImage = bufferedImage2;

		kPanel.setBufferedImage(bufferedImage);

		pack();
	}

	public void paint(){
		if(isShowing && paintable != null){
			Runnable runnable = new Runnable(){
				public void run(){
					if(clearScreenBeforePaintint){
						gr.setColor(new Color(0xffffffff));
						gr.fillRect(0, 0, WIDTH, HEIGHT);
					}

					paintable.paint(gr);
					kPanel.repaint();
				}
			};

			if(skipPainting){
				new Thread(runnable).start();
			}else{
				runnable.run();
			}
		}
	}



	//Overrided and changed setters
	public void setVisible(boolean b){
		super.setVisible(b);
		isShowing = b;
	}

	public void setTitle(String title){
		super.setTitle(title);
		this.title = title;
	}

	public void setSize(int width, int height){
		changeSize(width, height);
	}

	public void setWidth(int width){
		changeSize(width, HEIGHT);
	}

	public void setHeight(int height){
		changeSize(WIDTH, height);
	}

	public void setClearScreenBeforePaintint(boolean clearScreenBeforePaintint){
		this.clearScreenBeforePaintint = clearScreenBeforePaintint;
	}



	//setters
	public void setKPanel(KPanel kPanel){
		this.kPanel = kPanel;
	}

	public void setPaintable(Paintable paintable){
		this.paintable = paintable;
	}

	public void setBufferedImage(BufferedImage bufferedImage){
		this.bufferedImage = bufferedImage;
		changeSize(bufferedImage.getWidth(), bufferedImage.getHeight());
	}

	public void setDrawGraphics(Graphics2D gr){
		this.gr = gr;
	}

	public void setSkipPainting(boolean skipPainting){
		this.skipPainting = skipPainting;
	}


	//getters
	public KPanel getKPanel(){
		return kPanel;
	}

	public int getWidth(){
		return WIDTH;
	}

	public int getHeight(){
		return HEIGHT;
	}

	public Paintable getPaintable(){
		return paintable;
	}

	public BufferedImage getBufferedImage(){
		return bufferedImage;
	}

	public Graphics2D getDrawGraphics(){
		return gr;
	}

	public String getTitle(){
		return title;
	}

	public boolean isSkipPainting(){
		return skipPainting;
	}

	public boolean isShowing(){
		return isShowing;
	}

	public boolean isClearScreenBeforePaintint(){
		return clearScreenBeforePaintint;
	}

}
