package com.septacore.ripple.node;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;


/**
 * The manager for the Ripple boxes. Each box is a Runnable instance
 * which get submitted as a job to the thread pool.
 */
@Component(value = "RippleManager")
public class RippleManager {

    @Autowired
    private ThreadPoolTaskExecutor executor;

    /**
     * Add a new Box to the thread pool
     * @param node
     * @return a Future object
     */
    public Future addNode(Box node){
        return executor.submit(node);
    }

    /**
     * Terminate the execution of the Ripple set-up
     */
    public void terminateNetwork(){
        executor.shutdown();
    }


}
