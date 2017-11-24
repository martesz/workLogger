package entities;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DebugLogger {
	private Logger logger;

	public DebugLogger(final String name) {
		logger = Logger.getLogger(name);
	}
	
	public void log(final String message) {
		logger.log(Level.INFO, message);
	}
	
}
