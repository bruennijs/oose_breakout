package de.woock.games.breakout.util;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import de.woock.games.breakout.statistics.StatisticTool;

public class TestStringUtils {

	@Test
	public void extractedRightString() {
		String logLine = "2013-03-05 10:07:19,113 [thread applet-de.woock.games.breakout.Breakout.class] INFO  (    Tile.java)     - create token: Token [character=N, points=40]";
		String extracted = StringUtils.extractTimeStringFromLogline(logLine);
		assertEquals("2013-03-05 10:07:19,113", extracted);
	}

	@Test(expected = IllegalArgumentException.class)
	public void stringWithNoDateThrowsException() {
		String logLine = "[thread applet-de.woock.games.breakout.Breakout.class] INFO  (    Tile.java)     - create token: Token [character=N, points=40]";
		StringUtils.extractTimeStringFromLogline(logLine);
	}

}
