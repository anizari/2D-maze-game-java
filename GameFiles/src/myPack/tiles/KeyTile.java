package myPack.tiles;

import myPack.graphics.Resource;

public class KeyTile extends Tile {
	
	public KeyTile(int id) {
		super(Resource.key, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
