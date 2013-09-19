package de.woock.games.breakout.highscore.impl;

import java.util.List;

import javax.persistence.Persistence;

import de.woock.games.breakout.highscore.HighScore;
import de.woock.games.breakout.highscore.HighScoreDAO;

public class HighScoreDAOHibernateTransactionDecorator implements HighScoreDAO {

	HighScoreDAO decoratedObject;

	public HighScoreDAOHibernateTransactionDecorator(HighScoreDAO inHighScoreDAO) {
		decoratedObject = inHighScoreDAO;
	}

	@Override
	public List<HighScore> getHighScores() {
		finalizeTransaction();
		List<HighScore> result = this.decoratedObject.getHighScores();
		prepareTransaction();
		return result;
	}

	@Override
	public void saveHighScore(int score, String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public HighScore getHighScoreByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	protected void finalizeTransaction() {
		em.flush();
		tx.commit();
		em.close();
		emf.close();
	}

	protected void prepareTransaction() {
		emf = Persistence.createEntityManagerFactory("breakout-jpa");
		em = emf.createEntityManager();
		tx = em.getTransaction();
		tx.begin();
	}
}
