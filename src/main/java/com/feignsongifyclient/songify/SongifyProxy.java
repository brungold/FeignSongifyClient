package com.feignsongifyclient.songify;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "songify-server-client")
public interface SongifyProxy {
    // GET http://localhost:8080/songs
    @GetMapping("/songs")
    SongifyResponse fetchAllSongs();

//do ogarnięcia
    @GetMapping("/songs/{id}")
    GetSongResponseDto getSongById(@PathVariable Integer id);

    @PostMapping("/songs")
    SongifyResponse addSong(@RequestBody SongifyRequest request);

    @DeleteMapping("/songs/{songId}")
    void deleteByPathVariableId(@PathVariable Integer songId);

    @PutMapping("/songs/{songId}")
    SongifyRequest putByPathVariableId(@PathVariable Integer songId, @RequestBody SongifyRequest request);

    // do ogarnięcia
    @PatchMapping("/songs/{songId}")
    SongifyRequest patchByPathVariableID(@PathVariable Integer songId, @RequestBody SongifyRequest request);
}
