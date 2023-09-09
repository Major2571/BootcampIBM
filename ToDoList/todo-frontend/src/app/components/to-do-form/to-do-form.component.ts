import { Component, ElementRef, Inject, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
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

  constructor(private service: ToDoService) { };
  
  ngOnInit(): void {
    this.viewAllToDo();
  }

  saveNewToDo(form: NgForm) {
    if (this.toDo.id !== undefined) {
      this.service.putToDo(this.toDo).subscribe(() => {
        this.cleanForm(form);
      });
    } else {
      this.service.postToDo(this.toDo).subscribe(() => {
        this.cleanForm(form);
      });
    }
  }
  
  cleanForm(form: NgForm) {
    form.resetForm();
    this.toDo = {} as toDo;
    this.viewAllToDo();
  }

  viewAllToDo(): void {
    this.service.getAllToDo()
      .subscribe(response => {
        response.forEach((item) => {
          item.color = this.getRandomColor();
        });
        this.listToDo = response;
      });
  }

  findAllOpenToDo(): void {
    this.service.getAllToDoOpen()
      .subscribe(response => this.listToDo = response)
  }

  findAllClosedToDo(): void {
    this.service.getAllToDoClosed()
      .subscribe(response => this.listToDo = response)
  }

  getRandomColor() {
    const min = 150;
    const max = 255;

    const r = Math.floor(Math.random() * (max - min + 1) + min);
    const g = Math.floor(Math.random() * (max - min + 1) + min);
    const b = Math.floor(Math.random() * (max - min + 1) + min);

    return `rgb(${r},${g},${b})`;
  }

  checkedToDo(t: toDo): void{
    t.completed = !t.completed;
  }

  deleteToDo(t: toDo) {
    this.service.deleteToDo(t)
      .subscribe(() => {
        this.viewAllToDo();
      });
  }

  selectToDo( index: number ): void {
    this.toDo = this.listToDo[index];
  }

  editToDo( t: toDo ):void {
    console.log('Edit button clicked:', t);
    this.toDo = { ...t };
  }
}
