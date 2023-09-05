package com.feignsongifyclient.songify.response;


import com.feignsongifyclient.songify.request.SongifyRequest;

import java.util.Map;

public record SongifyResponse(Map<Integer, GetSongResponseById> songs) {
}
