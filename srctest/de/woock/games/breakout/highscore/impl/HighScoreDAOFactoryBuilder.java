package de.woock.games.breakout.highscore.impl;

public class HighScoreDAOFactoryBuilder {

	private String name;

	public HighScoreDAOFactoryBuilder WithTypeName(String string) {
		this.name = string;
		return this;
	}

	public HighScoreDAOFactory Build() {
		return new HighScoreDAOFactory(this.name);
	}
}
