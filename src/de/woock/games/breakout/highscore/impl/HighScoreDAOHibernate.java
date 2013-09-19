package de.woock.games.breakout.highscore.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import de.woock.games.breakout.highscore.HighScore;
import de.woock.games.breakout.highscore.HighScoreDAO;

public class HighScoreDAOHibernate implements HighScoreDAO {

	protected EntityManagerFactory emf = null;
	protected EntityManager em = null;
	protected EntityTransaction tx = null;

	public void fillDummyData() {

		prepareTransaction();

		em.persist(new HighScore(3000, "Hibernate"));
		em.persist(new HighScore(2000, "Hibernate"));
		em.persist(new HighScore(1000, "Hibernate"));

		finalizeTransaction();
	}

	@Override
	public List<HighScore> getHighScores() {
		TypedQuery<HighScore> query = em.createQuery("From HighScore",
				HighScore.class);
		List<HighScore> highScores = query.getResultList();
		return highScores;
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

	@Override
	public void saveHighScore(final int score, final String name) {
		prepareTransaction();

		em.persist(new HighScore(score, name));

		finalizeTransaction();
	}

	@Override
	public HighScore getHighScoreByName(String name) {
		HighScore highscore = new HighScore();

		prepareTransaction();

		TypedQuery<HighScore> query = em.createNamedQuery(
				"HighScore.getByName", HighScore.class);
		query.setParameter("name", name);
		highscore = query.getSingleResult();

		finalizeTransaction();

		return highscore;
	}
}
