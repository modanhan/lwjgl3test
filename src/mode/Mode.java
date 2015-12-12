package mode;

/**
 * 
 * Defines the game's super mode.
 * e.g. Main menu mode, level select mode, game mode, etc.
 * 
 * Each Mode should handle mode switching, or minimal
 * functionalities, additional
 * functionalities should be handled by other classes.
 *
 */
public interface Mode {
	void update();
}
