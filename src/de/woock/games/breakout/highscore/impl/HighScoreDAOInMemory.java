package de.woock.games.breakout.highscore.impl;

import java.util.ArrayList;
import java.util.List;

import de.woock.games.breakout.highscore.HighScore;
import de.woock.games.breakout.highscore.HighScoreDAO;

public class HighScoreDAOInMemory implements HighScoreDAO {

	private List<HighScore> list = new ArrayList<HighScore>();

	@Override
	public List<HighScore> getHighScores() {
		return this.list;
	}

	@Override
	public void saveHighScore(int score, String name) {
		this.list.add(new HighScore(score, name));
	}

	@Override
	public HighScore getHighScoreByName(String name) {
		HighScore foundObject = null;
		for (HighScore iterable_element : this.list) {
			if (iterable_element.getName().equalsIgnoreCase(name)) {
				foundObject = iterable_element;
			}
		}

		return foundObject;
	}

}
