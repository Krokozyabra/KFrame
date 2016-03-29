package kro.frame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class KPanel extends JPanel{

	private BufferedImage bufferedImage;
	
	public KPanel(){
		super();
		setFocusable(true);
	}

	public BufferedImage getBufferedImage(){
		return bufferedImage;
	}


	public void setBufferedImage(BufferedImage bufferedImage){
		this.bufferedImage = bufferedImage;
	}



	protected void paintComponent(Graphics gr){
		gr.drawImage(bufferedImage, 0, 0, null);
	}
}
