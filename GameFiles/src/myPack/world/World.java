package myPack.world;

import java.awt.Graphics;

import myPack.Handler;
import myPack.tiles.Tile;
import myPack.utilities.Utilities;

public class World {
	
	private Handler handler;
	private int width, height;
	private int spawnX, spawnY, endX, endY;
	private int[][] tiles;

	public World(Handler handler, String path) {
		this.handler = handler;
		loadWorld(path);
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOff() / Tile.TILE_WIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOff() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
		int yStart = (int) Math.max(0,  handler.getGameCamera().getyOff() / Tile.TILE_HEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOff() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);
		 
		for(int i = yStart; i < yEnd; i++) {
			for(int j = xStart; j < xEnd; j++) {
				getTile(j, i).render(g, (int) (j * Tile.TILE_WIDTH - handler.getGameCamera().getxOff()), 
								(int) (i * Tile.TILE_HEIGHT - handler.getGameCamera().getyOff()));
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null)
			return Tile.floorTile;
		return t;
	}
	
	private void loadWorld(String path) {
		String file = Utilities.loadFileAsString(path);
		String[] arr = file.split("\\s+"); //Split each number in file into separate strings by whitespace
		width = Utilities.parseInt(arr[0]);
		height = Utilities.parseInt(arr[1]);
		spawnX = Utilities.parseInt(arr[2]);
		spawnY = Utilities.parseInt(arr[3]);
		endX = Utilities.parseInt(arr[4]);
		endY = Utilities.parseInt(arr[5]);
		
		tiles = new int[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tiles[x][y] = Utilities.parseInt(arr[(x + y * width) + 6]);
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getSpawnX() {
		return spawnX;
	}

	public int getSpawnY() {
		return spawnY;
	}
	
	public int getEndX() {
		return endX;
	}

	public int getEndY() {
		return endY;
	}
}
