package de.woock.games.breakout.highscore.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.List;

import junit.framework.Assert;
import static org.junit.Assert.*;

import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.assertion.DiffCollectingFailureHandler;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;

import de.woock.games.breakout.highscore.HighScore;

public class DBUnitTest {

	@Before
	public void setUp() throws SQLException, DatabaseUnitException, FileNotFoundException {
		try {
			IDataSet dataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("srctest/initial.xml"));
			DatabaseOperation.CLEAN_INSERT.execute(getConnection(), dataSet);
		} catch (DatabaseUnitException ex) {
			throw ex;
		}
	}

	@Test
	public void highscoreIsSavedCorrectly() throws Exception {
		HighScoreDAOHibernate daoHibernate = new HighScoreDAOHibernate();
		daoHibernate.saveHighScore(3000, "Hermann");
		List<HighScore> highScores = daoHibernate.getHighScores();

		// actual table
		IDataSet databaseDataSet = getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("HIGHSCORE");

		// expected table
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("srctest/result.xml"));
		ITable expectedTable = expectedDataSet.getTable("HIGHSCORE");

		ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns());

		// make tests
		Assert.assertEquals(highScores.size(), actualTable.getRowCount());

		DiffCollectingFailureHandler handler = new DiffCollectingFailureHandler();
		Assertion.assertEquals(expectedTable, filteredTable, handler);
		List diffList = handler.getDiffList();
		System.out.println(diffList);

		// write database
		FlatXmlDataSet.write(databaseDataSet, new FileOutputStream("result.xml"));
	}

	@Test
	public void correctNumberOfhighscoresRead() {
		HighScoreDAOHibernate daoHibernate = new HighScoreDAOHibernate();
		List<HighScore> highScores = daoHibernate.getHighScores();
		assertEquals(3, highScores.size());
	}

	@Test
	public void correctValuesRead() {
		HighScoreDAOHibernate daoHibernate = new HighScoreDAOHibernate();
		HighScore highScore = daoHibernate.getHighScoreByName("Heinz");
		assertEquals("Heinz", highScore.getName());

	}

	public IDatabaseConnection getConnection() {
		return DBConnection.getConnection();
	}
}
