package com.example.EncurtaLinks.Services;

import com.example.EncurtaLinks.Models.Url;
import com.example.EncurtaLinks.Repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class UrlService {

    @Autowired
    UrlRepository urlRepository;


    private String gerarUrl(){
        return UUID.randomUUID().toString().substring(0, 5);
    }
    String urlCurta = gerarUrl();

    public String EncurtarUrl(String urlOriginal){

        Url url = new Url();
        url.setUrl(urlOriginal);
        url.setUrlCurta(urlCurta);
        urlRepository.save(url);

        return urlCurta;
    }





}
