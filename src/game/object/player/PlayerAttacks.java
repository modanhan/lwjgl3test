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
				addBullet(new PlayerLinearBullet(
						Global.player_init_bullet_speed, Global.Dir.UP),
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
				addBullet(new PlayerLinearBullet(Global.Dir.UP),
						Global.player_bullet_delay, 5, 0);
				addBullet(new PlayerLinearBullet(Global.Dir.UP),
						Global.player_bullet_delay, -5, 0);

			}
		},
		/**
		 * linear, level 3
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				addBullet(new PlayerLinearBullet(Global.Dir.UP
						- Global.player_bullet_cone_angle1),
						Global.player_bullet_delay);
				addBullet(new PlayerLinearBullet(Global.Dir.UP
						+ Global.player_bullet_cone_angle1),
						Global.player_bullet_delay);
				addBullet(new PlayerLinearBullet(Global.Dir.UP),
						Global.player_bullet_delay);
			}
		},
		/**
		 * linear, level 4
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				addBullet(new PlayerLinearBullet(Global.Dir.UP
						- Global.player_bullet_cone_angle0),
						Global.player_bullet_delay);
				addBullet(new PlayerLinearBullet(Global.Dir.UP
						+ Global.player_bullet_cone_angle0),
						Global.player_bullet_delay);
				addBullet(new PlayerLinearBullet(Global.Dir.UP
						- Global.player_bullet_cone_angle1),
						Global.player_bullet_delay);
				addBullet(new PlayerLinearBullet(Global.Dir.UP
						+ Global.player_bullet_cone_angle1),
						Global.player_bullet_delay);
			}
		},
		/**
		 * linear, level 5
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				addBullet(new PlayerLinearBullet(Global.Dir.UP
						- Global.player_bullet_cone_angle1),
						Global.player_bullet_delay);
				addBullet(new PlayerLinearBullet(Global.Dir.UP
						+ Global.player_bullet_cone_angle1),
						Global.player_bullet_delay);
				addBullet(new PlayerLinearBullet(Global.Dir.UP),
						Global.player_bullet_delay);
				addBullet(new PlayerLinearBullet(Global.Dir.UP
						- Global.player_bullet_cone_angle2),
						Global.player_bullet_delay);
				addBullet(new PlayerLinearBullet(Global.Dir.UP
						+ Global.player_bullet_cone_angle2),
						Global.player_bullet_delay);
			}
		},
		/**
		 * linear, level 6
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				addBullet(new PlayerLinearBullet(Global.Dir.UP
						- Global.player_bullet_cone_angle1),
						Global.player_bullet_delay);
				addBullet(new PlayerLinearBullet(Global.Dir.UP
						+ Global.player_bullet_cone_angle1),
						Global.player_bullet_delay);
				addBullet(new PlayerLinearBullet(Global.Dir.UP),
						Global.player_bullet_delay);

			}
		},
		/**
		 * linear, level 7
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				addBullet(new PlayerLinearBullet(Global.Dir.UP
						- Global.player_bullet_cone_angle1),
						Global.player_bullet_delay);
				addBullet(new PlayerLinearBullet(Global.Dir.UP
						+ Global.player_bullet_cone_angle1),
						Global.player_bullet_delay);
				addBullet(new PlayerLinearBullet(Global.Dir.UP
						- Global.player_bullet_cone_angle0),
						Global.player_bullet_delay);
				addBullet(new PlayerLinearBullet(Global.Dir.UP
						+ Global.player_bullet_cone_angle0),
						Global.player_bullet_delay);

			}
		},
		/**
		 * linear, level 8
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				// TODO Auto-generated method stub

			}
		},
		/**
		 * linear, level 9
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				// TODO Auto-generated method stub

			}
		},
		/**
		 * linear, level 10
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				// TODO Auto-generated method stub

			}
		},
		/**
		 * linear, level 11
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				// TODO Auto-generated method stub

			}
		},
		/**
		 * linear, level 12
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				// TODO Auto-generated method stub

			}
		},
		/**
		 * linear, level 13
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				// TODO Auto-generated method stub

			}
		},
		/**
		 * linear, level 14
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				// TODO Auto-generated method stub

			}
		} }, {
		/**
		 * seeker, level 0
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				addBullet(new PlayerLinearBullet(
						Global.player_init_bullet_speed, Global.Dir.UP),
						Global.player_init_bullet_delay);
			}
		},
		/**
		 * seeker, level 1
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				addBullet(new PlayerSeekerBullet(Global.Dir.UP),
						Global.player_bullet_delay);
			}
		},
		/**
		 * seeker, level 2
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				addBullet(new PlayerSeekerBullet(Global.Dir.UP
						+ Global.player_bullet_cone_angle1),
						Global.player_bullet_delay);
				addBullet(new PlayerSeekerBullet(Global.Dir.UP
						- Global.player_bullet_cone_angle1),
						Global.player_bullet_delay);
			}
		},
		/**
		 * seeker, level 3
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				addBullet(new PlayerSeekerBullet(Global.Dir.UP
						+ Global.player_bullet_cone_angle1),
						Global.player_bullet_delay);
				addBullet(new PlayerSeekerBullet(Global.Dir.UP
						- Global.player_bullet_cone_angle1),
						Global.player_bullet_delay);
				addBullet(new PlayerSeekerBullet(Global.Dir.UP
						+ Global.player_bullet_cone_angle2),
						Global.player_bullet_delay);
				addBullet(new PlayerSeekerBullet(Global.Dir.UP
						- Global.player_bullet_cone_angle2),
						Global.player_bullet_delay);

			}
		},
		/**
		 * seeker, level 4
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				float angle = Global.Dir.PI2 / 4;
				addBullet(new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5),
						Global.player_bullet_delay);
				addBullet(
						new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 2),
						Global.player_bullet_delay);
				addBullet(
						new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 3),
						Global.player_bullet_delay);
				addBullet(
						new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 4),
						Global.player_bullet_delay);
				addBullet(new PlayerSeekerBullet(angle),
						Global.player_bullet_delay);

			}
		},
		/**
		 * seeker, level 5
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				float angle = Global.Dir.PI2 / 4;
				addBullet(new PlayerSeekerBullet(angle),
						Global.player_bullet_short_delay, 0);
				addBullet(
						new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 1),
						Global.player_bullet_short_delay,
						Global.player_bullet_short_delay / 5);
				addBullet(
						new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 2),
						Global.player_bullet_short_delay,
						Global.player_bullet_short_delay / 5 * 2);
				addBullet(
						new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 3),
						Global.player_bullet_short_delay,
						Global.player_bullet_short_delay / 5 * 3);
				addBullet(
						new PlayerSeekerBullet(angle + Global.Dir.PI2 / 5 * 4),
						Global.player_bullet_short_delay,
						Global.player_bullet_short_delay / 5 * 4);
			}
		},
		/**
		 * seeker, level 6
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				// TODO Auto-generated method stub

			}
		},
		/**
		 * seeker, level 7
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				// TODO Auto-generated method stub

			}
		},
		/**
		 * seeker, level 8
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				// TODO Auto-generated method stub

			}
		},
		/**
		 * seeker, level 9
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				// TODO Auto-generated method stub

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
		} }, {
		/**
		 * laser, level 0
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				addBullet(new PlayerLinearBullet(
						Global.player_init_bullet_speed, Global.Dir.UP),
						Global.player_init_bullet_delay);
			}
		},
		/**
		 * laser, level 1
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				addBullet(new PlayerLaser(Global.Dir.UP),
						Global.player_laser_delay);
			}
		},
		/**
		 * laser, level 2
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				addBullet(new PlayerLaser(Global.Dir.UP
						- Global.player_bullet_cone_angle0),
						Global.player_laser_delay);
				addBullet(new PlayerLaser(Global.Dir.UP
						+ Global.player_bullet_cone_angle0),
						Global.player_laser_delay);
			}
		},
		/**
		 * laser, level 3
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				addBullet(new PlayerLaser(Global.Dir.UP),
						Global.player_laser_delay);
			}
		},
		/**
		 * laser, level 4
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				// TODO Auto-generated method stub

			}
		},
		/**
		 * laser, level 5
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				// TODO Auto-generated method stub

			}
		},
		/**
		 * laser, level 6
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				// TODO Auto-generated method stub

			}
		},
		/**
		 * laser, level 7
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				// TODO Auto-generated method stub

			}
		},
		/**
		 * laser, level 8
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				// TODO Auto-generated method stub

			}
		},
		/**
		 * laser, level 9
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				// TODO Auto-generated method stub

			}
		},
		/**
		 * laser, level 10
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				// TODO Auto-generated method stub

			}
		},
		/**
		 * laser, level 11
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				// TODO Auto-generated method stub

			}
		},
		/**
		 * laser, level 12
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				// TODO Auto-generated method stub

			}
		},
		/**
		 * laser, level 13
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				// TODO Auto-generated method stub

			}
		},
		/**
		 * laser, level 14
		 */
		new Attack(Game.player, true) {

			@Override
			public void init() {
				// TODO Auto-generated method stub

			}
		} } };
		pa = a;
	}

	private static Attack[][] pa;
}
