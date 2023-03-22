package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import com.example.demo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("todo")
public class TodoController {

    @GetMapping("/testTodo")
    public ResponseEntity<?> testTodo1(){

        /*0320 숙제*/
        String myid = "123";
        String mytitle = "성현";
        boolean mydone = true;

        TodoEntity myentity = new TodoEntity();

        myentity.setId(myid);
        myentity.setTitle(mytitle);
        myentity.setDone(mydone);

        TodoDTO response = new TodoDTO(myentity);
        //TodoDTO response = TOdoDTO.builder().id("123").title("성현").done("true").build();

        return ResponseEntity.ok().body(response);
    }


    //0322 수업
    @Autowired
    private TodoService service;

    @GetMapping("/test")
    public ResponseEntity<?> testTodo(){
        String str = service.testService();

        List<String> list = new ArrayList<>();
        list.add(str);

        //ResponseDTO<String> response = ResponseDTO<String>builder()
        //        .data(list).build();
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();

        return ResponseEntity.ok().body(response);
    }
}
