import java.util.List;

import de.woock.games.breakout.statistics.StatisticTool;

public class StatisticToolBuilder {
	private List<String> lines;

	public StatisticToolBuilder WithLines(List<String> value) {
		this.lines = value;
		return this;
	}

	public StatisticTool Build() {
		return new StatisticTool(this.lines) {
			@Override
			public List<String> fileOperation() {
				return this.logLines;
			}
		};
	}
}