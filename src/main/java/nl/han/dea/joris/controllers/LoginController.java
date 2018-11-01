package nl.han.dea.joris.controllers;

import nl.han.dea.joris.exceptions.UnauthorizedException;
import nl.han.dea.joris.login.LoginRequestDTO;
import nl.han.dea.joris.login.LoginResponseDTO;
import nl.han.dea.joris.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login")
public class LoginController {
    private UserService userService = new UserService();

    @PostMapping(produces = "application/json")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity login(@RequestBody LoginRequestDTO loginRequestDTO) {
        try{
            LoginResponseDTO loginResponseDTO = userService.authenticate(loginRequestDTO.getUser(), loginRequestDTO.getPassword());
            return ResponseEntity.ok().body(loginResponseDTO);
        }
        catch(UnauthorizedException e){
            return ResponseEntity.status(401).build();
        }
    }
}
