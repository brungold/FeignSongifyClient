package com.feignsongifyclient;

import com.feignsongifyclient.songify.SongifyProxy;
import com.feignsongifyclient.songify.request.GetSongRequestById;
import com.feignsongifyclient.songify.request.SongifyRequest;
import com.feignsongifyclient.songify.response.GetSongResponseById;
import com.feignsongifyclient.songify.response.SongifyResponse;
import feign.FeignException;
import feign.RetryableException;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;

import java.util.Map;

@SpringBootApplication
@EnableFeignClients
@Log4j2
public class FeignSongifyClientApplication {
    private final SongifyProxy songifyClient;

    public FeignSongifyClientApplication(SongifyProxy songifyClient) {
        this.songifyClient = songifyClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(FeignSongifyClientApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void run() {
        try {
            //GET all songs
            SongifyResponse response = songifyClient.fetchAllSongs();
            displayAllSongs(response.songs());

//            // GET by ID
//            GetSongRequestById responseDto = songifyClient.getSongById(2);
//            SongifyRequest request = new SongifyRequest(responseDto.song().name(), responseDto.song().artist());
//            log.info("Your song " + request.songName() + " by: " + request.artist());

            //POST
//            SongifyRequest request = new SongifyRequest("Parostatek", "Karol Krawczyk");
//            SongifyResponse response = songifyClient.addSong(request);
//            log.info("New song posted : {}", request.songName() + " " + request.artist());

            //DELETE
//            songifyClient.deleteByPathVariableId(1);
//            log.info("You deleted song");

            //PUT
//            songifyClient.putByPathVariableId(2, new SongifyRequest("Al Bundy", "Song"));
//            log.info("you have updated the data");

            //PATCH
//            SongifyRequest partiallyUpdateSong = new SongifyRequest("Trolololo","Jan");
//            songifyClient.patchByPathVariableID(4, partiallyUpdateSong);
//            log.info("Partially updated record: songName: " + partiallyUpdateSong.artist() + " and " + "artist: " + partiallyUpdateSong.artist());

        } catch (FeignException.FeignClientException feignException) {
            log.error("client exception: " + feignException.status());
        } catch (FeignException.FeignServerException feignException) {
            System.out.println("server exception: " + feignException.status());
        } catch (RetryableException retryableException) {
            System.out.println("retryable exception " + retryableException.getMessage());
        } catch (FeignException feignException) {
            System.out.println(feignException.getMessage());
            System.out.println(feignException.status());
        }

    }

    private void displayAllSongs(Map<Integer, GetSongResponseById> songs) {
        songs.forEach(
                (key, value) ->
                        log.info("Song ID: {}, Name: {}, Artist: {}", key, value.name(), value.artist())
        );
    }
    private void displayAllSongsAsOneBlock(Map<Integer, GetSongResponseById> songs) {
        StringBuilder songList = new StringBuilder("Song List:\n");

        songs.forEach((key, value) ->
                songList.append("Song ID: ")
                        .append(key)
                        .append(", Name: ")
                        .append(value.name())
                        .append(", Artist: ")
                        .append(value.artist())
                        .append("\n")
        );

        log.info(songList.toString());
    }
}

