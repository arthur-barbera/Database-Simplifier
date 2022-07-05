package fr.arthurbarbera.lib.dbsimplifier;

public final class Logger {

    public static void log(String message) {
        System.out.println("[DATABASE SIMPLIFIER] " + message);
    }
    
    public static void error(String message) {
        System.err.println("[DATABASE SIMPLIFIER] " + message);
    }

}
