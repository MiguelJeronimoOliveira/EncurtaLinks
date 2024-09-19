package com.example.EncurtaLinks.Repositories;

import com.example.EncurtaLinks.Models.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UrlRepository extends JpaRepository<Url, UUID> {

   Optional<Url> findByUrlCurta(String shortUrl);
   Optional<Url> findByUrl(String originalUrl);
}
