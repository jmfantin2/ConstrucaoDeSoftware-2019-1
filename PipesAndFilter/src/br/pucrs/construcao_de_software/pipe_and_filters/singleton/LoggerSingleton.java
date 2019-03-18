package br.pucrs.construcao_de_software.pipe_and_filters.singleton;

import java.util.logging.Logger;

public class LoggerSingleton {

    private static Logger logger;

    private LoggerSingleton() {
    }

    public static synchronized Logger getInstance() {
        if (logger == null)
            logger = Logger.getLogger("LogPipesAndFilter");
        return logger;
    }
}
