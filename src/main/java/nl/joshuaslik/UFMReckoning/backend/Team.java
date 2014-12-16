package nl.joshuaslik.UFMReckoning.backend;

import java.util.ArrayList;

/**
 * The Team Object stores all players in a given team. It divides them into
 * active and bench players.
 * 
 * The Object also keeps track of the team statistics.
 * 
 * @author Sander Benoist
 *
 */
public class Team {
	private ArrayList<Player> activePlayers = new ArrayList<Player>();
	private ArrayList<Player> benchPlayers = new ArrayList<Player>();
	private Player teamCaptain;
	private String teamName, coachName, id;
	int totalWins, totalLosses, totalDraws, points, goalsagaints, ranking, totalGoals = 0;
	int attackPower, defencePower, stamina = 0;

	/**
	 * Initialises the Object
	 * 
	 * @param tmName
	 *            is the team name.
	 * @param cchName
	 *            is the coach name.
	 */
	public Team(String id, String tmName, String cchName) {
		this.id = id;
		teamName = tmName;
		coachName = cchName;
	}

	/**
	 * Adds a player to the Active Player list if the team has less than 11
	 * active players and the player aint already in the list.
	 * 
	 * When a goalkeeper is added, the method checks if the team doesn't already
	 * have an active goalkeeper.
	 * 
	 * Also updates the team statistics.
	 * 
	 * @param player
	 *            is a Player Object
	 */
	public void addActivePlayer(Player player) {
		if (!activePlayers.contains(player) && activePlayers.size() < 11) {

			if (player instanceof Goalkeeper && !checkActiveGoalkeeper()) {
				activePlayers.add(player);
			}

			if (player instanceof Fieldplayer) {
				activePlayers.add(player);

				attackPower += ((Fieldplayer) player).getAttackPower();
				defencePower += ((Fieldplayer) player).getDefencePower();
				stamina += ((Fieldplayer) player).getStamina();
			}
		}
	}

	/**
	 * Adds a player to the list of Bench Players if the player aint already in
	 * it.
	 * 
	 * @param player
	 *            is a Player Object.
	 */
	public void addBenchPlayer(Player player) {
		if (!benchPlayers.contains(player)) {
			benchPlayers.add(player);
		}
	}

