package game;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glEnable;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

import org.lwjgl.opengl.GL13;

import game.object.Bullet;
import game.object.GameObject;
import game.object.enemy.Enemy;
import game.object.player.Player;
import graphics.FrameBuffer;
import graphics.Graphics;
import graphics.Shader;
import graphics.Texture;
import util.Global;

/**
 * 
 * Handles the gameplay, this class should only be called by modes that handle
 * gameplay, i.e. GameMode.
 *
 */
public class Game {
	public static Player player;

	public static LinkedList<Bullet> playerbullets,
			enemybullets;
	public static LinkedList<Enemy> enemies;
	public static LinkedList<GameObject> visuals;
	private static FrameBuffer hbuf, vbuf;
	private static Shader hblur, vblur;
	private static Texture main;

	public static void init() {
		
		enemies = new LinkedList<Enemy>();
		playerbullets = new LinkedList<Bullet>();
		enemybullets = new LinkedList<Bullet>();
		visuals = new LinkedList<GameObject>();
		
		player = new Player();
		
		initGraphics();
		spawnenemies();
	}
	
	/**
	 * TODO remove this
	 */
	private static void spawnenemies(){
		enemies.add(new Enemy(300,300));
	}

	private static void initGraphics() {
		if (hbuf == null)
			hbuf = new FrameBuffer(Global.width, Global.height);
		if (vbuf == null)
			vbuf = new FrameBuffer(Global.width, Global.height);
		if (hblur == null)
			hblur = new Shader(new File("shaders/mainvertex.glsl"), new File(
					"shaders/blurhfragment2.glsl"));
		Shader.use(hblur);
		hblur.setUniformi("texture", 0);
		if (vblur == null)
			vblur = new Shader(new File("shaders/mainvertex.glsl"), new File(
					"shaders/blurvfragment2.glsl"));
		Shader.use(vblur);
		vblur.setUniformi("texture", 0);
		Shader.use(0);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		if (main == null)
			main = new Texture(new File("res/circle.png"));
		Graphics.reset();
	}

	public static void update() {
		if (player != null) {
			if (player.remove)
				player = null;
			else
				player.update();
		}
		updateList(playerbullets.iterator());
		updateList(enemies.iterator());
		updateList(enemybullets.iterator());
		updateList(visuals.iterator());
		Collision.update();
		render();
	}

	private static void updateList(Iterator<?> it) {
		while (it.hasNext()) {
			GameObject go = (GameObject) it.next();
			if (go.remove) {
				it.remove();
			} else {
				go.update();
			}
		}
	}

	private static void render() {

		Graphics.blendOverlay();
		Graphics.set();

		Texture.bind(main);
		if (player != null)
			player.render();
		for (GameObject g : playerbullets)
			g.render();
		for (GameObject g : enemybullets)
			g.render();
		for (GameObject g : enemies)
			g.render();
		for (GameObject g : visuals) {
			g.render();
		}

		FrameBuffer.bind(hbuf);
		Graphics.clearBuffers();
		Texture.bind(main);
		if (player != null)
			player.renderGlow();
		for (GameObject g : playerbullets)
			g.renderGlow();
		for (GameObject g : enemybullets)
			g.renderGlow();
		for (GameObject g : enemies)
			g.renderGlow();
		for (GameObject g : visuals)
			g.renderGlow();
		Graphics.blendAdditive();
		Graphics.renderPass(hblur, hbuf, vbuf);
		Graphics.renderPass(vblur, vbuf);
		Graphics.reset();

	}

	public static void addEnemy(Enemy g) {
		enemies.add(g);
	}

	public static void addEnemyBullet(Bullet g) {
		enemybullets.add(g);
	}

	public static void addPlayerBullet(Bullet g) {
		playerbullets.add(g);
	}

	public static void addVisuals(GameObject g) {
		visuals.add(g);
	}
}
