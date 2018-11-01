package nl.han.dea.joris.track;

import nl.han.dea.joris.database.objects.Track;

import java.util.List;

public class TracksResponseDTO {

    private List<Track> tracks;

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
