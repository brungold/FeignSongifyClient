package com.feignsongifyclient.songify;

import java.util.Map;

public record SongifyResponse(Map<Integer, SongifyRequest> songs) {
}
