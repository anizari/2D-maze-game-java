package myPack.object.movingobject;

import myPack.Handler;
import myPack.object.Object;

public abstract class MovingObject extends Object {
	
	public static final int DEFAULT_OBJECT_WIDTH = 32, DEFAULT_OBJECT_HEIGHT = 32;
	public static final int DEFAULT_SPEED = 1;
	
	protected float MoveX, MoveY;
	protected float speed; //change speed to allow player to move more than 1 tile in 1 turn
	
	
	public MovingObject(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		speed = DEFAULT_SPEED;
		MoveX = 0;
		MoveY = 0;
	}
	
	public void move() {
		x += MoveX;
		y += MoveY;
	}
	
	public float getMoveX() {
		return MoveX;
	}

	public void setMoveX(float moveX) {
		MoveX = moveX;
	}

	public float getMoveY() {
		return MoveY;
	}

	public void setMoveY(float moveY) {
		MoveY = moveY;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	
	
	
	
}
