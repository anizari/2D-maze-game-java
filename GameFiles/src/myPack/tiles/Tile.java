package myPack.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;



public class Tile {
	
	public static Tile[] tiles = new Tile[265];
	public static Tile floorTile = new FloorTile(0);
	public static Tile wallTile = new WallTile(9);
	public static Tile KeyTile = new KeyTile(7);
	public static Tile doorTile = new DoorTile(2);
	public static Tile punishTile = new PunishTile(8);
	
	public static final int TILE_WIDTH = 32, TILE_HEIGHT = 32;
	
	protected BufferedImage texture;
	protected final int id;
	

	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public int getID() {
		return id;	
	}
	
	
	
}
