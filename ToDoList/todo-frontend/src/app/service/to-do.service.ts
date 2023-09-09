import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { toDo } from '../model/to-do';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ToDoService {

  private apiUrl = environment.upiUrl;

  constructor(private http:HttpClient) { }

  getAllToDo(): Observable<toDo[]> {
    return this.http.get<toDo[]>(this.apiUrl + '/all')
  }

  getAllToDoOpen(): Observable<toDo[]> {
    return this.http.get<toDo[]>(this.apiUrl + '/open')
  }

  getAllToDoClosed(): Observable<toDo[]> {
    return this.http.get<toDo[]>(this.apiUrl + '/closed')
  }

  postToDo(obj: toDo): Observable<toDo> {
    return this.http.post<toDo>(this.apiUrl, obj)
  }

  putToDo(obj: toDo): Observable<toDo>{
    return this.http.put<toDo>( this.apiUrl + '/' + obj.id + '/update', obj )
  }

  deleteToDo(obj: toDo): Observable<toDo>{
    return this.http.delete<toDo>( this.apiUrl + '/' + obj.id + '/delete' )
  }

}
