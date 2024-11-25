package org.example.videoservice.web;

import lombok.AllArgsConstructor;
import org.example.videoservice.entities.Creator;
import org.example.videoservice.entities.CreatorRequest;
import org.example.videoservice.entities.Video;
import org.example.videoservice.entities.VideoRequest;
import org.example.videoservice.repositories.CreatorRepository;
import org.example.videoservice.repositories.VideoRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

@Controller @AllArgsConstructor
public class VideoGraphQlController {
    private CreatorRepository creatorRepository;
    private VideoRepository videoRepository;

    @QueryMapping
    public List<Video> videoList(){
        return videoRepository.findAll();
    }
    @QueryMapping
    public Creator creatorById(@Argument Long id){
        return creatorRepository.findById(id).get();
    }
    @MutationMapping
    public Creator saveCreator(@Argument CreatorRequest creator){

        return creatorRepository.save(Creator.builder()
                        .name(creator.getName())
                        .email(creator.getEmail())
                .build());
    }
    @MutationMapping
    public Video saveVideo(@Argument VideoRequest video) {
        Creator creator = creatorRepository.findById(video.getCreator().getId())
                .orElseThrow(() -> new RuntimeException("Creator not found"));


        return videoRepository.save(Video.builder()
                .creator(creator)
                .url(video.getUrl())
                .description(video.getDescription())
                .name(video.getName())
                .datePublication(video.getDatePublication())
                .build());
    }
    @SubscriptionMapping
    public Flux<Video> notifyVideoChange(){
        return Flux.fromStream(
                Stream.generate(() ->{
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        throw new RuntimeException(e);
                    }
                    Creator creatorRequest = Creator.builder()
                            .name("jawad")
                            .email("azouzi@gmail.com")
                            .build();
                    Creator creator = creatorRepository.save(creatorRequest);
                    Video video = videoRepository.findById(1L).get();
                    video.setCreator(creator);
                    videoRepository.save(video);
                    return video;
                })
        );
    }
}
