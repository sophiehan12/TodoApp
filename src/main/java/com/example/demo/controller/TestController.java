package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TestRequestBodyDTO;
import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.method.HandlerTypePredicate.builder;

@RestController
@RequestMapping("test")

public class TestController {
    @GetMapping("/testGetMapping")
    public String testController(){
        return "Hello World hyeon!";
    }

    @GetMapping("/{id}")
    public String testControllerWithPathVariables(@PathVariable(required = false)int id){
        return "Hello WOrld, hyeon" + id;
    }

    @GetMapping("/testRequestParam")
    public String testControllerRequestParam(@RequestParam(required = false)int id){
        return "Hello world 한성현" + id;
    }

    @GetMapping("/testRequestBody")
    public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO){
        return "Hello world 한성현" + testRequestBodyDTO.getId() + ", message: "
                + testRequestBodyDTO.getMessage();
    }

    @GetMapping("/testResponseBody")
    public ResponseDTO<String> testControllerResponseBody(){
        List<String> list = new ArrayList<>();
        list.add("hsh");
        list.add("hsh2");
        list.add("hsh3");

        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return response;
    }

    @GetMapping("/testResponseEntity")
    public ResponseEntity<?> testControllerResponseEntity(){
        List<String> list = new ArrayList<>();
        list.add("hsh");
        list.add("hsh2");
        list.add("hsh3");

        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();

        //return ResponseEntity.ok().body(response);
        return ResponseEntity.badRequest().body(response);
    }

}
