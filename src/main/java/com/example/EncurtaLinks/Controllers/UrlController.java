package com.example.EncurtaLinks.Controllers;

import com.example.EncurtaLinks.Services.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/urlCurta")
    public ResponseEntity<String> EncurtarUrl(@RequestBody String urlOriginal){
        String urlCurta = urlService.EncurtarUrl(urlOriginal);
        return ResponseEntity.ok(urlCurta);
    }

    @GetMapping("/{urlCurta}")
    public ResponseEntity<Void> RetornarParaUrlOriginal(@PathVariable String urlCurta, HttpServletResponse response) throws IOException {

        String urlOriginal = urlService.getUrlOriginal(urlCurta);
        response.sendRedirect(urlOriginal);
        return ResponseEntity.status(HttpStatus.FOUND).build();
    }



}
