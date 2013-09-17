package de.woock.games.breakout.highscore.impl;

import junit.framework.Assert;

import org.junit.Test;

import de.woock.games.breakout.highscore.HighScoreDAO;

public class HighScoreDAOInMemoryTest {

	@Test
	public void When_created_expect_getHighScores_returns_empty_list() {
		HighScoreDAO sut = new HighScoreDAOInMemoryBuilder().Build();
		Assert.assertEquals(0, sut.getHighScores().size());
	}

	@Test
	public void When_saveHighScore_with_legal_parameter_expect_getHighScore_length_equals_1() {
		HighScoreDAO sut = new HighScoreDAOInMemoryBuilder().Build();
		sut.saveHighScore(1, "peter");
		Assert.assertEquals(1, sut.getHighScores().size());
	}

	@Test
	public void When_saveHighScore_with_legal_parameter_expect_getHighScore_contains_object_with_parameter() {
		String expectedName = "peter";
		Integer expectedScore = 1;
		HighScoreDAO sut = new HighScoreDAOInMemoryBuilder().Build();
		sut.saveHighScore(expectedScore, expectedName);
		Assert.assertEquals(expectedName, sut.getHighScores().get(0).getName());
		Assert.assertEquals(expectedScore, sut.getHighScores().get(0)
				.getScore());
	}

	@Test
	public void When_gethighscorebyname_on_empty_list_expect_return_null() {
		HighScoreDAO sut = new HighScoreDAOInMemoryBuilder().Build();
		Assert.assertNull(sut.getHighScoreByName("default"));
	}
}
