package game;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

import org.lwjgl.opengl.GL13;

import game.level.Level;
import game.object.Bullet;
import game.object.GameObject;
import game.object.enemy.Enemy;
import game.object.player.Player;
import game.object.player.PlayerAttacks;
import game.object.player.SideShooterAttacks;
import game.object.powerup.Powerup;
import graphics.FrameBuffer;
import graphics.Graphics;
import graphics.Shader;
import util.Global;
import util.Konami;

/**
 * 
 * Handles the gameplay, this class should only be called by modes that handle
 * gameplay, i.e. GameMode.
 *
 */
public class Game {
	public static Player player;

	private static int level = 0;

	public static LinkedList<Bullet> playerbullets, enemybullets;
	public static LinkedList<Enemy> enemies;
	public static LinkedList<GameObject> visuals;
	public static LinkedList<Powerup> powerups;
	private static FrameBuffer hbuf, vbuf;
	private static Shader hblur, vblur;

	public static void init() {
		player = new Player();

		enemies = new LinkedList<Enemy>();
		playerbullets = new LinkedList<Bullet>();
		enemybullets = new LinkedList<Bullet>();
		visuals = new LinkedList<GameObject>();
		powerups = new LinkedList<Powerup>();

		PlayerAttacks.init();
		SideShooterAttacks.init();
		player.switchAttack(PlayerAttacks.LINEAR, 0);

		initGraphics();

		Konami.init();

		Level.init(level);
	}

	public static void setLevel(int i) {
		level = i;
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
		Global.Textures.load();
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
		updateList(powerups.iterator());
		updateList(visuals.iterator());
		Collision.update();
		render();

		Konami.update();
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
		glPushMatrix();
		glTranslatef((Global.width)/2f,(Global.height)/2f,0);
		glScalef(Global.height/(float)Global.gameheight,Global.height/(float)Global.gameheight,0);
		glTranslatef(-Global.gamewidth/2f,-Global.gameheight/2f,0);

		Graphics.blendOverlay();
		Graphics.set();
		
		if (player != null)
			player.render();
		for (GameObject g : playerbullets)
			g.render();
		for (GameObject g : enemybullets)
			g.render();
		for (GameObject g : enemies)
			g.render();
		for (GameObject g : powerups)
			g.render();
		for (GameObject g : visuals) {
			g.render();
		}

		FrameBuffer.bind(hbuf);
		Graphics.clearBuffers();
		if (player != null)
			player.renderGlow();
		for (GameObject g : playerbullets)
			g.renderGlow();
		for (GameObject g : enemybullets)
			g.renderGlow();
		for (GameObject g : enemies)
			g.renderGlow();
		for (GameObject g : powerups)
			g.renderGlow();
		for (GameObject g : visuals)
			g.renderGlow();
		glPopMatrix();
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

	public static void addPowerup(Powerup g) {
		powerups.add(g);
	}
}
