package com.example.EncurtaLinks.Controllers;

import com.example.EncurtaLinks.Dtos.UrlCurtaResponse;
import com.example.EncurtaLinks.Dtos.UrlcurtaRequest;
import com.example.EncurtaLinks.Repositories.UrlRepository;
import com.example.EncurtaLinks.Services.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping()
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

    @GetMapping("{urlCurta}")
    public ResponseEntity<Void> Redirect(@PathVariable("urlCurta") String urlCurta){

        var url = urlService.GetUrlOriginal(urlCurta);

        if(url.isEmpty()){
            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url.get())).build();
    }


}
