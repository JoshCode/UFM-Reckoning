package nl.joshuaslik.UFMReckoning.backend;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CompetitionTest {
	
	@Test
	public void testConstructor() {
		Game game1 = new Game();
		Team team1 = new Team("ajax", "Frank de Boer");
		Team team2 = new Team("ado", "Frank de Boer");
		game1.addUser(new Human(team1, "Bryan", 5000));
		game1.addUser(new PC(team2, "pc1", 5000));
		Competition competition1 = new Competition(game1);
		assertEquals(competition1, competition1);
	}
	
	@Test
	public void testDefineplayrounds1() {
		Game game1 = new Game();
		Team team1 = new Team("ajax", "Frank de Boer");
		Team team2 = new Team("ado", "Frank de Boer");
		Team team3 = new Team("az", "piet");
		Team team4 = new Team("psv", "jan");
		game1.addUser(new Human(team1, "Bryan", 5000));
		game1.addUser(new PC(team2, "pc1", 5000));
		game1.addUser(new PC(team3, "pc2", 500));
		game1.addUser(new PC(team4, "pc3", 5000));
		Competition competition1 = new Competition(game1);
		competition1.DefinePlayrounds();
		assertEquals(competition1.getPlayrounds().size(), 6);
	}
	
	@Test
	public void testDefineplayrounds2() {
		Game game1 = new Game();
		Team team1 = new Team("ajax", "Frank de Boer");
		Team team2 = new Team("ado", "Frank de Boer");
		Team team3 = new Team("az", "piet");
		Team team4 = new Team("psv", "jan");
		game1.addUser(new Human(team1, "Bryan", 5000));
		game1.addUser(new PC(team2, "pc1", 5000));
		game1.addUser(new PC(team3, "pc2", 500));
		game1.addUser(new PC(team4, "pc3", 5000));
		Competition competition1 = new Competition(game1);
		competition1.DefinePlayrounds();
		assertEquals(competition1.getPlayrounds().get(1).getMatches().size(), 2);
	}
	
	@Test
	public void testComputeStandings() {
		Game game1 = new Game();
		Team team1 = new Team("ajax", "Frank de Boer");
		Team team2 = new Team("ado", "Frank de Boer");
		Team team3 = new Team("az", "piet");
		Team team4 = new Team("psv", "jan");
		game1.addUser(new Human(team1, "Bryan", 5000));
		game1.addUser(new PC(team2, "pc1", 5000));
		game1.addUser(new PC(team3, "pc2", 500));
		game1.addUser(new PC(team4, "pc3", 5000));
		Competition competition1 = new Competition(game1);
		team3.addPoints(3);
		team4.addGoals(5);
		competition1.ComputeStandings();
		assertEquals(team1.getRanking(), 3);
		assertEquals(team2.getRanking(), 3);
		assertEquals(team3.getRanking(), 1);
		assertEquals(team4.getRanking(), 2);
	}
	
	@Test
	public void testComputeStandings1() {
		Game game1 = new Game();
		Team team1 = new Team("ajax", "Frank de Boer");
		Team team2 = new Team("ado", "Frank de Boer");
		Team team3 = new Team("az", "piet");
		Team team4 = new Team("psv", "jan");
		game1.addUser(new Human(team1, "Bryan", 5000));
		game1.addUser(new PC(team2, "pc1", 5000));
		game1.addUser(new PC(team3, "pc2", 500));
		game1.addUser(new PC(team4, "pc3", 5000));
		Competition competition1 = new Competition(game1);
		team1.addGoals(5);
		team2.addGoals(1);
		team3.addGoals(6);
		team4.addGoals(10);
		
		team1.addPoints(0);
		team2.addPoints(0);
		team3.addPoints(3);
		team4.addPoints(3);
		competition1.ComputeStandings();
		assertEquals(team1.getRanking(), 3);
		assertEquals(team2.getRanking(), 4);
		assertEquals(team3.getRanking(), 2);
		assertEquals(team4.getRanking(), 1);
	}
	
	
	

	
	
}