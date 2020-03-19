package org.sfu.MotaGame.Bean;

import java.util.HashMap;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;

/*
 * <h1>Image Data</h1>
 * This class gets all of our sprites for the game and places them on the map
 * 
 * 
 */
public class ImageData {

	private HashMap<Integer, BufferedImage> imageMap;
	private HashMap<dir, BufferedImage> heroImageMap;
	
	
	private static final int FLOOR = 0;
	private static final int WALL = 9;
	private static final int REWARD = 7;
	private static final int PUNISHMENT = 8; 
	private static final int ENEMY = 3;
	private static final int EXIT = 2;
	private static final int EXIT_OPEN = 5;
	private static final int BONUSREWARD = 4;
	public static BufferedImage[] heroUp, heroDown, heroLeft, heroRight;
	public static BufferedImage hero;
	/*
	 * This method contains all of the data for our images, 
	 * it gets the sprites and puts them on the map
	 * 
	 * @return Nothing.
	 */
	public ImageData() {
		imageMap = new HashMap<Integer, BufferedImage>();
		heroImageMap = new HashMap<dir, BufferedImage>();

		try {
			BufferedImage floorImg = ImageIO.read(new File(System.getProperty("user.dir") + "/sprite/floor.png"));
			BufferedImage wallImg = ImageIO.read(new File(System.getProperty("user.dir") + "/sprite/wall.png"));
			BufferedImage ItemImg = ImageIO.read(new File(System.getProperty("user.dir") + "/sprite/item1.png"));
			BufferedImage punishmentImg = ImageIO.read(new File(System.getProperty("user.dir") + "/sprite/trap.png"));
			BufferedImage enemyImg1 = ImageIO.read(new File(System.getProperty("user.dir") + "/sprite/enemy02.png"));
			BufferedImage exitImg = ImageIO.read(new File(System.getProperty("user.dir") + "/sprite/door.png"));
			BufferedImage bonusrewardImg = ImageIO.read(new File(System.getProperty("user.dir") + "/sprite/item2.png"));
			BufferedImage heroImg = ImageIO.read(new File(System.getProperty("user.dir") + "/sprite/hero.png"));
			
			imageMap.put(FLOOR, floorImg);
			imageMap.put(WALL, wallImg);
			imageMap.put(REWARD, ItemImg.getSubimage(0, 0, 32, 32));
			imageMap.put(PUNISHMENT, punishmentImg.getSubimage(0, 32, 32, 32));
			imageMap.put(ENEMY, enemyImg1.getSubimage(0, 32, 32, 32));
			imageMap.put(EXIT, exitImg.getSubimage(0, 0, 32, 32));
			imageMap.put(EXIT_OPEN, exitImg.getSubimage(0, 64, 32, 32));
			imageMap.put(BONUSREWARD, bonusrewardImg.getSubimage(0, 32, 32, 32));
			
			hero = heroImg.getSubimage(0, 0, 32, 32);
			
			heroUp = new BufferedImage[4];
			heroUp[0] = heroImg.getSubimage(0, 0, 32, 32);
			heroUp[1] = heroImg.getSubimage(32, 0, 32, 32);
			heroUp[2] = heroImg.getSubimage(64, 0, 32, 32);
			heroUp[3] = heroImg.getSubimage(96, 0, 32, 32);
			
			heroDown = new BufferedImage[4];
			heroDown[0] = heroImg.getSubimage(0, 32, 32, 32);
			heroDown[1] = heroImg.getSubimage(32, 32, 32, 32);
			heroDown[2] = heroImg.getSubimage(64, 32, 32, 32);
			heroDown[3] = heroImg.getSubimage(96, 32, 32, 32);
			
			heroLeft = new BufferedImage[4];
			heroLeft[0] = heroImg.getSubimage(0, 64, 32, 32);
			heroLeft[1] = heroImg.getSubimage(32, 64, 32, 32);
			heroLeft[2] = heroImg.getSubimage(64, 64, 32, 32);
			heroLeft[3] = heroImg.getSubimage(96, 64, 32, 32);
			
			heroRight = new BufferedImage[4];
			heroRight[0] = heroImg.getSubimage(0, 96, 32, 32);
			heroRight[1] = heroImg.getSubimage(32, 96, 32, 32);
			heroRight[2] = heroImg.getSubimage(64, 96, 32, 32);
			heroRight[3] = heroImg.getSubimage(96, 96, 32, 32);
			
			/*BufferedImage heroImg = ImageIO.read(new File(System.getProperty("user.dir") + "/sprite/hero.png"));
			heroImageMap.put(dir.UP, heroImg.getSubimage(0, 0, 32, 32));
			heroImageMap.put(dir.DOWN, heroImg.getSubimage(0, 32, 32, 32));
			heroImageMap.put(dir.LEFT, heroImg.getSubimage(0, 64, 32, 32));
			heroImageMap.put(dir.RIGHT, heroImg.getSubimage(0, 96, 32, 32));*/

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*This method
	 * 
	 * 
	 * @param i This parameter
	 * @return 
	 */
	public BufferedImage get(int i) {
		if ( i == 1) {
			// it's the hero, we might still want to render a floor for it...
			return imageMap.get(FLOOR);
		}
		return imageMap.get(i);
	}
	
	/*This method gets the direction of the player
	 * 
	 * 
	 * @param direction This parameter represents the player's direction
	 * @return 
	 */
	public BufferedImage getHeroImg(dir direction) {
		return heroImageMap.get(direction);
	}
}
