package com.feignsongifyclient;

import com.feignsongifyclient.songify.SongifyProxy;
import feign.FeignException;
import feign.RetryableException;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;

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

    @EventListener(FeignSongifyClientApplication.class)
    public void run() {
        try {
//            ItunesResponse response = itunesClient.makeSearchRequest("shawnmendes", 5);
            log.info(songifyClient.fetchAllSongs());
            songifyClient.deleteByPathVariableId("0");
//            log.info(sampleShawnMendesServerClient.addSong(new SampleShawnMendesRequest("In my Blood")));
//            log.info(sampleShawnMendesServerClient.addSong(new SampleShawnMendesRequest("Stitches")));

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
}
