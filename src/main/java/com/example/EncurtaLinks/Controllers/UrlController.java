package com.example.EncurtaLinks.Controllers;

import com.example.EncurtaLinks.Dtos.UrlCurtaResponse;
import com.example.EncurtaLinks.Dtos.UrlcurtaRequest;
import com.example.EncurtaLinks.Models.Url;
import com.example.EncurtaLinks.Repositories.UrlRepository;
import com.example.EncurtaLinks.Services.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UrlController {

    private final UrlRepository urlRepository;

    @Autowired
    UrlService urlService;

    @Autowired
    public UrlController(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }


    @PostMapping("/shortenUrl")
    public ResponseEntity<UrlCurtaResponse> ShortenUrl(@RequestBody String urlOriginal, UrlcurtaRequest request, HttpServletRequest servletRequest){

        String urlCurta = urlService.EncurtarUrl(urlOriginal);

        var redirectUrl = servletRequest.getRequestURL().toString().replace("/shortenUrl", urlCurta);


        return ResponseEntity.ok(new UrlCurtaResponse(redirectUrl));
    }



}
