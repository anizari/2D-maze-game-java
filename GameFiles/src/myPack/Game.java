package myPack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import myPack.gamewindow.GameWindow;
import myPack.graphics.GameCamera;
import myPack.graphics.Resource;
import myPack.key.KeyMngr;
import myPack.state.GameState;
import myPack.state.MenuState;
import myPack.state.OptionState;
import myPack.state.State;

public class Game implements Runnable {
	private GameWindow window;
	private int width, height;
	public String title;
	
	private Thread thread;
	private boolean running = false;
	
	private BufferStrategy buff;
	private Graphics g;
	
	//STATES
	private State gameState;
	private State menuState;
	private State optionState;
	
	//INPUT
	private KeyMngr key;
	
	//CAMERA
	private GameCamera cam;
	
	//HANDLER
	private Handler handler;
	
	
	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		key = new KeyMngr();
		
	}
	
	private void initialize() {
		window = new GameWindow(title, width, height);
		window.getFrame().addKeyListener(key);
		Resource.init();
		
		handler = new Handler(this);
		cam = new GameCamera(handler, 0, 0);
		
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		optionState = new OptionState(handler);
		State.setState(gameState);
	}
	
	
	
	private void tick() {
		key.tick();
		
		if(State.getState() != null)
			State.getState().tick();
	}
	
	private void render() {
		buff = window.getCanvas().getBufferStrategy();
		if(buff == null) {
			window.getCanvas().createBufferStrategy(3);	
			return;
		}
		
		g = buff.getDrawGraphics();
		
		g.clearRect(0, 0, width, height);
		//Draw
		
		if(State.getState() != null)
			State.getState().render(g);
		
		//End Draw
		buff.show();
		g.dispose();
		
	}
	
	public void run() {
		initialize();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}
			
			
			if(timer >= 1000000000) {
				System.out.println("FPS: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
	}
	
	public KeyMngr getKey() {
		return key;
	}
	
	public GameCamera getGameCamera() {
		return cam;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public synchronized void start() {
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
