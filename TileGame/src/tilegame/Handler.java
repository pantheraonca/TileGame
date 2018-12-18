package tilegame;

import tilegame.difficulty.DifficultyLevel;
import tilegame.entities.EntityManager;
import tilegame.entities.individuals.Player;
import tilegame.fields.Field;
import tilegame.graphics.PlayerCamera;
import tilegame.input.KeyInputManager;
import tilegame.input.MouseInputManager;
import tilegame.skills.Skills;
import tilegame.userinterface.UIManager;

public class Handler {

	private Game game;
	private Field field;

	public Handler(Game game) { //allows us to just pass one object and access all the rest in here
		this.game = game;
	}

	public KeyInputManager getKeyInputManager() {
		return game.getKeyInputManager();
	}

	public MouseInputManager getMouseInputManager() {
		return game.getMouseInputManager();
	}

	public int getWidth() {
		return game.getWidth();
	}

	public int getHeight() {
		return game.getHeight();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public UIManager getUIManager () {
		return game.getMenuState().getUiManager();
	}

	public PlayerCamera getPlayerCamera() {
		return game.getPlayerCamera();
	}

	public int getVisibilityLevel() {
		return game.gameState.getShadow().getVisibilityLevel();
	}

	public void setVisibilityLevel(int visibilityLevel) {
		game.gameState.getShadow().setVisibilityLevel(visibilityLevel);
	}

	public UIManager getSettingsUiManager() {
		return game.settingsState.getSettingsUiManager();
	}

	public DifficultyLevel getDifficultyState() {
		return game.getDifficultyState();
	}

	public void setDifficultyState(DifficultyLevel difficultyState) {
		game.setDifficultyState(difficultyState); 
	}

	public void setSettingsUiManager(UIManager settingsUiManager) {
		game.settingsState.setSettingsUiManager(settingsUiManager);
	}

	public boolean[] getSkillArray() {
		return game.settingsState.getSkillArray();
	}

	public void setSkillArray(boolean[] skillArray) {
		game.settingsState.setSkillArray(skillArray); 
	}
	
	public Skills getStamina() {
		return getPlayer().getStamina();
	}

	public void setStamina(Skills stamina) {
		getPlayer().setStamina(stamina);
	}

	public Skills getFighter() {
		return getPlayer().getFighter();
	}

	public void setFighter(Skills fighter) {
		getPlayer().setFighter(fighter);;
	}
	
	public int getDamage() {
		return getPlayer().getDamage();
	}

	public void setDamage(int damage) {
		getPlayer().setDamage(damage);
	}
	
	public EntityManager getEntityManager() {
		return getField().getEntityManager();
	}
	public void setEntityManager(EntityManager entityManager) {
		getField().setEntityManager(entityManager);
	}
	public Player getPlayer() {
		return getEntityManager().getPlayer();
	}

	public void setPlayer(Player player) {
		 getEntityManager().setPlayer(player);
	}
}
