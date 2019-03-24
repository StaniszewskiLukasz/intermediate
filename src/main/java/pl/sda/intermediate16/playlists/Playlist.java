package pl.sda.intermediate16.playlists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static pl.sda.intermediate16.playlists.PlayMode.*;


public class Playlist extends Playable {

    private List<Playable> playablesList = new ArrayList<>(); // teraz mamy jedną listę dla wszystkich bytów i jest to lista playablesów
    private PlayMode playMode;

    public Playlist(PlayMode playMode) {
        this.playMode = playMode;
    }

    public void addToPlaylist(Playable playable){
        playablesList.add(playable);
    }

    public void addToPlayList(Playable playable){
        playablesList.add(playable);
    }

    @Override
    public String play() {
        if (playMode == SEQUENTIAL) {
            return collectTitles(playablesList);
        } else if (playMode == LOOP) {
            String result = "";
            for (int i = 0; i < 10; i++) {
                result = result + collectTitles(playablesList);
            }
            return result;
        } else if (playMode == RANDOM){
            List<Playable> tempList = new ArrayList<>(playablesList);
            Collections.shuffle(tempList);
            return collectTitles(tempList);
        }
        return "ERROR!!";
    }
    /*protected String play() {
        if (playMode == SEQUENTIAL) { // możemy tu użyć == bo enum przechowuje obiekty ale jest wyjątkiem i przechowuje miejsce w pamięci, można robić tak i tak ale tak jest czytelniej i widać że to jest enum
            return collectTitles(playablesList);//leci tu do nas pięć napisów, mamy zwrócić jeden napis ale w pięciu liniach
        } else if (playMode == LOOP) { // tu nie ma klasy. enum bo to jest import statyczny
            String loopMode = "";
//            IntStream.range(1,11).forEach(e->e.loopMode);// coś takiego
            for (int i = 0; i < 10; i++) {
                loopMode = loopMode + collectTitles(playablesList);
            }
            return loopMode;
        } else {
                List<Playable> tempList = new ArrayList<>(playablesList);
//            Collections.shuffle(playablesList);// jeśli przeszuflujemy listę główną to się na niej zmieni kolejność za każdym razem
                Collections.shuffle(tempList);
                return collectTitles(tempList);
            }
//        return "ERROR";
        }*/

    private String collectTitles(List<Playable> playablesList) {
        return playablesList.stream()
                .map(Playable::play)
                .collect(Collectors.joining("\\n"));
    }
//   private List<Movie> movieList = new ArrayList<>(); // takie rozwiązanie jest bez polimorfizmu i dziedziczenia
//   private List<Music> musicList = new ArrayList<>(); // poza tym będziemy to rozrzucać wszystko po trzech listach
//   private List<Playlist> playlists = new ArrayList<>(); // dlatego robimy naklasę dla tych trzech


}
