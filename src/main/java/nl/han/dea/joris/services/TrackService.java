package nl.han.dea.joris.services;

import nl.han.dea.joris.database.dao.TrackDAO;
import nl.han.dea.joris.database.objects.Track;
import nl.han.dea.joris.track.TracksResponseDTO;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class TrackService {

    private TrackDAO trackDAO = new TrackDAO();

    public TracksResponseDTO getTracks(int playlistID){
        TracksResponseDTO tracksResponseDTO = new TracksResponseDTO();

        List<Track> tracks = trackDAO.getTracks(playlistID);
        tracksResponseDTO.setTracks(tracks);
        return tracksResponseDTO;

    }

    public TracksResponseDTO getListOfTracks(int playlistID){
        TracksResponseDTO tracksResponseDTO = new TracksResponseDTO();

        List<Track> tracks = trackDAO.getTracksNotInPlaylist(playlistID);
        tracksResponseDTO.setTracks(tracks);
        return tracksResponseDTO;

    }

    public TracksResponseDTO addTrack(int playlistID, int trackID, boolean offlineAvailable){
        trackDAO.addTrack(playlistID, trackID, offlineAvailable);
        return getTracks(playlistID);
    }

    public TracksResponseDTO deleteTrack(int playlistID, int trackID){
        trackDAO.deleteTrack(playlistID, trackID);
        return getTracks(playlistID);
    }

    @Bean
    public void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }
}
