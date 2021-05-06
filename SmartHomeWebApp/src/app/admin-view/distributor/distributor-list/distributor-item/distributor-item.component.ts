import { Component, Input, OnInit } from '@angular/core';
import { Distributor } from 'src/app/models/distributor.model';

@Component({
  selector: 'app-distributor-item',
  templateUrl: './distributor-item.component.html',
  styleUrls: ['./distributor-item.component.css']
})
export class DistributorItemComponent implements OnInit {

  @Input() distributor: Distributor;
  @Input() index: number;

  constructor() { }

  ngOnInit(): void {
  }

}
