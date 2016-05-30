package net.aimeizi.product;

import org.apache.log4j.Logger;

public class Send {

    private static final Logger logger = Logger.getLogger(Send.class);

    public static void main(String[] args) throws Exception {
        logger.info("Info Log.");
        logger.warn("Warn Log");
        logger.error("Error Log.");
        System.exit(0);
    }
}
