package com.feignsongifyclient.songify;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "songify-server-client")
public interface SongifyProxy {
    // GET http://localhost:8080/songs
    @GetMapping("/songs")
    SongifyResponse fetchAllSongs();

    @PostMapping("/songs")
    SongifyResponse addSong(@RequestBody SongifyRequest request);

    @DeleteMapping("/songs/{songId}")
    void deleteByPathVariableId(@PathVariable String songId);

}
