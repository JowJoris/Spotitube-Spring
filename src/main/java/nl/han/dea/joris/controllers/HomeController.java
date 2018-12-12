package nl.han.dea.joris.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping(produces = "application/json")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity hello() {
        return ResponseEntity.ok().body("Hello world!");
    }

}
