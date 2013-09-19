package de.woock.games.breakout.highscore.impl;

import junit.framework.Assert;

import org.junit.Test;

public class HighScoreDAOFactoryTest {
	@Test
	public void When_create_with_full_qualified_class_name_for_hibernate_expect_type_of_highscoredaohibernate_returned()
			throws Exception {
		HighScoreDAOFactory sut = new HighScoreDAOFactoryBuilder()
				.WithTypeName(
						"de.woock.games.breakout.highscore.impl.HighScoreDAOHibernate")
				.Build();

		Assert.assertTrue(sut.create() instanceof HighScoreDAOHibernate);
	}

	@Test
	public void When_create_with_full_qualified_class_name_for_in_memory_expect_type_of_highscoredaohibernate_returned()
			throws Exception {
		HighScoreDAOFactory sut = new HighScoreDAOFactoryBuilder()
				.WithTypeName(
						"de.woock.games.breakout.highscore.impl.HighScoreDAOInMemory")
				.Build();

		Assert.assertTrue(sut.create() instanceof HighScoreDAOInMemory);
	}

	@Test
	public void When_class_name_not_a_impl_expect_exception() {
		HighScoreDAOFactory sut = new HighScoreDAOFactoryBuilder().Build();

		try {
			sut.create();
			Assert.fail("no exception thrown");
		} catch (Exception e) {
		}
	}
}
