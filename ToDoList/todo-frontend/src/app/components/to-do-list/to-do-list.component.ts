import { Component, OnInit } from '@angular/core';
import * as Masonry from 'masonry-layout';
import { toDo } from 'src/app/model/to-do';
import { ToDoService } from 'src/app/service/to-do.service';

@Component({
  selector: 'app-to-do-list',
  templateUrl: './to-do-list.component.html',
  styleUrls: ['./to-do-list.component.css']
})
export class ToDoListComponent implements OnInit {

  toDo = {} as toDo;

  listToDo: toDo[] = [];

  filterType: 'all' | 'closed' | 'open' = 'all';

  constructor(private service: ToDoService) { }

  ngOnInit() {
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
    t.completed = true;
  }


}
