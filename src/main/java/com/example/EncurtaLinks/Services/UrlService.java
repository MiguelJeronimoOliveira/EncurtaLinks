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


    public String gerarUrl(String urlOriginal){

        String urlCurta = UUID.randomUUID().toString().substring(0, 4);

        while(urlRepository.existByUrlCurta(urlCurta)){
            urlCurta = UUID.randomUUID().toString().substring(0, 4);
        }

        Url url = new Url();
        url.setUrl(urlOriginal);
        url.setUrlCurta(urlCurta);
        urlRepository.save(url);

        return urlCurta;
    }




}
