package nl.han.dea.joris.database.objects;

import java.util.List;

public class Playlist {

    private int id;
    private String name;
    private int ownerId;
    private boolean owner = false;
    private List<Track> tracks;

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public List<Track> getTracks() {return tracks;}

    public void setTracks(List<Track> tracks) {this.tracks = tracks;}
}
