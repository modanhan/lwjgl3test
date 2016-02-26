package game.object.player;

import util.Global;
import game.Attack;
import game.Game;

public class PlayerAttacks {
	public static final int LINEAR = 0, SEEKER = 1, LASER = 3;

	public static Attack acquire(int type, int level) {
		return pa[type][level];
	}

	public static void init() {
		Attack[][] a = { {
				/**
				 * linear, level 0
				 */
				new Attack(Game.player, true) {

					@Override
					public void init() {
						addBullet(new PlayerLinearBullet(Global.player_init_bullet_speed, Global.Dir.UP),
								Global.player_init_bullet_delay);
					}
				},
				/**
				 * linear, level 1
				 */
				new Attack(Game.player, true) {

					@Override
					public void init() {
						addBullet(new PlayerLinearBullet(), Global.player_bullet_delay);
					}
				},
				/**
				 * linear, level 2
				 */
				new Attack(Game.player, true) {

					@Override
					public void init() {
						addBullet(new PlayerLinearBullet(Global.Dir.UP), Global.player_bullet_delay,
								+Global.player_bullet_offset, 0);
						addBullet(new PlayerLinearBullet(Global.Dir.UP), Global.player_bullet_delay,
								-Global.player_bullet_offset, 0);

					}
				},
				/**
				 * linear, level 3
				 */
				new Attack(Game.player, true) {

					@Override
					public void init() {
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle1),
								Global.player_bullet_delay);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle1),
								Global.player_bullet_delay);
						addBullet(new PlayerLinearBullet(Global.Dir.UP), Global.player_bullet_delay);
					}
				},
				/**
				 * linear, level 4
				 */
				new Attack(Game.player, true) {

					@Override
					public void init() {
						addBullet(new PlayerLinearBullet(Global.Dir.UP), Global.player_bullet_delay,
								+Global.player_bullet_offset, 0);
						addBullet(new PlayerLinearBullet(Global.Dir.UP), Global.player_bullet_delay,
								-Global.player_bullet_offset, 0);
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle1),
								Global.player_bullet_delay);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle1),
								Global.player_bullet_delay);
					}
				},
				/**
				 * linear, level 5
				 */
				new Attack(Game.player, true) {

					@Override
					public void init() {
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle1),
								Global.player_bullet_delay);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle1),
								Global.player_bullet_delay);
						addBullet(new PlayerLinearBullet(Global.Dir.UP), Global.player_bullet_delay);
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle2),
								Global.player_bullet_delay);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle2),
								Global.player_bullet_delay);
					}
				},
				/**
				 * linear, level 6
				 */
				new Attack(Game.player, true) {

					@Override
					public void init() {
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle1),
								Global.player_bullet_delay);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle1),
								Global.player_bullet_delay);
						addBullet(new PlayerLinearBullet(Global.Dir.UP), Global.player_bullet_delay);
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle2),
								Global.player_bullet_delay);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle2),
								Global.player_bullet_delay);

					}
				},
				/**
				 * linear, level 7
				 */
				new Attack(Game.player, true) {

					@Override
					public void init() {
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle1),
								Global.player_bullet_delay);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle1),
								Global.player_bullet_delay);
						addBullet(new PlayerLinearBullet(Global.Dir.UP), Global.player_bullet_delay,
								+Global.player_bullet_offset, 0);
						addBullet(new PlayerLinearBullet(Global.Dir.UP), Global.player_bullet_delay,
								-Global.player_bullet_offset, 0);
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle2),
								Global.player_bullet_delay);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle2),
								Global.player_bullet_delay);

					}
				},
				/**
				 * linear, level 8
				 */
				new Attack(Game.player, true) {

					@Override
					public void init() {
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle1),
								Global.player_bullet_delay);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle1),
								Global.player_bullet_delay);
						addBullet(new PlayerLinearBullet(Global.Dir.UP), Global.player_bullet_delay,
								+Global.player_bullet_offset, 0);
						addBullet(new PlayerLinearBullet(Global.Dir.UP), Global.player_bullet_delay,
								-Global.player_bullet_offset, 0);
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle2),
								Global.player_bullet_delay);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle2),
								Global.player_bullet_delay);
					}
				},
				/**
				 * linear, level 9
				 */
				new Attack(Game.player, true) {

					@Override
					public void init() {
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle1),
								Global.player_bullet_delay, +Global.player_bullet_cone_offset1_x,
								-Global.player_bullet_cone_offset1_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle1),
								Global.player_bullet_delay, -Global.player_bullet_cone_offset1_x,
								+Global.player_bullet_cone_offset1_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle1),
								Global.player_bullet_delay, +Global.player_bullet_cone_offset1_x,
								+Global.player_bullet_cone_offset1_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle1),
								Global.player_bullet_delay, -Global.player_bullet_cone_offset1_x,
								-Global.player_bullet_cone_offset1_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP), Global.player_bullet_delay,
								+Global.player_bullet_offset, 0);
						addBullet(new PlayerLinearBullet(Global.Dir.UP), Global.player_bullet_delay,
								-Global.player_bullet_offset, 0);
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle2),
								Global.player_bullet_delay);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle2),
								Global.player_bullet_delay);

					}
				},
				/**
				 * linear, level 10
				 */
				new Attack(Game.player, true) {

					@Override
					public void init() {
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle2),
								Global.player_bullet_delay, +Global.player_bullet_cone_offset2_x,
								-Global.player_bullet_cone_offset2_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle2),
								Global.player_bullet_delay, -Global.player_bullet_cone_offset2_x,
								+Global.player_bullet_cone_offset2_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle2),
								Global.player_bullet_delay, +Global.player_bullet_cone_offset2_x,
								+Global.player_bullet_cone_offset2_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle2),
								Global.player_bullet_delay, -Global.player_bullet_cone_offset2_x,
								-Global.player_bullet_cone_offset2_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle1),
								Global.player_bullet_delay, +Global.player_bullet_cone_offset1_x,
								-Global.player_bullet_cone_offset1_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle1),
								Global.player_bullet_delay, -Global.player_bullet_cone_offset1_x,
								+Global.player_bullet_cone_offset1_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle1),
								Global.player_bullet_delay, +Global.player_bullet_cone_offset1_x,
								+Global.player_bullet_cone_offset1_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle1),
								Global.player_bullet_delay, -Global.player_bullet_cone_offset1_x,
								-Global.player_bullet_cone_offset1_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP), Global.player_bullet_delay,
								+Global.player_bullet_offset, 0);
						addBullet(new PlayerLinearBullet(Global.Dir.UP), Global.player_bullet_delay,
								-Global.player_bullet_offset, 0);

					}
				},
				/**
				 * linear, level 11
				 */
				new Attack(Game.player, true) {

					@Override
					public void init() {
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle2),
								Global.player_bullet_delay, +Global.player_bullet_cone_offset2_x,
								-Global.player_bullet_cone_offset2_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle2),
								Global.player_bullet_delay, -Global.player_bullet_cone_offset2_x,
								+Global.player_bullet_cone_offset2_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle2),
								Global.player_bullet_delay, +Global.player_bullet_cone_offset2_x,
								+Global.player_bullet_cone_offset2_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle2),
								Global.player_bullet_delay, -Global.player_bullet_cone_offset2_x,
								-Global.player_bullet_cone_offset2_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle1),
								Global.player_bullet_delay, +Global.player_bullet_cone_offset1_x,
								-Global.player_bullet_cone_offset1_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle1),
								Global.player_bullet_delay, -Global.player_bullet_cone_offset1_x,
								+Global.player_bullet_cone_offset1_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle1),
								Global.player_bullet_delay, +Global.player_bullet_cone_offset1_x,
								+Global.player_bullet_cone_offset1_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle1),
								Global.player_bullet_delay, -Global.player_bullet_cone_offset1_x,
								-Global.player_bullet_cone_offset1_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP), Global.player_bullet_delay,
								+Global.player_bullet_offset, 0);
						addBullet(new PlayerLinearBullet(Global.Dir.UP), Global.player_bullet_delay,
								-Global.player_bullet_offset, 0);

					}
				},
				/**
				 * linear, level 12
				 */
				new Attack(Game.player, true) {

					@Override
					public void init() {
						
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle2),
								Global.player_bullet_delay, +Global.player_bullet_cone_offset2_x,
								-Global.player_bullet_cone_offset2_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle2),
								Global.player_bullet_delay, -Global.player_bullet_cone_offset2_x,
								+Global.player_bullet_cone_offset2_y);
						
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle2),
								Global.player_bullet_delay, +Global.player_bullet_cone_offset2_x,
								+Global.player_bullet_cone_offset2_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle2),
								Global.player_bullet_delay, -Global.player_bullet_cone_offset2_x,
								-Global.player_bullet_cone_offset2_y);
						
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle1),
								Global.player_bullet_delay, +Global.player_bullet_cone_offset1_x,
								-Global.player_bullet_cone_offset1_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle1),
								Global.player_bullet_delay, -Global.player_bullet_cone_offset1_x,
								+Global.player_bullet_cone_offset1_y);
						
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle1),
								Global.player_bullet_delay, +Global.player_bullet_cone_offset1_x,
								+Global.player_bullet_cone_offset1_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle1),
								Global.player_bullet_delay, -Global.player_bullet_cone_offset1_x,
								-Global.player_bullet_cone_offset1_y);
						
						addBullet(new PlayerLinearBullet(Global.Dir.UP), Global.player_bullet_delay,
								+2 * Global.player_bullet_offset, 0);
						addBullet(new PlayerLinearBullet(Global.Dir.UP), Global.player_bullet_delay);
						addBullet(new PlayerLinearBullet(Global.Dir.UP), Global.player_bullet_delay,
								-2 * Global.player_bullet_offset, 0);

					}
				},
				/**
				 * linear, level 13
				 */
				new Attack(Game.player, true) {

					@Override
					public void init() {
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle2),
								Global.player_bullet_delay, +Global.player_bullet_cone_offset2_x,
								-Global.player_bullet_cone_offset2_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle2),
								Global.player_bullet_delay, -Global.player_bullet_cone_offset2_x,
								+Global.player_bullet_cone_offset2_y);
						
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle2),
								Global.player_bullet_delay, +Global.player_bullet_cone_offset2_x,
								+Global.player_bullet_cone_offset2_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle2),
								Global.player_bullet_delay, -Global.player_bullet_cone_offset2_x,
								-Global.player_bullet_cone_offset2_y);
						
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle1),
								Global.player_bullet_delay, +2 * Global.player_bullet_cone_offset1_x,
								-2 * Global.player_bullet_cone_offset1_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle1),
								Global.player_bullet_delay);
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle1),
								Global.player_bullet_delay, -2 * Global.player_bullet_cone_offset1_x,
								+2 * Global.player_bullet_cone_offset1_y);
						
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle1),
								Global.player_bullet_delay, +2 * Global.player_bullet_cone_offset1_x,
								+2 * Global.player_bullet_cone_offset1_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle1),
								Global.player_bullet_delay);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle1),
								Global.player_bullet_delay, -2 * Global.player_bullet_cone_offset1_x,
								-2 * Global.player_bullet_cone_offset1_y);
						
						addBullet(new PlayerLinearBullet(Global.Dir.UP), Global.player_bullet_delay,
								+2 * Global.player_bullet_offset, 0);
						addBullet(new PlayerLinearBullet(Global.Dir.UP), Global.player_bullet_delay);
						addBullet(new PlayerLinearBullet(Global.Dir.UP), Global.player_bullet_delay,
								-2 * Global.player_bullet_offset, 0);

					}
				},
				/**
				 * linear, level 14
				 */
				new Attack(Game.player, true) {

					@Override
					public void init() {
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle2),
								Global.player_bullet_delay,400, +2 * Global.player_bullet_cone_offset2_x,
								-2 * Global.player_bullet_cone_offset2_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle2),
								Global.player_bullet_delay,400);
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle2),
								Global.player_bullet_delay,400, -2 * Global.player_bullet_cone_offset2_x,
								+2 * Global.player_bullet_cone_offset2_y);
						
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle2),
								Global.player_bullet_delay,400, +2 * Global.player_bullet_cone_offset2_x,
								+2 * Global.player_bullet_cone_offset2_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle2),
								Global.player_bullet_delay,400);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle2),
								Global.player_bullet_delay,400, -2 * Global.player_bullet_cone_offset2_x,
								-2 * Global.player_bullet_cone_offset2_y);
						
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle1),
								Global.player_bullet_delay,200, +2 * Global.player_bullet_cone_offset1_x,
								-2 * Global.player_bullet_cone_offset1_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle1),
								Global.player_bullet_delay,200);
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle1),
								Global.player_bullet_delay,200, -2 * Global.player_bullet_cone_offset1_x,
								+2 * Global.player_bullet_cone_offset1_y);
						
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle1),
								Global.player_bullet_delay,200, +2 * Global.player_bullet_cone_offset1_x,
								+2 * Global.player_bullet_cone_offset1_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle1),
								Global.player_bullet_delay,200);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle1),
								Global.player_bullet_delay,200, -2 * Global.player_bullet_cone_offset1_x,
								-2 * Global.player_bullet_cone_offset1_y);
						
						addBullet(new PlayerLinearBullet(Global.Dir.UP), Global.player_bullet_delay,
								+2 * Global.player_bullet_offset, 0);
						addBullet(new PlayerLinearBullet(Global.Dir.UP), Global.player_bullet_delay);
						addBullet(new PlayerLinearBullet(Global.Dir.UP), Global.player_bullet_delay,
								-2 * Global.player_bullet_offset, 0);

					}
				} },
				{
						/**
						 * seeker, level 0
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								addBullet(new PlayerSeekerBullet(Global.Dir.UP), Global.seeker_bullet_delay_long+250);
							}
						},
						/**
						 * seeker, level 1
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								addBullet(new PlayerSeekerBullet(Global.Dir.UP), Global.seeker_bullet_delay_long);
							}
						},
						/**
						 * seeker, level 2
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								addBullet(new PlayerSeekerBullet(Global.Dir.UP + Global.player_bullet_cone_angle1),
										Global.seeker_bullet_delay_long);
								addBullet(new PlayerSeekerBullet(Global.Dir.UP - Global.player_bullet_cone_angle1),
										Global.seeker_bullet_delay_long);
							}
						},
						/**
						 * seeker, level 3
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								addBullet(new PlayerSeekerBullet(Global.Dir.UP - Global.player_bullet_cone_angle1),
										Global.seeker_bullet_delay_long);
								addBullet(new PlayerSeekerBullet(Global.Dir.UP + Global.player_bullet_cone_angle2),
										Global.seeker_bullet_delay_long);
								addBullet(new PlayerSeekerBullet(Global.Dir.UP - Global.player_bullet_cone_angle2),
										Global.seeker_bullet_delay_long);

							}
						},
						/**
						 * seeker, level 4
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								float angle = Global.Dir.UP;
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5),
										Global.seeker_bullet_delay_long);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 2),
										Global.seeker_bullet_delay_long);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 3),
										Global.seeker_bullet_delay_long);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 4),
										Global.seeker_bullet_delay_long);
								addBullet(new PlayerSeekerBullet(angle), Global.seeker_bullet_delay_long);

							}
						},
						/**
						 * seeker, level 5
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								float angle = Global.Dir.UP;
								addBullet(new PlayerSeekerBullet(angle), Global.seeker_bullet_delay, 0);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 1),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 5);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 2),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 5 * 2);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 3),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 5 * 3);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 4),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 5 * 4);
							}
						},
						/**
						 * seeker, level 6
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								float angle = Global.Dir.UP;
								addBullet(new PlayerSeekerBullet(angle), Global.seeker_bullet_delay, 0);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 6 * 1),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 6);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 6 * 2),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 6 * 2);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 6 * 3),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 6 * 3);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 6 * 4),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 6 * 4);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 6 * 5),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 6 * 5);
							}
						},
						/**
						 * seeker, level 7
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								float angle = Global.Dir.UP;
								addBullet(new PlayerSeekerBullet(angle), Global.seeker_bullet_delay, 0);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 6 * 2),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 6 * 2);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 6 * 3),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 6 * 3);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 6 * 4),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 6 * 4);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 6 * 5),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 6 * 5);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 6 * 1),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 6 * 1);
							
							}
						},
						/**
						 * seeker, level 8
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								float angle = Global.Dir.UP;
								addBullet(new PlayerSeekerBullet(angle), Global.seeker_bullet_delay, 0);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 7 * 1),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 7);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 7 * 2),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 7 * 2);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 7 * 3),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 7 * 3);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 7 * 4),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 7 * 4);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 7 * 5),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 7 * 5);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 7 * 6),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 7 * 6);

							}
						},
						/**
						 * seeker, level 9
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								float angle = Global.Dir.UP;
								for(int i=0;i<18;i++){
									addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 18 * i),
											Global.seeker_bullet_burst_delay_short);
								}								
							}
						},
						/**
						 * seeker, level 10
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								float angle = Global.Dir.UP;
								for(int i=0;i<12;i++){
									addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 12 * i),
											Global.seeker_bullet_burst_delay_short);
								}
								for(int i=0;i<12;i++){
									addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 12 * i),
											Global.seeker_bullet_burst_delay_short,250);
								}
								
							//	addBullet(new PlayerDefenseBullet(Global.Dir.UP),Global.player_defense_bullet_delay);
							//	PlayerDefenseBullet.maxcount = 4;
							}
						},
						/**
						 * seeker, level 11
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								float angle = Global.Dir.UP;
								for(int i=0;i<18;i++){
									addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 18 * i),
											Global.seeker_bullet_burst_delay_short);
								}
								addBullet(new PlayerSeekerBullet(angle), Global.seeker_bullet_delay, 0);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 1),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 5);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 2),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 5 * 2);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 3),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 5 * 3);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 4),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 5 * 4);
							//	addBullet(new PlayerDefenseBullet(Global.Dir.UP),Global.player_defense_bullet_delay);
							//	PlayerDefenseBullet.maxcount = 4;
								

							}
						},
						/**
						 * seeker, level 12
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								float angle = Global.Dir.UP;
								addBullet(new PlayerSeekerBullet(angle), Global.seeker_bullet_delay, 0);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 1),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 5);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 2),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 5 * 2);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 3),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 5 * 3);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 4),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 5 * 4);
								for(int i=0;i<8;i++){
									addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 8 * i),
											Global.seeker_bullet_burst_delay_medium);
								}
								for(int i=0;i<8;i++){
									addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 8 * i),
											Global.seeker_bullet_burst_delay_medium,250);
								}
								for(int i=0;i<8;i++){
									addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 8 * i),
											Global.seeker_bullet_burst_delay_medium,500);
								}
							//	addBullet(new PlayerDefenseBullet(Global.Dir.UP),Global.player_defense_bullet_delay/2);
							//	PlayerDefenseBullet.maxcount = 8;
							}
						},
						/**
						 * seeker, level 13
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								float angle = Global.Dir.UP;
								addBullet(new PlayerSeekerBullet(angle), Global.seeker_bullet_delay, 0);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 1),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 5);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 2),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 5 * 2);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 3),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 5 * 3);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 4),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 5 * 4);
								for(int i=0;i<15;i++){
									addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 15 * i),
											Global.seeker_bullet_burst_delay_short);
								}

							//	addBullet(new PlayerDefenseBullet(Global.Dir.UP),Global.player_defense_bullet_delay/4);
							//	PlayerDefenseBullet.maxcount = 16;
							}
						},
						/**
						 * seeker, level 14
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								float angle = Global.Dir.UP;
								addBullet(new PlayerSeekerBullet(angle), Global.seeker_bullet_delay, 0);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 7 * 1),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 7);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 7 * 2),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 7 * 2);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 7 * 3),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 7 * 3);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 7 * 4),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 7 * 4);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 7 * 5),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 7 * 5);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 7 * 6),
										Global.seeker_bullet_delay, Global.seeker_bullet_delay / 7 * 6);

								for(int i=0;i<120;i++){
									addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 60 * i),
											Global.seeker_bullet_burst_delay*5,40*(i%40));
								}

								
							}
							
						} },
				{
						/**
						 * laser, level 0
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								addBullet(new PlayerLaser(Global.Dir.UP), Global.player_laser_init_delay+250);
							}
						},
						/**
						 * laser, level 1
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								addBullet(new PlayerLaser(Global.Dir.UP), Global.player_laser_init_delay);
							}
						},
						/**
						 * laser, level 2
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								addBullet(new PlayerLaser(Global.Dir.UP), Global.player_laser_delay);
							}
						},
						/**
						 * laser, level 3
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								addBullet(new PlayerLaser(Global.Dir.UP), Global.player_laser_delay,
										+Global.player_laser_offset, 0);
								addBullet(new PlayerLaser(Global.Dir.UP), Global.player_laser_delay,
										-Global.player_laser_offset, 0);							
							}
						},
						/**
						 * laser, level 4
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								addBullet(new PlayerLaser(Global.Dir.UP), Global.player_laser_delay,
										+Global.player_laser_offset, 0);
								addBullet(new PlayerLaser(Global.Dir.UP), Global.player_laser_delay,
										-Global.player_laser_offset, 0);	
							}
						},
						/**
						 * laser, level 5
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								addBullet(new PlayerLaser(Global.Dir.UP), Global.player_laser_delay,
										+Global.player_laser_offset, 0);
								addBullet(new PlayerLaser(Global.Dir.UP), Global.player_laser_delay,
										-Global.player_laser_offset, 0);	
							}
						},
						/**
						 * laser, level 6
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								addBullet(new PlayerLaser(Global.Dir.UP), Global.player_laser_delay,
										+Global.player_laser_offset, 0);
								addBullet(new PlayerLaser(Global.Dir.UP), Global.player_laser_delay,
										-Global.player_laser_offset, 0);	
								addBullet(new PlayerDefenseLaser(), 500);
							}
						},
						/**
						 * laser, level 7
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								addBullet(new PlayerLaser(Global.Dir.UP, 5, Global.player_wide_laser_size), Global.player_laser_delay);	
								addBullet(new PlayerDefenseLaser(), 500);
							}
						},
						/**
						 * laser, level 8
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								addBullet(new PlayerLaser(Global.Dir.UP, 5, Global.player_wide_laser_size), Global.player_laser_delay);	
								addBullet(new PlayerDefenseLaser(), 250);
							}
						},
						/**
						 * laser, level 9
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								addBullet(new PlayerLaser(Global.Dir.UP, 5, Global.player_wide_laser_size), Global.player_laser_delay);	
								addBullet(new PlayerDefenseLaser(), 250);
							}
						},
						/**
						 * laser, level 10
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								addBullet(new PlayerLaser(Global.Dir.UP, 5, Global.player_wide_laser_size), Global.player_laser_delay);	
								addBullet(new PlayerDefenseLaser(), 250);
							}
						},
						/**
						 * laser, level 11
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								addBullet(new PlayerLaser(Global.Dir.UP, 5, Global.player_wide_laser_size), Global.player_laser_delay);	
								addBullet(new PlayerDefenseLaser(), 500/3);
							}
						},
						/**
						 * laser, level 12
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								addBullet(new PlayerLaser(Global.Dir.UP, 5, Global.player_wide_laser_size), Global.player_laser_delay);	
								addBullet(new PlayerDefenseLaser(), 500/3);
							}
						},
						/**
						 * laser, level 13
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								addBullet(new PlayerLaser(Global.Dir.UP, 5, Global.player_wide_laser_size), Global.player_laser_delay);	
								addBullet(new PlayerDefenseLaser(), 500/3);
							}
						},
						/**
						 * laser, level 14
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								addBullet(new PlayerLaser(Global.Dir.UP, 5, Global.player_wide_laser_size), Global.player_laser_delay);	
								addBullet(new PlayerDefenseLaser(), 500/3);
							}
						} } };
		pa = a;
	}

	private static Attack[][] pa;
}
