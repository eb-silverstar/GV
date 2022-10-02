package com.kt.corp.comm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseComm {
    protected final Log log = LogFactory.getLog(getClass());
    protected final Logger logger = LogManager.getLogger(getClass());
}
