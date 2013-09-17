package de.woock.games.breakout.statistics;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import de.woock.games.breakout.util.StringUtils;

public class StatisticTool {

	Logger log = Logger.getLogger(StatisticTool.class);

	final static Charset ENCODING = StandardCharsets.UTF_8;

	final String logFile = "breakoutLog.log";

	protected List<String> logLines;

	public StatisticTool() {
	}

	public StatisticTool(List<String> logLines) {
		this.logLines = logLines;
	}

	public List<String> fileOperation() {
		Path path = Paths.get(this.logFile);
		this.logLines = new ArrayList<String>();
		try {
			logLines = Files.readAllLines(path, ENCODING);
		} catch (IOException e) {
			throw new RuntimeException("Could not read Logfile from: "
					+ logFile);
		}

		return logLines;
	}

	private List<String> readLogFileForNumberMethods() {
		List<String> logLines = fileOperation();
		return logLines;
	}

	private int countLinesByString(List<String> logLines, String searchString) {
		int count = 0;
		for (String logEntry : logLines) {
			if (logEntry.contains(searchString)) {
				count++;
			}
		}
		return count;
	}

	public int getNumberOfHitTiles() {
		List<String> logLines = readLogFileForNumberMethods();
		int count = countLinesByString(logLines, "hit Tile");
		return count;
	}

	public int getNumberOfUsedBalls() {
		List<String> logLines = readLogFileForNumberMethods();
		int count = 1 + countLinesByString(logLines, "next Ball");
		return count;
	}

	public long getTime() {
		List<String> strings = fileOperation();
		String string = strings.get(0);
		String date = StringUtils.extractTimeStringFromLogline(string);
		Date start = convertDateStringToDate(date);
		long start2 = start.getTime();

		String lastString = strings.get(strings.size() - 1);
		date = StringUtils.extractTimeStringFromLogline(lastString);
		Date end = convertDateStringToDate(date);
		long end2 = end.getTime();

		return (end2 - start2) / 1000 / 60;
	}

	public String getPrettyStatisticsString() {
		List<String> logLines = fileOperation();

		// Number of Balls
		// Calculates Number of Balls from LogFiles
		// Every Line is checked, if it contains the Ballstring
		// if it does, c is increased
		int c = 1;

		for (int i = 0; i < logLines.size(); i++) {
			if (logLines.get(i).contains("next Ball"))
				c++; // no pun intended
		}

		return "Time played: " + getTime() + " Minutes\nTiles hit: "
				+ getNumberOfHitTiles() + "\nBalls used: " + c;
	}

	public Date convertDateStringToDate(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss,SSS");
		Date date = new Date();
		try {
			date = sdf.parse(dateString);
		} catch (Exception e) {
			// TODO: Exceptionhandling
		}
		return date;
	}

}
