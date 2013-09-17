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
}
