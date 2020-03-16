package myPack.state;

import java.awt.Graphics;

import myPack.Handler;
import myPack.object.movingobject.Player;
import myPack.tiles.Tile;
import myPack.world.World;

public class GameState extends State {

	private Player player;
	private World world;
	
	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
		player = new Player(handler, world.getSpawnX() * Tile.TILE_WIDTH, world.getSpawnY() * Tile.TILE_HEIGHT);
		
		
	}
	
	@Override
	public void tick() {
		world.tick();
		player.tick();
		
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		player.render(g);
		
		
	}

}
