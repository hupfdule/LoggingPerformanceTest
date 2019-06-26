package com.github.hupfdule.lpt;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;


/**
 * A simple formatter for java.util.logging to log to a single line.
 */
public class OneLineFormatter extends Formatter {

  private static final String LINE_SEPARATOR = System.getProperty("line.separator");
  private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSS");


  @Override
  public String format(final LogRecord record) {
    final StringBuilder sb = new StringBuilder();

    final String dateString = DATE_FORMAT.format(new Date(record.getMillis()));

    sb.append(dateString)
      .append(" ")
      .append(getLogLevelString(record.getLevel()))
      .append(" [")
      .append(record.getSourceClassName())
      .append("#")
      .append(record.getSourceMethodName()).append("()")
      .append("]")
      .append(" - ")
      .append(formatMessage(record))
      .append(LINE_SEPARATOR);

    if (record.getThrown() != null) {
      try {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        record.getThrown().printStackTrace(pw);
        pw.close();
        sb.append(sw.toString());
      } catch (Exception ex) {
        // ignore
      }
    }

    return sb.toString();
  }


  private String getLogLevelString(final Level level) {
    return String.format("%1$-7s", level.getName());
  }
}
