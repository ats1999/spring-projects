package com.todo.inmemory.repositories;

import java.util.ArrayList;

import com.todo.inmemory.DataBase;
import com.todo.inmemory.exceptions.UnprocessableEntityHttpException;
import com.todo.inmemory.models.ToDo;

public class ToDoRepositery {
	public ToDo getToDoById(long todoId) {
		
		for(ToDo todo : DataBase.todos) {
			if(todo.getId() == todoId) {
				return todo;
			}
		}
		
		return null;
	}
	
	public ArrayList<ToDo> getAllToDo() {
		return DataBase.todos;
	}
	
	public void saveToDo(ToDo todo) {
		DataBase.todos.add(todo);
	}
	
	public boolean updateToDo(ToDo todo) {
		for(int todoIdx = 0; todoIdx < DataBase.todos.size(); todoIdx++) {
			ToDo tempToDo = DataBase.todos.get(todoIdx);
			if(tempToDo.getId() == todo.getId()) {
				DataBase.todos.set(todoIdx, todo);
				return true;
			}
		}
		
		// in case todo is not present
		return false;
	}
	
	public boolean deleteToDoById(long todoId) throws UnprocessableEntityHttpException {
		for(int todoIdx = 0; todoIdx < DataBase.todos.size(); todoIdx++) {
			ToDo todo = DataBase.todos.get(todoIdx);
			if(todo.getId() == todoId) {
				DataBase.todos.remove(todoIdx);
				return true;
			}
		}
		
		// TODO: create custom exception classes
		throw new UnprocessableEntityHttpException("ToDo not found");
	}
}
