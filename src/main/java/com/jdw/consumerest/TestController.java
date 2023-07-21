package com.jdw.consumerest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdw.consumerest.Entity.Joke;

@Controller
public class TestController {
    
    @GetMapping("/joke")
    public ResponseEntity<String> getJoke(RestTemplate restTemplate){
        try {
            Joke joke = restTemplate.getForObject("https://official-joke-api.appspot.com/random_joke3", Joke.class);
            System.out.println(joke);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "inline").contentType(MediaType.TEXT_PLAIN).body(joke.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/json-joke")
    public ResponseEntity<String> getJsonJoke(RestTemplate restTemplate){
        try {
            Joke joke = restTemplate.getForObject("https://official-joke-api.appspot.com/random_joke", Joke.class);
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonJoke = objectMapper.writeValueAsString(joke);

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "inline").contentType(MediaType.APPLICATION_JSON).body(jsonJoke);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
