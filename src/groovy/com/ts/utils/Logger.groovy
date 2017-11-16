package com.ts.utils

/**
 * Created by gmazzaglia on 22/8/16.
 */
class Logger {
    static ERROR = "ERROR"
    static INFO = "INFO"
    static TRACE = "TRACE"

    static def kibana = { String level, Map args, String msg ->
        if(args){
            args += [LEVEL: level];
        } else {
            args = [LEVEL: level];
        }
        println("${args} ${msg}");
    }
}
