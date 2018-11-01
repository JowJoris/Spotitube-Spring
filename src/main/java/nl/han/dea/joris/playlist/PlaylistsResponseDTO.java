package nl.han.dea.joris.playlist;

import nl.han.dea.joris.database.objects.Playlist;

import java.util.ArrayList;
import java.util.List;

public class PlaylistsResponseDTO {

    private List<Playlist> playlists = new ArrayList<>();
    private int length;

    public void setPlaylists(List<Playlist> playlists) {this.playlists = playlists;}
    public List<Playlist> getPlaylists() {return playlists;}
    public int getLength() {return length;}
    public void setLength(int length) {this.length = length;}

}
