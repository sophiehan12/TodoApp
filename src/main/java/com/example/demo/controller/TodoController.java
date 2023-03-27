package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import com.example.demo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto){
        try{
            String temporaryUserId = "temporary-user";

            TodoEntity entity = TodoDTO.toEntity(dto);

            entity.setId(null);

            entity.setUserId(temporaryUserId);

            List<TodoEntity> entities = service.create(entity);

            List<TodoDTO> dtos = entities.stream()
                    .map(TodoDTO :: new)
                    .collect(Collectors.toList());

            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();


            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            String error= e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<?> retrieveTodoList(){
        String temporaryUserId = "temporary-user";

        List<TodoEntity> entities = service.retrieve(temporaryUserId);

        List<TodoDTO> dtos = entities.stream()
                .map(TodoDTO :: new)
                .collect(Collectors.toList());

        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();


        return ResponseEntity.ok().body(response);
    }
}
