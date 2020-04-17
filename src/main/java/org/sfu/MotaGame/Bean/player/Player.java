package org.sfu.MotaGame.Bean.player;

import java.awt.*;

import org.sfu.MotaGame.Bean.ImageData;

/**
 * Player
 * This class renders the player and contains all of the relevant
 * variables and constructors
 *
 * @author Christopher Kassner, Alim Nizari, Thomas Chiu, Yuzhuo Ye
 * @version 1.0
 * @since 2020-03-18
 */
public class Player extends Rectangle {

    public boolean moveUp, moveDown, moveLeft, moveRight;
    private int velX = 2;
    private int velY = 2;

    //Animate the player
    private playerAnimation up, down, left, right;
    private ImageData imgData = new ImageData();

    /**
     * This method initialized the player coordinates
     * and size of the player object. The values for the player
     * animations are also initialized.
     *
     * @param x This is the x-coord of the player object
     * @param y This is the y-coord of the player object
     */
    public Player(int x, int y) {
        setBounds(x, y, 32, 32);

        up = new playerAnimation(imgData.getHeroUp(), 124);
        down = new playerAnimation(imgData.getHeroDown(), 124);
        left = new playerAnimation(imgData.getHeroLeft(), 124);
        right = new playerAnimation(imgData.getHeroRight(), 124);
    }

    /**
     * This method updates the player at every tick
     *
     */
    public void tick() {
        up.animate();
        down.animate();
        left.animate();
        right.animate();

        velX = 2;
        velY = 2;
        if (moveUp)
            y -= velY;
        if (moveDown)
            y += velY;
        if (moveLeft)
            x -= velX;
        if (moveRight)
            x += velX;
    }

    public boolean checkCollision(Rectangle boardEntity) {
        return this.getBoundsTop().intersects(boardEntity.getBounds()) ||
                this.getBoundsBottom().intersects(boardEntity.getBounds()) ||
                this.getBoundsLeft().intersects(boardEntity.getBounds()) ||
                this.getBoundsRight().intersects(boardEntity.getBounds());
    }

    /**
     * This method renders the player graphics
     * including animations
     *
     * @param g This parameter allows us to use graphics.
     */
    public void render(Graphics g) {

        if (moveUp) {
            g.drawImage(up.getFrame(), x, y, width, height, null);
        } else if (moveDown) {
            g.drawImage(down.getFrame(), x, y, width, height, null);
        } else if (moveLeft) {
            g.drawImage(left.getFrame(), x, y, width, height, null);
        } else if (moveRight) {
            g.drawImage(right.getFrame(), x, y, width, height, null);
        } else {
            g.drawImage(imgData.getHero(), x, y, width, height, null);
        }

    }

    /**
     * This method gets the x value
     *
     * @return x.
     */
    public double getX() {
        return x;
    }

    /**
     * This method gets the y value
     *
     * @return y
     */
    public double getY() {
        return y;
    }

    /**
     * This method sets the x value
     * @param x setter for x value
     */
    public void setX(double x) {
        this.x = (int) x;
    }

    /**
     * This method sets the y value
     * @param y setter for y value
     */
    public void setY(double y) {
        this.y = (int) y;
    }

    /**
     * This method sets the velX value
     * @param x setter for velX value
     */
    public void setVelX(int x) {
        this.velX = x;
    }

    /**
     * This method sets the velY value
     * @param y setter for velY value
     */
    public void setVelY(int y) {
        this.velY = y;
    }

    public int getVelX() {
        return this.velX;
    }

    public int getVelY() {
        return this.velY;
    }

    @Override
    /**
     * This method gets the bounds of a rectangle
     *
     * @return Rectangle object
     */
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }

    /**
     * This method gets the top bounds of a rectangle
     *
     * @return Rectangle object
     */
    public Rectangle getBoundsTop() {
        return new Rectangle((int) x + (width / 2) - ((width / 2) / 2), (int) y, (int) width / 2, (int) height / 2);
    }

    /**
     * This method gets the bottom bounds of a rectangle
     *
     * @return Rectangle object
     */
    public Rectangle getBoundsBottom() {
        return new Rectangle((int) x + (width / 2) - ((width / 2) / 2), (int) y + (height / 2), (int) width / 2, (int) height / 2);
    }

    /**
     * This method gets the right bounds of a rectangle
     *
     * @return Rectangle object
     */
    public Rectangle getBoundsRight() {
        return new Rectangle((int) x + width - 5, (int) y + 4, (int) 5, (int) height - 8);
    }

    /**
     * This method gets the left bounds of a rectangle
     *
     * @return Rectangle object
     */
    public Rectangle getBoundsLeft() {
        return new Rectangle((int) x, (int) y + 4, 5, height - 8);
    }


}
