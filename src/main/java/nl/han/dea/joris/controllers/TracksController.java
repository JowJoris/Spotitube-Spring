package nl.han.dea.joris.controllers;

import nl.han.dea.joris.exceptions.TokenException;
import nl.han.dea.joris.services.TrackService;
import nl.han.dea.joris.services.UserService;
import nl.han.dea.joris.track.TrackRequestDTO;
import nl.han.dea.joris.track.TracksResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("playlists/{playlistID}/tracks")
public class TracksController {

    private TrackService trackService = new TrackService();
    private UserService userService = new UserService();


    @GetMapping(produces = "application/json")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity getTracks(@PathVariable("playlistID") int playlistID, @RequestParam("token") String token) {
        try{
            userService.verifyToken(token);
            TracksResponseDTO tracksResponseDTO = trackService.getTracks(playlistID);
            return ResponseEntity.ok().body(tracksResponseDTO);
        }
        catch (TokenException e){
            return ResponseEntity.status(403).build();
        }
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity addTrack (@PathVariable("playlistID") int playlistID, @RequestParam("token") String token, TrackRequestDTO trackRequestDTO) {
        try{
            userService.verifyToken(token);
            TracksResponseDTO tracksResponseDTO= trackService.addTrack(playlistID, trackRequestDTO.getId(), trackRequestDTO.isOfflineAvailable());
            return ResponseEntity.ok().body(tracksResponseDTO);
        }
        catch (TokenException e){
            return ResponseEntity.status(403).build();
        }
    }

    @DeleteMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity deleteTrack(@PathVariable("playlistID") int playlistID, @PathVariable("id") int id, @RequestParam("token") String token){
        try {
            userService.verifyToken(token);
            TracksResponseDTO tracksResponseDTO = trackService.deleteTrack(playlistID, id);
            return ResponseEntity.ok().body(tracksResponseDTO);
        } catch (TokenException e) {
            return ResponseEntity.status(403).build();
        }
    }
}
