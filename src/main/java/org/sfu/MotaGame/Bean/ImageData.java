package org.sfu.MotaGame.Bean;

import java.util.HashMap;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;

import org.sfu.MotaGame.Bean.*;


public class ImageData {

	private HashMap<Integer, BufferedImage> imageMap;
	private HashMap<dir, BufferedImage> heroImageMap;
	
	
	private static final int FLOOR = 0;
	private static final int WALL = 9;
	
	public ImageData() {
		imageMap = new HashMap<Integer, BufferedImage>();
		heroImageMap = new HashMap<dir, BufferedImage>();

		
		try {
			BufferedImage floorImg = ImageIO.read(new File(System.getProperty("user.dir") + "/sprite/floor.png"));
			BufferedImage wallImg = ImageIO.read(new File(System.getProperty("user.dir") + "/sprite/wall.png"));
			imageMap.put(FLOOR, floorImg);
			imageMap.put(WALL, wallImg);
			
			
			BufferedImage heroImg = ImageIO.read(new File(System.getProperty("user.dir") + "/sprite/hero.png"));
			heroImageMap.put(dir.UP, heroImg.getSubimage(0, 0, 32, 32));
			heroImageMap.put(dir.DOWN, heroImg.getSubimage(0, 32, 32, 32));
			heroImageMap.put(dir.LEFT, heroImg.getSubimage(0, 64, 32, 32));
			heroImageMap.put(dir.RIGHT, heroImg.getSubimage(0, 96, 32, 32));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public BufferedImage get(int i) {
		if ( i == 1) {
			// it's the hero, we might still want to render a floor for it...
			return imageMap.get(FLOOR);
		}
		return imageMap.get(i);
	}
	
	public BufferedImage getHeroImg(dir direction) {
		return heroImageMap.get(direction);
	}
}