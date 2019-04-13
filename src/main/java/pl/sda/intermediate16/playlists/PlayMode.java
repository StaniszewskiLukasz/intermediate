package pl.sda.intermediate16.playlists;

public enum PlayMode {
    //czym to się różni od zwykłej klasy?, gdyby to była klasa to miałaby jedno pole,,
    // a tak to są gotowe instancje tej klasy
    SEQUENTIAL("normalnie"), LOOP("pętla"), RANDOM("losowo");

    private String plName;
    // to jest tak naprawdę Singleton

    PlayMode(String plName) {
        this.plName = plName;
    }
}
