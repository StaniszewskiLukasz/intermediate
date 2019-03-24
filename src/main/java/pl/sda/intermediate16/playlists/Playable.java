package pl.sda.intermediate16.playlists;

public abstract class Playable {//jeśli to nie będize abstract to mozemy zapomnieć o implementacj metody w klasach podrzęnych, musimy wpisac ciało metody a możemy na razie nie wiedzieć co ona zrobi,
    protected abstract String play();
}
