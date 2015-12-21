package util;

import java.util.Random;

import org.lwjgl.glfw.GLFW;

public class Global {
	public static int width = 1280, height = 720, margin = 500, length;
	public static boolean fullscreen = false;
	public static long window;
	public static boolean running;

	public static boolean cheats = true;
	public static boolean godmode = false;
	public static boolean bulletstorm = false;

	public static Random random = new Random();

	public static int player_size = 5;

	public static float linear_bullet_default_speed = 0.5f;

	public static float player_init_bullet_speed = 0.3f;
	public static int player_init_bullet_delay = 350;

	public static float player_bullet_dir = (float) (Math.PI / 2);
	public static float player_bullet_speed = 0.4f;
	public static float player_bullet_size = 5;
	public static int player_bullet_delay = 200;

	public static int player_wtf_bullet_delay = 100;

	public static float enemy_bullet_default_size = 5;
	public static float enemy_bullet_default_speed = 0.4f;

	public static int shooter_enemy_bullet_delay = 500;
	public static float shooter_enemy_bullet_speed = 0.4f;

	public static float boss_enemy_bullet_speed = 0.2f;

	public static int return_boss_size = 50;
	public static int return_boss_bullet_stream = 10;
	public static int return_boss_bullet_count = 20;
	public static int return_boss_bullet_delay = 350;
	public static int return_boss_bullet_size = 3;
	public static float return_boss_bullet_acc = -0.00025f;
	public static float return_boss_bullet_speed = 0.55f;
	public static float return_boss_bullet_rotate = (float) (Math.PI / 18);
	public static int return_boss_bullet_wave_delay = 8000;

	public static class Dir {
		public static final float UP = (float) (Math.PI / 2),
				DOWN = (float) (3 * Math.PI / 2), LEFT = (float) Math.PI,
				RIGHT = 0, PI2 = (float) (Math.PI * 2);
	}

	public static void update() {
		if (Keyboard.isKeyPressed(GLFW.GLFW_KEY_1)) {
			System.out.println("BREAK POINT");
		}
	}
}
