package com.sunxj.javatest.MultyThread.Chapter15ObserverModel;

public interface TaskLifecycle<T> {
    /**
     * 这里实际上相当于观察者一旦观察到变化后进行的相应
     * @param thread
     */
    void onStart(Thread thread);

    void onRunning(Thread thread);

    void onFinish(Thread thread);

    void onError(Thread thread);

    class EmptyTaskLisfcycle<T> implements TaskLifecycle {

        @Override
        public void onStart(Thread thread) {

        }

        @Override
        public void onRunning(Thread thread) {

        }

        @Override
        public void onFinish(Thread thread) {

        }

        @Override
        public void onError(Thread thread) {

        }
    }
}
