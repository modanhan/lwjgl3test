package game;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glEnable;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Consumer;

import org.lwjgl.opengl.GL13;

import events.EventHandler;
import game.lib.Enemies;
import game.object.Enemy;
import game.object.GameObject;
import game.object.Player;
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

	public static LinkedList<GameObject> enemies, playerbullets, enemybullets;
	private static FrameBuffer hbuf, vbuf;
	private static Shader hblur, vblur;
	private static Texture main;

	public static void init() {
		player = new Player();
		enemies = new LinkedList<GameObject>();
		playerbullets = new LinkedList<GameObject>();
		enemybullets = new LinkedList<GameObject>();
		initGraphics();

		spawnEnemies();

	}

	/**
	 * this is just for testing.. remove this
	 */
	private static void spawnEnemies() {
		EventHandler.add(new Enemy.EnemySpawnEvent(1000, new Enemies.BossEnemy(Global.width / 2, Global.length * 3 / 4)));
	}

	private static void initGraphics() {
		if (hbuf == null)
			hbuf = new FrameBuffer(Global.width, Global.height);
		if (vbuf == null)
			vbuf = new FrameBuffer(Global.width, Global.height);
		if (hblur == null)
			hblur = new Shader(new File("shaders/mainvertex.glsl"), new File("shaders/blurhfragment2.glsl"));
		Shader.use(hblur);
		hblur.setUniformi("texture", 0);
		if (vblur == null)
			vblur = new Shader(new File("shaders/mainvertex.glsl"), new File("shaders/blurvfragment2.glsl"));
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
		Collision.update();
		render();
	}

	private static void updateList(Iterator<GameObject> it) {
		while (it.hasNext()) {
			GameObject go = it.next();
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
		
		

		Graphics.blendAdditive();
		Graphics.renderPass(hblur, hbuf, vbuf);
		Graphics.renderPass(vblur, vbuf, hbuf);
		Graphics.renderPass(hblur, hbuf, vbuf);
		Graphics.renderPass(vblur, vbuf, hbuf);
		Graphics.renderPass(hblur, hbuf, vbuf);
		Graphics.renderPass(vblur, vbuf);
		Graphics.reset();
		
	}

	public static void addEnemy(GameObject g) {
		enemies.add(g);
	}

	public static void addEnemyBullet(GameObject g) {
		enemybullets.add(g);
	}

	public static void addPlayerBullet(GameObject g) {
		playerbullets.add(g);
	}

	public static void removeAll(Collection<? extends GameObject> e) {
		e.forEach(new Consumer<GameObject>() {
			@Override
			public void accept(GameObject t) {
				t.remove();
			}
		});
	}
}
