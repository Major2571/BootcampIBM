import { Component, OnInit } from '@angular/core';
import { toDo } from 'src/app/model/to-do';
import { ToDoService } from 'src/app/service/to-do.service';

@Component({
  selector: 'app-to-do-form',
  templateUrl: './to-do-form.component.html',
  styleUrls: ['./to-do-form.component.css']
})
export class ToDoFormComponent implements OnInit {

  toDo = {} as toDo;

  listToDo: toDo[] = [];

  constructor(private service: ToDoService) { }
  
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }
  
  

  saveNewToDo(): void {
    this.service.postToDo(this.toDo)
      .subscribe(response => {
        this.listToDo.push(response);
        this.toDo = {} as toDo;
        alert("New toDO added")
      });
  }


}
