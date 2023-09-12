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

  btn: boolean = true;
  cardsContainer: boolean = true;

  constructor(private service: ToDoService) { };

  ngOnInit(): void {
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

  saveNewToDo(form: NgForm) {
    if (this.toDo.id !== undefined) {
      this.service.putToDo(this.toDo).subscribe(() => {
        this.cleanForm(form);
        this.btn = true;
      });
    } else {
      this.service.postToDo(this.toDo).subscribe(() => {
        this.cleanForm(form);
      });
    }
  }

  editToDo(t: toDo): void {
    this.toDo = { ...t };
    this.btn = false;
  }

  deleteToDo(t: toDo) {
    this.service.deleteToDo(t)
      .subscribe(() => {
        this.viewAllToDo();
      });
  }

  cleanForm(form: NgForm) {
    form.resetForm();
    this.toDo = {} as toDo;
    this.viewAllToDo();
  }

  getRandomColor() {
    const min = 150;
    const max = 255;

    const r = Math.floor(Math.random() * (max - min + 1) + min);
    const g = Math.floor(Math.random() * (max - min + 1) + min);
    const b = Math.floor(Math.random() * (max - min + 1) + min);

    return `rgb(${r},${g},${b})`;
  }

  checkedToDo(t: toDo): void {
    t.completed = !t.completed
  }

  btnCancel(): void {
    this.toDo = {} as toDo;
    this.btn = true;
    this.cardsContainer = true;
  }
}
