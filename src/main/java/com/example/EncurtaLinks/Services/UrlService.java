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
        return UUID.randomUUID().toString().substring(0, 8);
    }

    String urlCurta = gerarUrl();

   public String EncurtarUrl(String urlOriginal){
       Optional<Url> existingUrl = urlRepository.findByUrl(urlOriginal);

       if(existingUrl.isPresent())
           return existingUrl.get().getUrlCurta();


       Url url = new Url();
        url.setUrl(urlOriginal);
        url.setUrlCurta(urlCurta);
        urlRepository.save(url);

        return urlCurta;
   }

   public String getUrlOriginal(String urlCurta){
       Optional<Url> url = urlRepository.findByUrlCurta(urlCurta);
       return url.map(Url::getUrl).orElseThrow(() -> new RuntimeException("Url nao encontrada"));
   }











}
