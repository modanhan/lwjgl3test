package util;

import java.io.File;
import java.util.Random;

import org.lwjgl.glfw.GLFW;

import graphics.Texture;

public class Global {
	public static int width = 1600, height = 900, margin = 500, length;
	public static int gamewidth = 1080, gameheight = 1080;
	public static boolean fullscreen = false;
	public static long window;
	public static boolean running;

	public static boolean cheats = true;
	public static boolean godmode = false;
	public static boolean bulletstorm = false;

	public static Random random = new Random();

	public static float powerup_radius = 7.5f;
	public static int powerup_switch_time = 2500;
	public static float powerup_h_speed = .002f;
	public static int powerup_h_dist = 75;
	public static float powerup_v_speed = .05f;

	public static int player_size = 5;
	public static float player_sideshooter_size = 2.5f;
	
	public static float linear_bullet_default_speed = 0.3f;

	public static float seeker_bullet_default_speed = 0.5f;

	public static float seeker_bullet_acceleration = 3.0f;
	public static float seeker_bullet_minimum_speed = 0.45f;
	public static float seeker_bullet_maximum_speed = 0.55f;
	public static float seeker_bullet_targetting_time = 1.2f;

	public static float player_bullet_sideshooter_distance = 50f;
	public static float player_laser_sideshooter_distance = 150f;

	public static float player_speed = .3f, player_slow_speed = .125f;
	public static float player_sideshooter_speed = 0.001f;

	public static float player_init_bullet_speed = 0.3f;
	public static int player_init_bullet_delay = 500;

	public static float player_bullet_speed = 0.4f;
	public static float player_bullet_size = 5;
	public static int player_bullet_delay = 375;
	public static int player_bullet_short_delay = 300;//player_bullet_delay/1.25
	public static int player_bullet_burst_delay = 1000;//player_bullet_delay/1.5*2
	public static int player_bullet_extra1_delay = player_bullet_delay*5;
	public static int player_bullet_extra1_count = 10;
	public static int player_bullet_extra2_delay = player_bullet_delay*10;
	public static int player_bullet_extra2_count = 50;
	
	public static int player_bullet_offset = 5;
	
	public static float player_defense_bullet_range = 200;
	public static double player_defense_bullet_dist = 100;
	public static int player_defense_bullet_delay = player_bullet_delay;
	
	public static int player_laser_init_delay = 750;
	public static int player_laser_delay = 500;
	public static int player_laser_size = 5;
	public static int player_wide_laser_size = 10;
	
	public static int player_laser_offset = 5;

	public static float player_defense_laser_range = 100f;
	
	public static float player_bullet_cone_angle1 = .3f;
	public static float player_bullet_cone_offset1_x = (float) Math.cos(player_bullet_cone_angle1)
			* player_bullet_offset;
	public static float player_bullet_cone_offset1_y = (float) Math.sin(player_bullet_cone_angle1)
			* player_bullet_offset;
	public static float player_bullet_cone_angle2 = .55f;
	public static float player_bullet_cone_offset2_x = (float) Math.cos(player_bullet_cone_angle2)
			* player_bullet_offset;
	public static float player_bullet_cone_offset2_y = (float) Math.sin(player_bullet_cone_angle2)
			* player_bullet_offset;
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
		public static final float UP = (float) (Math.PI / 2), DOWN = (float) (3 * Math.PI / 2), LEFT = (float) Math.PI,
				RIGHT = 0, PI2 = (float) (Math.PI * 2);
	}

	public static class Textures {
		public static Texture circle;

		public static void load() {
			circle = new Texture(new File("res/circle.png"));
		}
	}

	public static void update() {
		if (Keyboard.isKeyPressed(GLFW.GLFW_KEY_F1)) {
			System.out.println("BREAK POINT");
		}
	}
}
