package pl.sda.intermediate16.playlists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static pl.sda.intermediate16.playlists.PlayMode.*;

public class Playlist extends Playable {

    //   private List<Movie> movieList = new ArrayList<>();
    // takie rozwiązanie jest bez polimorfizmu i dziedziczenia

    //   private List<Music> musicList = new ArrayList<>();
    // poza tym będziemy to rozrzucać wszystko po trzech listach

    //   private List<Playlist> playlists = new ArrayList<>();
    // dlatego robimy naklasę dla tych trzech

    private List<Playable> playables = new ArrayList<>();
    private PlayMode playMode;

    public Playlist(PlayMode playMode) {
        this.playMode = playMode;
    }

    protected void addToPlaylist(Playable playable){
        playables.add(playable);
    }

    @Override
    protected String play() {
        if (playMode == SEQUENTIAL) {
            // możemy tu użyć == bo enum przechowuje obiekty ale jest wyjątkiem i przechowuje miejsce w pamięci,
            // można robić tak i tak ale tak jest czytelniej i widać że to jest enum
            return collectTitles(playables);
            //leci tu do nas pięć napisów,
            // mamy zwrócić jeden napis ale w pięciu liniach dzięki metodzie poniżej
        } else if (playMode == LOOP) {
            // tu nie ma klasy. enum bo to jest import statyczny
            String result = "";
            for (int i = 0; i < 10; i++) {
                result = result + collectTitles(playables);
            }
            return result;
        } else if (playMode == RANDOM){
            List<Playable> tempList = new ArrayList<>(playables);
            Collections.shuffle(tempList);
            // jeśli przeszuflujemy listę główną to się na niej zmieni kolejność za każdym razem
            return collectTitles(tempList);
        }
        return "ERROR!!";
    }

    private String collectTitles(List<Playable> playables) {
        return playables.stream()
                .map(Playable::play)
                .collect(Collectors.joining("\n"));
    }
}
