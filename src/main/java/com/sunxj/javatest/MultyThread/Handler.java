package com.sunxj.javatest.MultyThread;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.List;

public interface Handler {
    public List<Thread> createThread(int num);
    public void Handler();

}
