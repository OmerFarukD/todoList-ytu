package com.ytu.todolist.exceptions;

public class NotFoundException  extends  RuntimeException{
    public NotFoundException(Long id, String tableName) {
        super("Id : "+ id+" ye ait "+tableName+" bulunamadı.");
    }
}
