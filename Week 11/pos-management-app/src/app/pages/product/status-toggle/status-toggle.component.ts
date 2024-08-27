import { Component, Input, Output, EventEmitter } from '@angular/core';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-status-toggle',
  standalone: true,
  imports: [CommonModule, MatSlideToggleModule],
  template: `
    <mat-slide-toggle
      [checked]="status === 'ACTIVE'"
      (change)="onToggleChange($event)">
    </mat-slide-toggle>
  `,
})
export class StatusToggleComponent {
  @Input() status: string = 'INACTIVE';
  @Output() statusChange = new EventEmitter<string>();

  onToggleChange(event: any) {
    const newStatus = event.checked ? 'ACTIVE' : 'INACTIVE';
    this.statusChange.emit(newStatus);
  }
}
