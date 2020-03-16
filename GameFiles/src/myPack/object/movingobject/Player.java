package myPack.object.movingobject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import myPack.Handler;
import myPack.graphics.Animation;
import myPack.graphics.Resource;

public class Player extends MovingObject {

	private int score = 100;
	
	//Animation
	private Animation moveDown, moveUp, moveLeft, moveRight;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, MovingObject.DEFAULT_OBJECT_WIDTH, MovingObject.DEFAULT_OBJECT_HEIGHT);
		
		moveDown = new Animation(124, Resource.movePlayerDown);
		moveUp = new Animation(124, Resource.movePlayerUp);
		moveLeft = new Animation(124, Resource.movePlayerLeft);
		moveRight = new Animation(124, Resource.movePlayerRight);
	}

	@Override
	public void tick() {
		
		//Animation
		moveDown.tick();
		moveUp.tick();
		moveLeft.tick();
		moveRight.tick();
		
		getInput();
		move();
		handler.getGameCamera().playerCamera(this);
		
		//Old movement
		/*if(game.getKey().up)
			y -= 1;
		if(game.getKey().down)
			y += 1;
		if(game.getKey().left)
			x -= 1;
		if(game.getKey().right)
			x += 1;*/
		
	}
	
	private void getInput() {
		MoveX = 0;
		MoveY = 0;
		
		if(handler.getKey().up) {
			MoveY = -1;
		}
			
		if(handler.getKey().down) {
			MoveY = 1;
		}
		
		if(handler.getKey().left) {
			MoveX = -1;
		}
		
		if(handler.getKey().right) {
			MoveX = 1;
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrAnimation(), (int) (x - handler.getGameCamera().getxOff()), 
									(int) (y - handler.getGameCamera().getyOff()), width, height, null);
	}
	
	private BufferedImage getCurrAnimation() {
		if(MoveX != 0 || MoveY != 0) {
			if(MoveX < 0) {
				return moveLeft.getCurrFrame();
			}else if(MoveX > 0) {
				return moveRight.getCurrFrame();
			}else if(MoveY < 0) {
				return moveUp.getCurrFrame();
			}else {
				return moveDown.getCurrFrame();
			}
		}else {
			return Resource.player;
		}
		
		
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
}
