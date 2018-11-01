package nl.han.dea.joris.database.dao;

import nl.han.dea.joris.database.objects.Track;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class TrackDAO extends DefaultDAO {

    private static final String GET_PLAYTLIST_TRACKS = "SELECT `trackdata`.*, DATE_FORMAT(`trackdata`.`publicationDate`, '%d-%m-%Y') AS publicationDateRefactored, `trackinplaylist`.`offlineAvailable`, `trackinplaylist`.`playcount` FROM `trackdata` LEFT JOIN `trackinplaylist` ON `trackdata`.`trackID` = `trackinplaylist`.`trackID` WHERE playlistID = ?";
    private static final String GET_TRACKS_NOT_IN_PLAYLIST = "SELECT `trackdata`.* FROM `trackdata` WHERE trackID NOT IN (SELECT `trackID` FROM `trackinplaylist` WHERE `playlistID` = ?)";
    private static final String ADD_TRACK = "INSERT INTO `trackinplaylist` (`playlistID`, `trackID`, `offlineAvailable`) VALUES (?, ?, ?)";
    private static final String DELETE_TRACK = "DELETE FROM `trackinplaylist` WHERE `trackinplaylist`.`playlistID` = ? AND `trackinplaylist`.`trackID` = ?";

    private List<Track> tracks = new ArrayList<>();

    public List<Track> getTracks(int id) {
        try {
            connection = connector.getConnection();
            pstmt = connection.prepareStatement(GET_PLAYTLIST_TRACKS);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Track track = new Track();
                track.setId(rs.getInt("trackID"));
                track.setTitle(rs.getString("title"));
                track.setPerformer(rs.getString("performer"));
                track.setDuration(rs.getInt("duration"));
                track.setAlbum(rs.getString("album"));
                track.setPublicationDate(rs.getString("publicationDateRefactored"));
                track.setDescription(rs.getString("description"));
                track.setOfflineAvailable(rs.getBoolean("offlineAvailable"));
                track.setPlaycount(rs.getInt("playcount"));
                tracks.add(track);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        } finally {
            closeConnections();
        }
        return tracks;
    }

    public List<Track> getTracksNotInPlaylist(int id) {
        try {
            connection = connector.getConnection();
            pstmt = connection.prepareStatement(GET_TRACKS_NOT_IN_PLAYLIST);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Track track = new Track();
                track.setId(rs.getInt("trackID"));
                track.setTitle(rs.getString("title"));
                track.setPerformer(rs.getString("performer"));
                track.setDuration(rs.getInt("duration"));
                track.setAlbum(rs.getString("album"));
                track.setPublicationDate(rs.getString("publicationDate"));
                track.setDescription(rs.getString("description"));
                tracks.add(track);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        } finally {
            closeConnections();
        }
        return tracks;
    }

    public void addTrack(int playlistID, int trackID, boolean offlineAvailable) {
        try {
            connection = connector.getConnection();
            pstmt = connection.prepareStatement(ADD_TRACK);
            pstmt.setInt(1, playlistID);
            pstmt.setInt(2, trackID);
            pstmt.setBoolean(3, offlineAvailable);

            pstmt.executeUpdate();


        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        } finally {
            closeConnections();
        }
    }

    public void deleteTrack(int playlistID, int trackID) {
        try {
            connection = connector.getConnection();
            pstmt = connection.prepareStatement(DELETE_TRACK);
            pstmt.setInt(1, playlistID);
            pstmt.setInt(2, trackID);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        } finally {
            closeConnections();
        }
    }
}
