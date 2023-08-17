package com.todo.todobackend.controller.exeptions;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class StandartError implements Serializable{

    private Long timestamp;
    private Integer status;
    private String error;
    
}
