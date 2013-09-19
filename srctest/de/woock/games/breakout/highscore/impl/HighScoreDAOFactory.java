package de.woock.games.breakout.highscore.impl;

import de.woock.games.breakout.highscore.HighScoreDAO;

public class HighScoreDAOFactory {

	private String name;

	public HighScoreDAOFactory(String name) {
		this.name = name;
	}

	public HighScoreDAO create() throws Exception {
		if (IsHibernate()) {
			return new HighScoreDAOHibernate();
		}

		if (IsInMemory()) {
			return new HighScoreDAOInMemory();
		}

		throw new Exception(String.format("{0} not supported", this.name));
	}

	private boolean IsInMemory() {
		return "de.woock.games.breakout.highscore.impl.HighScoreDAOInMemory"
				.equals(this.name);
	}

	private boolean IsHibernate() {
		return "de.woock.games.breakout.highscore.impl.HighScoreDAOHibernate"
				.equals(this.name);
	}
}
