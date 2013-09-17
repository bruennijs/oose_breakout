package de.woock.games.breakout.highscore.impl;

import de.woock.games.breakout.highscore.HighScoreDAO;

public class HighScoreDAOInMemoryBuilder {
	public HighScoreDAO Build() {
		return new HighScoreDAOInMemory();
	}
}