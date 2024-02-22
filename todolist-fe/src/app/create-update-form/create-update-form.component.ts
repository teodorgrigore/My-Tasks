import { Component, Input, OnChanges, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { TaskService } from '../services/task.service';

@Component({
  selector: 'app-create-update-form',
  templateUrl: './create-update-form.component.html',
  styleUrls: ['./create-update-form.component.css']
})
export class CreateUpdateFormComponent implements OnChanges {
  @Input()
  taskToUpdate: any;
  taskFormGroup: FormGroup = new FormGroup({
    idControl: new FormControl('', {nonNullable:true}),
    titleControl: new FormControl('', {nonNullable:true, validators: [Validators.required, Validators.minLength(2)]}),
    descriptionControl: new FormControl('', {nonNullable:true}),
    // dueDayControl: new FormControl('', {nonNullable:true, validators: [Validators.required]}),
    // dueTimeControl: new FormControl('', {nonNullable:true, validators: [Validators.required]})
    dueDateControl: new FormControl('', {nonNullable:true})

  })
  constructor(private taskService: TaskService) { }

  ngOnChanges(): void {
    console.log(this.taskToUpdate);
    this.prepareUpdate();
  }
    private prepareUpdate(): void{
      this.taskFormGroup.setValue({
        idControl: this.taskToUpdate.id,
        titleControl: this.taskToUpdate.title,
        descriptionControl: this.taskToUpdate.description,
        dueDateControl: this.taskToUpdate.dueDate
      })
    }

  onSubmit(): void {
    console.log(this.taskFormGroup);

    if(!this.taskFormGroup.valid) {
      alert("The form has errors");
      return;
    }

    const createUpdateTaskDTO = {
      title: this.taskFormGroup.value.titleControl,
      description: this.taskFormGroup.value.descriptionControl,
      dueDate: this.taskFormGroup.value.dueDateControl
    }

    this.taskService.create(createUpdateTaskDTO).subscribe( () => {
        console.log("Task created");
        this.taskFormGroup.reset();
        
    });

  }


}
