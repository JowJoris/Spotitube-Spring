package nl.han.dea.joris.services;

import nl.han.dea.joris.database.dao.PlaylistDAO;
import nl.han.dea.joris.database.dao.UserPlaylistType;
import nl.han.dea.joris.database.objects.Playlist;
import nl.han.dea.joris.playlist.PlaylistsResponseDTO;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class PlaylistService {
    private PlaylistDAO playlistDAO = new PlaylistDAO();

    public PlaylistsResponseDTO getPlaylists(int userID) {
        PlaylistsResponseDTO playlistsResponseDTO = new PlaylistsResponseDTO();
        List<Playlist> playlists = playlistDAO.getPlaylists(userID);
        List<Playlist> checkedPlaylists = checkOwner(userID, playlists);
        playlistsResponseDTO.setLength(getLength());
        playlistsResponseDTO.setPlaylists(checkedPlaylists);
        return playlistsResponseDTO;

    }

    public PlaylistsResponseDTO addPlaylist(int userID, String name) {
        playlistDAO.addPlaylist(userID, name);
        playlistDAO.editUserPlaylist(UserPlaylistType.ADD, playlistDAO.getPlaylistID(name), userID);
        return getPlaylists(userID);
    }

    private List<Playlist> checkOwner(int userID, List<Playlist> playlists) {
        for (Playlist p : playlists) {
            if (p.getOwnerId() == userID) {
                p.setOwner(true);
            }
        }
        return playlists;
    }

    private int getLength() {
        return playlistDAO.getLength();
    }

    public PlaylistsResponseDTO editPlaylist(String name, int playlistID, int userID) {
        playlistDAO.editPlaylist(name, playlistID);
        return getPlaylists(userID);
    }

    public PlaylistsResponseDTO deletePlaylist(int playlistID, int userID){
        playlistDAO.deletePlaylist(playlistID);
        playlistDAO.editUserPlaylist(UserPlaylistType.DELETE, playlistID, userID);
        return getPlaylists(userID);
    }

    @Bean
    public void setPlaylistDAO(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }
}
