package com.github.hupfdule.lpt;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simple test class for comparing the performance of logging with JUL and Log4j.
 *
 * @author mherrn
 */
public class LPT {

  /** Logger to write simple log statements to */
  private static final Logger LOGGER_SIMPLE= Logger.getLogger("LPT_SIMPLE");

  /** Logger to write a bit more complex and inefficient log statements to */
  private static final Logger LOGGER_INEFFICIENT= Logger.getLogger("LPT_INEFFICENT");


  /////////////////////////////////////////////////////////////////////////////
  //
  // Attributes

  /** The number of log message to generate for each logger */
  private final int noOfLogStatements;

  /** Whether to generate the simple log statements */
  private final boolean logSimple;

  /** Whether to generate the complex and inefficient log statements */
  private final boolean logInefficient;


  /////////////////////////////////////////////////////////////////////////////
  //
  // Constructors

  public LPT(final int noOfLogStatements, final boolean logSimple, final boolean logInefficient) {
    this.noOfLogStatements = noOfLogStatements;
    this.logSimple = logSimple;
    this.logInefficient = logInefficient;
  }


  /////////////////////////////////////////////////////////////////////////////
  //
  // Methods

  public void run() {
    System.out.println("no of log statements:       "+noOfLogStatements);
    System.out.println("log simple statements:      "+logSimple);
    System.out.println("log inefficient statements: "+logInefficient);

    if (logSimple) {
      for (int i=0; i < noOfLogStatements; i++) {
        LOGGER_SIMPLE.log(Level.INFO, "A simple log statement with no dynamic content");
      }
    }

    if (logInefficient) {
      for (int i=0; i < noOfLogStatements; i++) {
        LOGGER_INEFFICIENT.log(Level.INFO, "Complex "+i+" and inefficient "+new Date()+": "+getComplexString());
      }
    }
  }


  private String getComplexString() {
    String s= "My silly complex string ";
    s += this.getClass().getName();
    s += " ";
    s += System.identityHashCode(this);
    s += " ";
    s += new Date();
    s += " ";
    s += this.toString();
    s += " ";

    for (int i= 0; i < 10; i++) {
      s += i+", ";
    }

    return s;
  }


  public static void main(String[] args) {
    if (args.length != 3) {
      System.out.println("\n"
        + "3 arguments required:\n"
        + "  1: no of log statements to issue for each logger\n"
        + "  2: whether to generate simple log statements\n"
        + "  3: whether to generate complex and inefficient log statements\n"
        + "\n"
        + "Example: \n"
        + "  java -cp jars/* com.github.hupfdule.lpt.LPT 1500000 true false\n");
      System.exit(1);
    }

    final int noOfLogStatements= Integer.parseInt(args[0]);
    final boolean logSimple= Boolean.valueOf(args[1]);
    final boolean logInefficient= Boolean.valueOf(args[2]);

    final LPT lpt= new LPT(noOfLogStatements, logSimple, logInefficient);
    lpt.run();
  }
}
