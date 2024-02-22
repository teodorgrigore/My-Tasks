import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private readonly BASE_URL = 'http://localhost:8080/tasks'

  constructor(private http: HttpClient) { }

  public create(createTaskDTO: {title: string, description: string, dueDate: string}) : Observable<void> {
    return this.http.post<void>(this.BASE_URL, createTaskDTO);
  }

  public getAll(): Observable<any[]> {
    return this.http.get<any>(this.BASE_URL);
  }

  public delete(taskId: string) : Observable<void> {
    return this.http.delete<void>(`${this.BASE_URL}/${taskId}`);
    }

  public changeStatus(id: string, newStatus: string): Observable<void> {
      const changeStatusDTO = {id, newStatus};
      return this.http.post<void>(`${this.BASE_URL}/status`, changeStatusDTO);
    }
}
