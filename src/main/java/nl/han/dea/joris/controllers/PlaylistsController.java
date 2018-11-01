package nl.han.dea.joris.controllers;

import nl.han.dea.joris.exceptions.TokenException;
import nl.han.dea.joris.playlist.PlaylistRequestDTO;
import nl.han.dea.joris.playlist.PlaylistsResponseDTO;
import nl.han.dea.joris.services.PlaylistService;
import nl.han.dea.joris.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlists")
public class PlaylistsController {
    private UserService userService = new UserService();
    private PlaylistService playlistService = new PlaylistService();

    @GetMapping(produces = "application/json")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity getPlaylists(@RequestParam("token") String token) {
        try {
            PlaylistsResponseDTO playlistsResponseDTO = playlistService.getPlaylists(userService.verifyToken(token));
            return ResponseEntity.ok().body(playlistsResponseDTO);
        } catch (TokenException e) {
            return ResponseEntity.status(403).build();
        }
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity addPlaylist(@RequestParam("token") String token, PlaylistRequestDTO playlistRequestDTO) {
        try {
            PlaylistsResponseDTO playlistsResponseDTO = playlistService.addPlaylist(userService.verifyToken(token), playlistRequestDTO.getName());
            return ResponseEntity.ok().body(playlistsResponseDTO);
        } catch (TokenException e) {
            return ResponseEntity.status(403).build();
        }
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity editPlaylist(PlaylistRequestDTO playlistRequestDTO, @PathVariable("id") int playlistID, @RequestParam("token") String token) {
        try {
            PlaylistsResponseDTO playlistsResponseDTO = playlistService.editPlaylist(playlistRequestDTO.getName(), playlistID, userService.verifyToken(token));
            return ResponseEntity.ok().body(playlistsResponseDTO);
        } catch (TokenException e) {
            return ResponseEntity.status(403).build();
        }
    }

    @DeleteMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity deletePlaylist(@PathVariable("id") int playlistID, @RequestParam("token") String token){
        try {
            PlaylistsResponseDTO playlistsResponseDTO = playlistService.deletePlaylist(playlistID, userService.verifyToken(token));
            return ResponseEntity.ok().body(playlistsResponseDTO);
        } catch (TokenException e) {
            return ResponseEntity.status(403).build();
        }
    }
}

