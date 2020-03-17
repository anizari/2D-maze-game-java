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
	private static final int REWARD = 7;
	private static final int PUNISHMENT = 8; 

	private static final int DOOR = 2;
	
	//Dimensions for the sprite crops
	private static final int width = 32, height = 32;

	private static final int ENEMY = 3;
	private static final int EXIT = 2;
	
	public ImageData() {
		imageMap = new HashMap<Integer, BufferedImage>();
		heroImageMap = new HashMap<dir, BufferedImage>();

		try {
			BufferedImage floorImg = ImageIO.read(new File(System.getProperty("user.dir") + "/sprite/floor.png"));
			BufferedImage wallImg = ImageIO.read(new File(System.getProperty("user.dir") + "/sprite/wall.png"));
			BufferedImage ItemImg = ImageIO.read(new File(System.getProperty("user.dir") + "/sprite/item1.png"));
			BufferedImage punishmentImg = ImageIO.read(new File(System.getProperty("user.dir") + "/sprite/trap.png"));
	//		BufferedImage EnemyImag1 = ImageIO.read(new File(System.getProperty("user.dir") + "/sprite/enemy09.png"));
			BufferedImage enemyImg1 = ImageIO.read(new File(System.getProperty("user.dir") + "/sprite/enemy02.png"));
			BufferedImage exitImg = ImageIO.read(new File(System.getProperty("user.dir") + "/sprite/door.png"));
			imageMap.put(FLOOR, floorImg);
			imageMap.put(WALL, wallImg);
			imageMap.put(REWARD, ItemImg.getSubimage(width, height, width, height));
	//		imageMap.put(PUNISHMENT, EnemyImag1.getSubimage(0, height, width, height));
			imageMap.put(REWARD, ItemImg.getSubimage(32, 32, 32, 32));
			imageMap.put(PUNISHMENT, punishmentImg.getSubimage(0, 32, 32, 32));
			imageMap.put(ENEMY, enemyImg1.getSubimage(0, 32, 32, 32));
			imageMap.put(EXIT, exitImg.getSubimage(0, 32, 32, 32));
	//		imageMap.put(PUNISHMENT, EnemyImag1.getSubimage(0, 32, 32, 32));
			
			
			BufferedImage heroImg = ImageIO.read(new File(System.getProperty("user.dir") + "/sprite/hero.png"));
			heroImageMap.put(dir.UP, heroImg.getSubimage(0, 0, width, height));
			heroImageMap.put(dir.DOWN, heroImg.getSubimage(0, height, width, height));
			heroImageMap.put(dir.LEFT, heroImg.getSubimage(0, height*2, width, height));
			heroImageMap.put(dir.RIGHT, heroImg.getSubimage(0, height*3, width, height));

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
