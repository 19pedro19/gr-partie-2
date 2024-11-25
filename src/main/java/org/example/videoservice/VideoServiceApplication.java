package org.example.videoservice;

import lombok.AllArgsConstructor;
import org.example.videoservice.entities.Creator;
import org.example.videoservice.entities.Video;
import org.example.videoservice.repositories.CreatorRepository;
import org.example.videoservice.repositories.VideoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication @AllArgsConstructor
public class VideoServiceApplication {
    CreatorRepository creatorRepository;
    VideoRepository videoRepository;

    public static void main(String[] args) {
        SpringApplication.run(VideoServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(){
        return args -> {
            List<Creator> creators = List.of(
                    Creator.builder().name("Aziz").email("aziz@gmail.com").build(),
                    Creator.builder().name("Ahmed").email("ahmed@gmail.com").build(),
                    Creator.builder().name("Khalil").email("khalil@gmail.com").build());
            creatorRepository.saveAll(creators);

            List<Video> videos = List.of(
                    Video.builder().url("http://video.com/1").name("video2").description("video 1 desc").creator(creators.get(0)).build(),
                    Video.builder().url("http://video.com/2").name("video2").description("video 2 desc").creator(creators.get(1)).build(),
                    Video.builder().url("http://video.com/3").name("video3").description("video 2 desc").creator(creators.get(2)).build()
            );
            videoRepository.saveAll(videos);

        };

    }




}


