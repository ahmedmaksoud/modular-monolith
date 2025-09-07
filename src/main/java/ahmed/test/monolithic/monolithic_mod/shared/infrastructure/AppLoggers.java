package ahmed.test.monolithic.monolithic_mod.shared.infrastructure;
// src/main/java/com/example/logging/AppLoggers.java


import ahmed.test.monolithic.monolithic_mod.shared.IApplogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.StringJoiner;

public final class AppLoggers {
    private AppLoggers() {}

    public static IApplogger get(Class<?> type) {
        Logger slf4j = LoggerFactory.getLogger(type);
        return new DefaultAppLogger(slf4j);
    }

    static final class DefaultAppLogger implements IApplogger {
        private final Logger log;
        DefaultAppLogger(Logger log) { this.log = log; }

        @Override public void debug(String m, Object... a){ if(log.isDebugEnabled()) log.debug(m, a);}
        @Override public void info (String m, Object... a){ if(log.isInfoEnabled())  log.info (m, a);}
        @Override public void warn (String m, Object... a){ if(log.isWarnEnabled())  log.warn (m, a);}
        @Override public void error(String m, Object... a){ if(log.isErrorEnabled()) log.error(m, a);}
        @Override public void error(String m, Throwable t, Object... a){ if(log.isErrorEnabled()) log.error(m, t); }
        @Override public void trace(String m,  Object... a){ if(log.isTraceEnabled()) log.trace(m, a); }


        @Override public void debug(String m, Map<String,?> kv){ if(log.isDebugEnabled()) log.debug(m + ctx(kv)); }
        @Override public void info (String m, Map<String,?> kv){ if(log.isInfoEnabled())  log.info (m + ctx(kv)); }
        @Override public void warn (String m, Map<String,?> kv){ if(log.isWarnEnabled())  log.warn (m + ctx(kv)); }
        @Override public void error(String m, Map<String,?> kv){ if(log.isErrorEnabled()) log.error(m + ctx(kv)); }
        @Override public void trace(String m, Map<String,?> kv){ if(log.isTraceEnabled()) log.trace(m + ctx(kv)); }



        private static String ctx(Map<String,?> kv) {
            if (kv == null || kv.isEmpty()) return "";
            StringJoiner j = new StringJoiner(" ", " ", "");
            kv.forEach((k,v) -> j.add(k + "=" + String.valueOf(v)));
            return j.toString();
        }
    }
}
