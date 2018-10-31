package com;

public class Main {
    public static void main(String[] args)throws Exception {
        Resourse resourse = new Resourse();
        resourse.setI(5);
        MyThread myThread = new MyThread();
        myThread.setName("one");
        MyThread myThread2 = new MyThread();
        myThread.setResource(resourse);
        myThread2.setResource(resourse);
        myThread.start();
        myThread2.start();
        myThread.join();
        myThread2.join();
        System.out.println(resourse.getI());
    }
}

class MyThread extends Thread {
    Resourse resourse;
    public void setResource(Resourse resourse) {
        this.resourse = resourse;
    }
    @Override
    public void run() {
        resourse.changeI();
    }
}

class Resourse {
   private int i;

    public int getI() {
        return i;
    }

    public synchronized void setI(int i) {
        this.i = i;
    }

    public synchronized void changeI() {
        int i = this.i;
        if(Thread.currentThread().getName().equals("one")){
            Thread.yield();
        }
        i++;
        this.i = i;
    }
}