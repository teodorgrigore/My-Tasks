import { Component, OnInit } from '@angular/core';
import { TaskService } from '../services/task.service';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {

  allTasks: any[] = [];
  currentTask: any = null;
  isEditMode: boolean = false;
  constructor(private taskService: TaskService) { }

  ngOnInit(): void {
    this.initData();
  }

  initData(): void {
    this.taskService.getAll().subscribe((response: any[]) => this.allTasks = response);
  }

  onDelete(task: any): void {
    const answer = confirm(`Are you sure you want to delete the task ` + task.name + ' ?');
    if(answer) {
      this.taskService.delete(task.id).subscribe( () => this.initData());
    }
  }

  changeStatus(taskId: string, newStatus: string) : void{
    this.taskService.changeStatus(taskId, newStatus).subscribe( () => this.initData());
  }

  onUpdate(task: any): void {
    this.currentTask = null;
    this.currentTask = task;
    this.isEditMode = true;
    console.log("On update", task);
  }

  findTasksByStatus(tasksList:any, status: string): any[]{
    return tasksList.filter((task:any) => task.status===status);
  }
}
