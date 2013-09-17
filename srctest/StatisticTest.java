import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

import de.woock.games.breakout.statistics.StatisticTool;

public class StatisticTest {
	@Test
	public void TestGetNumberOfHitTiles() {
		ArrayList<String> expectedLines = new ArrayList<String>();
		expectedLines.add("hit Tile");
		StatisticTool sut = new StatisticToolBuilder().WithLines(expectedLines)
				.Build();
		Assert.assertEquals(1, sut.getNumberOfHitTiles());
	}

	@Test
	public void TestGetNumberOfUsedBalls() {
		ArrayList<String> expectedLines = new ArrayList<String>();
		expectedLines.add("next Ball");
		StatisticTool sut = new StatisticToolBuilder().WithLines(expectedLines)
				.Build();
		Assert.assertEquals(2, sut.getNumberOfUsedBalls());
	}

	@Test
	public void TestGetTimeWithTwoLinesExpectGetTimeReturnsDifference() {
		ArrayList<String> expectedLines = new ArrayList<String>();
		expectedLines
				.add("2013-09-17 01:22:22,272 DEBUG [de.woock.games.breakout.Breakout] - <init>");
		expectedLines
				.add("2013-09-17 02:22:22,272 DEBUG [de.woock.games.breakout.Breakout] - <init>");
		StatisticTool sut = new StatisticToolBuilder().WithLines(expectedLines)
				.Build();
		Assert.assertEquals(60, sut.getTime());
	}

}
