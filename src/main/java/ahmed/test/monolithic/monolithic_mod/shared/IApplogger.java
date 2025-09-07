package ahmed.test.monolithic.monolithic_mod.shared;

import java.util.Map;

public interface IApplogger {
    void debug(String msg, Object... args);
    void info(String msg, Object... args);
    void warn(String msg, Object... args);
    void error(String msg, Object... args);
    void error(String msg, Throwable t, Object... args);
    void trace(String msg, Object... args);

    // Structured key-values, e.g. logger.info("User created", Map.of("userId", 123));
    void debug(String msg, Map<String, ?> kv);
    void info(String msg, Map<String, ?> kv);
    void warn(String msg, Map<String, ?> kv);
    void error(String msg, Map<String, ?> kv);
    void trace(String msg, Map<String, ?> kv);
}