package fr.arthurbarbera.lib.dbsimplifier.connection;

public record DBCredentials(String host, String databaseName, String user, String pass, int port) {

    public String toURI() {
        return "jdbc:mysql://" + (this.host.isBlank() ? "localhost" : host) + ":" + this.port + "/" + this.databaseName;
    }

}
