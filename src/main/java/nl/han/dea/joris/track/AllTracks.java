package nl.han.dea.joris.track;

import nl.han.dea.joris.exceptions.TokenException;
import nl.han.dea.joris.services.TrackService;
import nl.han.dea.joris.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tracks")
public class AllTracks {

    private TrackService trackService = new TrackService();
    private UserService userService = new UserService();

    @GetMapping(produces = "application/json")
    public ResponseEntity getAllTracks(@RequestParam("forPlaylist") int playlistID, @RequestParam("token") String token) {
        try {
            userService.verifyToken(token);
            TracksResponseDTO tracksResponseDTO = trackService.getListOfTracks(playlistID);
            return ResponseEntity.ok().body(tracksResponseDTO);
        } catch (TokenException e) {
            return ResponseEntity.status(403).build();
        }
    }
}