	/**
	 * Checks if a team has an active goalkeeper.
	 * 
	 * @return true when the team has an active goalkeeper,
	 */
	public boolean checkActiveGoalkeeper() {
		for (int i = 0; i < activePlayers.size(); i++) {
			if (activePlayers.get(i) instanceof Goalkeeper) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Removes a player from the Active Player list. Also updates the team
	 * statistics.
	 * 
	 * @param player
	 *            is a Player Object.
	 */
	public void removeActivePlayer(Player player) {
		if (activePlayers.contains(player)) {
			activePlayers.remove(activePlayers.indexOf(player));

			if (player instanceof Fieldplayer) {
				attackPower -= ((Fieldplayer) player).getAttackPower();
				defencePower -= ((Fieldplayer) player).getDefencePower();
				stamina -= ((Fieldplayer) player).getStamina();
			}
		}
	}

	/**
	 * Removes a player from the Bench Player list.
	 * 
	 * @param player
	 *            is a Player Object.
	 */
	public void removeBenchPlayer(Player player) {
		if (benchPlayers.contains(player)) {
			benchPlayers.remove(benchPlayers.indexOf(player));
		}
	}

	/**
	 * Assigns the given player as the Team Captain
	 * 
	 * @param player
	 *            is a Player Object.
	 */
	public void setTeamCaptain(Player player) {
		teamCaptain = player;
	}

	/**
	 * Adds the player to the active player list and removes him from the bench
	 * player list.
	 * 
	 * @param player
	 *            is a Player Object.
	 */
	public void setPlayerActive(Player player) {
		if (!activePlayers.contains(player) && activePlayers.size() < 11) {

			if (player instanceof Goalkeeper && !checkActiveGoalkeeper()) {
				activePlayers.add(player);
			}

			if (player instanceof Fieldplayer) {
				activePlayers.add(player);

				attackPower += ((Fieldplayer) player).getAttackPower();
				defencePower += ((Fieldplayer) player).getDefencePower();
				stamina += ((Fieldplayer) player).getStamina();
			}

			if (benchPlayers.contains(player)) {
				benchPlayers.remove(benchPlayers.indexOf(player));
			}
		}
	}

	/**
	 * Removes the Player from the active playerlist and adds him to the bench
	 * playerlist.
	 * 
	 * @param player
	 *            is a Player Object
	 */
	public void setPlayerBench(Player player) {
		if (!benchPlayers.contains(player)) {
			benchPlayers.add(player);

			if (activePlayers.contains(player)) {
				activePlayers.remove(activePlayers.indexOf(player));

				if (player instanceof Fieldplayer) {
					attackPower -= ((Fieldplayer) player).getAttackPower();
					defencePower -= ((Fieldplayer) player).getDefencePower();
					stamina -= ((Fieldplayer) player).getStamina();
				}
			}
		}
	}

	public boolean equals(Object other) {
		if (other instanceof Team) {
			Team that = (Team) other;
			if(this.teamCaptain != null && that.teamCaptain != null){
				if (this.activePlayers.equals(that.activePlayers)
					&& this.benchPlayers.equals(that.benchPlayers)
					&& this.teamCaptain.equals(that.teamCaptain)
					&& this.teamName.equals(that.teamName)
					&& this.coachName.equals(that.coachName)
					&& (this.totalWins == that.totalWins)
					&& (this.totalLosses == that.totalLosses)
					&& (this.totalDraws == that.totalDraws)
					&& (this.totalGoals == that.totalGoals)
					&& (this.attackPower == that.attackPower)
					&& (this.defencePower == that.defencePower)
					&& (this.stamina == that.stamina)
					&& (this.points == that.points)
					&& (this.ranking == that.ranking)
					&& (this.goalsagaints == that.goalsagaints)) {
				return true;
				}
			}
			else if(this.activePlayers.equals(that.activePlayers)
					&& this.benchPlayers.equals(that.benchPlayers)
					&& (this.teamCaptain == null && that.teamCaptain == null)
					&& this.teamName.equals(that.teamName)
					&& this.coachName.equals(that.coachName)
					&& (this.totalWins == that.totalWins)
					&& (this.totalLosses == that.totalLosses)
					&& (this.totalDraws == that.totalDraws)
					&& (this.totalGoals == that.totalGoals)
					&& (this.attackPower == that.attackPower)
					&& (this.defencePower == that.defencePower)
					&& (this.stamina == that.stamina)
					&& (this.points == that.points)
					&& (this.ranking == that.ranking)
					&& (this.goalsagaints == that.goalsagaints)){
				return true;
			}
		}
		return false;
	}

	public ArrayList<Player> getActivePlayers() {
		return activePlayers;
	}

	public ArrayList<Player> getBenchPlayers() {
		return benchPlayers;
	}

	public String getTeamName() {
		return teamName;
	}

	public Player getTeamCaptain() {
		return teamCaptain;
	}

	public String getCoachName() {
		return coachName;
	}

	public int getTotalWins() {
		return totalWins;
	}

	/**
	 * increments totalwins with 1
	 */
	public void incTotalWins() {
		this.totalWins = this.totalWins + 1;
	}

	public int getTotalLosses() {
		return totalLosses;
	}
	
	public void addGoalsAgainst(int goals){
		goalsagaints = goalsagaints + goals;
	}
	
	public int getGoalsAgainst(){
		return goalsagaints;
	}
	
	public int getRanking(){
		return ranking;
	}
	
	public void setRanking(int ranking){
		this.ranking = ranking;
	}
	
	/**
	 * add points 
	 * @param points to add
	 */
	public void addPoints(int points){
		this.points = points + this.points;
	}
	
	public int getPoints(){
		return points;
	}
	
	/**
	 * increments totalLosses with 1
	 */
	public void incTotalLosses() {
		this.totalLosses = this.totalLosses + 1;
	}

	public int getTotalDraws() {
		return totalDraws;
	}
	
	/**
	 * increments totalDraws with 1
	 */
	public void incTotalDraws() {
		this.totalDraws = this.totalDraws + 1;
	}

	public int getTotalGoals() {
		return totalGoals;
	}
	
	/**
	 * add goals to totalgoals
	 * @param goals to add
	 */
	public void addGoals(int goals) {
		this.totalGoals = this.totalGoals + goals;
	}

	public int getAttackPower() {
		return attackPower;
	}

	public void setAttackPower(int attack) {
		this.attackPower = attack;
	}

	public int getDefencePower() {
		return defencePower;
	}

	public void setDefencePower(int defence) {
		this.defencePower = defence;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}
	
	public String getid(){
		return id;
	}
	
	public int getPlayers(){
		return getActivePlayers().size()+getBenchPlayers().size();
	}
}
