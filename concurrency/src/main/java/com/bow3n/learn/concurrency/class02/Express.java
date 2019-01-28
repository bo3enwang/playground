package com.bow3n.learn.concurrency.class02;

/**
 * @author bowen
 */
public class Express {
    public final static String CITY = "shanghai";

    private int km;
    private String site;

    public Express(int km, String site) {
        this.km = km;
        this.site = site;
    }

    public synchronized void changeKm() {
        this.km = 101;
        notifyAll();
    }

    public synchronized void changeSite() {
        this.site = "Beijing";
        notifyAll();
    }

    public synchronized void waitKm() {
        while (this.km < 100) {
            try {
                wait();
                System.out.println("wait km change-------------");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("business operator km");
    }

    public synchronized void waitSite() {
        while (CITY.equals(this.site)) {
            try {
                wait();
                System.out.println("wait site change-------------");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("business operator site");
    }
}
