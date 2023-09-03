package com.feignsongifyclient.songify;

import com.feignsongifyclient.songify.SongifyRequest;

import java.util.Map;

public record SongifyResponse(Map<Integer, SongifyRequest> songs) {
}
