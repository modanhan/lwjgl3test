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
								Global.player_bullet_delay, +2 * Global.player_bullet_cone_offset2_x,
								-2 * Global.player_bullet_cone_offset2_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle2),
								Global.player_bullet_delay);
						addBullet(new PlayerLinearBullet(Global.Dir.UP - Global.player_bullet_cone_angle2),
								Global.player_bullet_delay, -2 * Global.player_bullet_cone_offset2_x,
								+2 * Global.player_bullet_cone_offset2_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle2),
								Global.player_bullet_delay, +2 * Global.player_bullet_cone_offset2_x,
								+2 * Global.player_bullet_cone_offset2_y);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle2),
								Global.player_bullet_delay);
						addBullet(new PlayerLinearBullet(Global.Dir.UP + Global.player_bullet_cone_angle2),
								Global.player_bullet_delay, -2 * Global.player_bullet_cone_offset2_x,
								-2 * Global.player_bullet_cone_offset2_y);
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
				} },
				{
						/**
						 * seeker, level 0
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								addBullet(new PlayerLinearBullet(Global.player_init_bullet_speed, Global.Dir.UP),
										Global.player_init_bullet_delay);
							}
						},
						/**
						 * seeker, level 1
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								addBullet(new PlayerLinearBullet(Global.Dir.UP), Global.player_bullet_delay);
								addBullet(new PlayerSeekerBullet(Global.Dir.UP), Global.player_bullet_delay);
							}
						},
						/**
						 * seeker, level 2
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								addBullet(new PlayerLinearBullet(Global.Dir.UP), Global.player_bullet_delay);
								addBullet(new PlayerSeekerBullet(Global.Dir.UP + Global.player_bullet_cone_angle1),
										Global.player_bullet_delay);
								addBullet(new PlayerSeekerBullet(Global.Dir.UP - Global.player_bullet_cone_angle1),
										Global.player_bullet_delay);
							}
						},
						/**
						 * seeker, level 3
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								addBullet(new PlayerSeekerBullet(Global.Dir.UP + Global.player_bullet_cone_angle1),
										Global.player_bullet_delay);
								addBullet(new PlayerSeekerBullet(Global.Dir.UP - Global.player_bullet_cone_angle1),
										Global.player_bullet_delay);
								addBullet(new PlayerSeekerBullet(Global.Dir.UP + Global.player_bullet_cone_angle2),
										Global.player_bullet_delay);
								addBullet(new PlayerSeekerBullet(Global.Dir.UP - Global.player_bullet_cone_angle2),
										Global.player_bullet_delay);

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
										Global.player_bullet_delay);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 2),
										Global.player_bullet_delay);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 3),
										Global.player_bullet_delay);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 4),
										Global.player_bullet_delay);
								addBullet(new PlayerSeekerBullet(angle), Global.player_bullet_delay);

							}
						},
						/**
						 * seeker, level 5
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								float angle = Global.Dir.UP;
								addBullet(new PlayerSeekerBullet(angle), Global.player_bullet_short_delay, 0);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 1),
										Global.player_bullet_short_delay, Global.player_bullet_short_delay / 5);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 2),
										Global.player_bullet_short_delay, Global.player_bullet_short_delay / 5 * 2);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 3),
										Global.player_bullet_short_delay, Global.player_bullet_short_delay / 5 * 3);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 4),
										Global.player_bullet_short_delay, Global.player_bullet_short_delay / 5 * 4);
							}
						},
						/**
						 * seeker, level 6
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								float angle = Global.Dir.UP;
								addBullet(new PlayerSeekerBullet(angle), Global.player_bullet_short_delay, 0);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 6 * 1),
										Global.player_bullet_short_delay, Global.player_bullet_short_delay / 6);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 6 * 2),
										Global.player_bullet_short_delay, Global.player_bullet_short_delay / 6 * 2);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 6 * 3),
										Global.player_bullet_short_delay, Global.player_bullet_short_delay / 6 * 3);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 6 * 4),
										Global.player_bullet_short_delay, Global.player_bullet_short_delay / 6 * 4);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 6 * 5),
										Global.player_bullet_short_delay, Global.player_bullet_short_delay / 6 * 5);
							}
						},
						/**
						 * seeker, level 7
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								float angle = Global.Dir.UP;
								addBullet(new PlayerSeekerBullet(angle), Global.player_bullet_short_delay, 0);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 7 * 1),
										Global.player_bullet_short_delay, Global.player_bullet_short_delay / 7);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 7 * 2),
										Global.player_bullet_short_delay, Global.player_bullet_short_delay / 7 * 2);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 7 * 3),
										Global.player_bullet_short_delay, Global.player_bullet_short_delay / 7 * 3);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 7 * 4),
										Global.player_bullet_short_delay, Global.player_bullet_short_delay / 7 * 4);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 7 * 5),
										Global.player_bullet_short_delay, Global.player_bullet_short_delay / 7 * 5);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 7 * 6),
										Global.player_bullet_short_delay, Global.player_bullet_short_delay / 7 * 6);
							
							}
						},
						/**
						 * seeker, level 8
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								float angle = Global.Dir.UP;
								addBullet(new PlayerSeekerBullet(angle), Global.player_bullet_short_delay, 0);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 7 * 1),
										Global.player_bullet_short_delay, Global.player_bullet_short_delay / 7);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 7 * 2),
										Global.player_bullet_short_delay, Global.player_bullet_short_delay / 7 * 2);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 7 * 3),
										Global.player_bullet_short_delay, Global.player_bullet_short_delay / 7 * 3);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 7 * 4),
										Global.player_bullet_short_delay, Global.player_bullet_short_delay / 7 * 4);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 7 * 5),
										Global.player_bullet_short_delay, Global.player_bullet_short_delay / 7 * 5);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 7 * 6),
										Global.player_bullet_short_delay, Global.player_bullet_short_delay / 7 * 6);

							}
						},
						/**
						 * seeker, level 9
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								float angle = Global.Dir.UP;
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 12 * 0),
										Global.player_bullet_burst_cooldown, 0*Global.player_bullet_burst_delay);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 12 * 1),
										Global.player_bullet_burst_cooldown, 1*Global.player_bullet_burst_delay);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 12 * 2),
										Global.player_bullet_burst_cooldown, 2*Global.player_bullet_burst_delay);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 12 * 3),
										Global.player_bullet_burst_cooldown, 3*Global.player_bullet_burst_delay);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 12 * 4),
										Global.player_bullet_burst_cooldown, 0*Global.player_bullet_burst_delay);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 12 * 5),
										Global.player_bullet_burst_cooldown, 1*Global.player_bullet_burst_delay);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 12 * 6),
										Global.player_bullet_burst_cooldown, 2*Global.player_bullet_burst_delay);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 12 * 7),
										Global.player_bullet_burst_cooldown, 3*Global.player_bullet_burst_delay);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 12 * 8),
										Global.player_bullet_burst_cooldown, 0*Global.player_bullet_burst_delay);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 12 * 9),
										Global.player_bullet_burst_cooldown, 1*Global.player_bullet_burst_delay);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 12 * 10),
										Global.player_bullet_burst_cooldown, 2*Global.player_bullet_burst_delay);
								addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 12 * 11),
										Global.player_bullet_burst_cooldown, 3*Global.player_bullet_burst_delay);
							}
						},
						/**
						 * seeker, level 10
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								// TODO Auto-generated method stub

							}
						},
						/**
						 * seeker, level 11
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								// TODO Auto-generated method stub

							}
						},
						/**
						 * seeker, level 12
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								// TODO Auto-generated method stub

							}
						},
						/**
						 * seeker, level 13
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								// TODO Auto-generated method stub

							}
						},
						/**
						 * seeker, level 14
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								// TODO Auto-generated method stub

							}
						} },
				{
						/**
						 * laser, level 0
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								addBullet(new PlayerLinearBullet(Global.player_init_bullet_speed, Global.Dir.UP),
										Global.player_init_bullet_delay);
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
							}
						},
						/**
						 * laser, level 7
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								addBullet(new PlayerLaser(Global.Dir.UP, 5, Global.player_wide_laser_size), Global.player_laser_delay);	
							}
						},
						/**
						 * laser, level 8
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								addBullet(new PlayerLaser(Global.Dir.UP, 5, Global.player_wide_laser_size), Global.player_laser_delay);	
							}
						},
						/**
						 * laser, level 9
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								addBullet(new PlayerLaser(Global.Dir.UP, 5, Global.player_wide_laser_size), Global.player_laser_delay);	
							}
						},
						/**
						 * laser, level 10
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								addBullet(new PlayerLaser(Global.Dir.UP, 5, Global.player_wide_laser_size), Global.player_laser_delay);	
							}
						},
						/**
						 * laser, level 11
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								addBullet(new PlayerLaser(Global.Dir.UP, 5, Global.player_wide_laser_size), Global.player_laser_delay);	
							}
						},
						/**
						 * laser, level 12
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								addBullet(new PlayerLaser(Global.Dir.UP, 5, Global.player_wide_laser_size), Global.player_laser_delay);	
							}
						},
						/**
						 * laser, level 13
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								addBullet(new PlayerLaser(Global.Dir.UP, 5, Global.player_wide_laser_size), Global.player_laser_delay);	
							}
						},
						/**
						 * laser, level 14
						 */
						new Attack(Game.player, true) {

							@Override
							public void init() {
								addBullet(new PlayerLaser(Global.Dir.UP, 5, Global.player_wide_laser_size), Global.player_laser_delay);	
							}
						} } };
		pa = a;
	}

	private static Attack[][] pa;
}
