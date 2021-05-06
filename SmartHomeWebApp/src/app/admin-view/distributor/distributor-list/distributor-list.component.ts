import { Component, OnInit } from '@angular/core';
import { Distributor } from 'src/app/models/distributor.model';
import { DistributorService } from '../distributor.service';

@Component({
  selector: 'app-distributor-list',
  templateUrl: './distributor-list.component.html',
  styleUrls: ['./distributor-list.component.css']
})
export class DistributorListComponent implements OnInit {
  distributors: Distributor[];
  constructor(private distributorService: DistributorService) { }

  ngOnInit() {
    
    this.distributors = this.distributorService.getDistributors();
    console.log(this.distributors);
  }

}
