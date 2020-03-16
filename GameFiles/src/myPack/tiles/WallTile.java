package myPack.tiles;

import myPack.graphics.Resource;

public class WallTile extends Tile {
	
	public WallTile(int id) {
		super(Resource.wall, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
	
}
