package com.feignsongifyclient.songify;

import com.feignsongifyclient.songify.request.GetSongRequestById;
import com.feignsongifyclient.songify.request.SongifyRequest;
import com.feignsongifyclient.songify.response.GetSongResponseById;
import com.feignsongifyclient.songify.response.SongifyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "songify-server-client")
public interface SongifyProxy {
    // GET http://localhost:8080/songs
    @GetMapping("/songs")
    SongifyResponse fetchAllSongs();

    @GetMapping("/songs/{id}")
    GetSongRequestById getSongById(@PathVariable Integer id, @RequestHeader String requestId);

    @PostMapping("/songs")
    SongifyResponse addSong(@RequestBody SongifyRequest request);

    @DeleteMapping("/songs/{songId}")
    void deleteByPathVariableId(@PathVariable Integer songId);

    @PutMapping("/songs/{songId}")
    SongifyRequest putByPathVariableId(@PathVariable Integer songId, @RequestBody SongifyRequest request);

    @PatchMapping(value ="/songs/{songId}", consumes = "application/json")
    SongifyRequest patchByPathVariableID(@PathVariable Integer songId, @RequestBody SongifyRequest request);
}
