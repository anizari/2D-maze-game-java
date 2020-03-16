package myPack.graphics;

import java.awt.image.BufferedImage;

public class Resource {
	
	private static final int width = 32, height = 32;
	
	public static BufferedImage player, floor, enemy, item, key, door, wall, punishment;
	public static BufferedImage[] movePlayerDown, movePlayerUp, movePlayerLeft, movePlayerRight;
	
	
	public static void init() {
		Sprite playerSheet = new Sprite(Image.loadImage("/sprite/hero.png"));
		Sprite floorSheet = new Sprite(Image.loadImage("/sprite/floor.png"));
		Sprite wallSheet = new Sprite(Image.loadImage("/sprite/door2.png"));
		Sprite enemySheet = new Sprite(Image.loadImage("/sprite/enemy01.png"));
		Sprite itemSheet = new Sprite(Image.loadImage("/sprite/item1.png"));
		Sprite doorSheet = new Sprite(Image.loadImage("/sprite/door.png"));
		Sprite punishSheet = new Sprite(Image.loadImage("/sprite/trap.png"));
		
		//Player animation sprites
		movePlayerDown = new BufferedImage[4];
		movePlayerDown[0] = playerSheet.crop(0, height, width, height);
		movePlayerDown[1] = playerSheet.crop(width, height, width, height);
		movePlayerDown[2] = playerSheet.crop(width*2, height, width, height);
		movePlayerDown[3] = playerSheet.crop(width*3, height, width, height);
		
		movePlayerUp = new BufferedImage[4];
		movePlayerUp[0] = playerSheet.crop(0, 0, width, height);
		movePlayerUp[1] = playerSheet.crop(width, 0, width, height);
		movePlayerUp[2] = playerSheet.crop(width*2, 0, width, height);
		movePlayerUp[3] = playerSheet.crop(width*3, 0, width, height);
		
		movePlayerLeft = new BufferedImage[4];
		movePlayerLeft[0] = playerSheet.crop(0, height*2, width, height);
		movePlayerLeft[1] = playerSheet.crop(width, height*2, width, height);
		movePlayerLeft[2] = playerSheet.crop(width*2, height*2, width, height);
		movePlayerLeft[3] = playerSheet.crop(width*3, height*2, width, height);

		
		movePlayerRight = new BufferedImage[4];
		movePlayerRight[0] = playerSheet.crop(0, height*3, width, height);
		movePlayerRight[1] = playerSheet.crop(width, height*3, width, height);
		movePlayerRight[2] = playerSheet.crop(width*2, height*3, width, height);
		movePlayerRight[3] = playerSheet.crop(width*3, height*3, width, height);
		
		//Other sprites
		player = playerSheet.crop(0, height, width, height);
		floor = floorSheet.crop(0, 0, width, height);
		wall = wallSheet.crop(0, 0, width, height);
		enemy = enemySheet.crop(0, 0, width, height);
		key = itemSheet.crop(0, 0, width, height);
		door = doorSheet.crop(0, 0, width, height);
		punishment = punishSheet.crop(0, 0, width, height);
	}
}
