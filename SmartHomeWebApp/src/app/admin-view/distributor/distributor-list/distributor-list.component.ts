import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Distributor } from 'src/app/models/distributor.model';
import { DataStorageService } from 'src/app/shared/data-storage.service';
import { DistributorService } from '../distributor.service';

@Component({
  selector: 'app-distributor-list',
  templateUrl: './distributor-list.component.html',
  styleUrls: ['./distributor-list.component.css']
})
export class DistributorListComponent implements OnInit {
  distributors: Distributor[];
  subscription: Subscription;
  constructor(private distributorService: DistributorService,
              private dataStorageService: DataStorageService) { }

  ngOnInit() {
    this.subscription = this.distributorService.distributorsChanged
      .subscribe(
        (distributors: Distributor[]) => {
          this.distributors = distributors;
        }
      );
    this.distributors = this.distributorService.getDistributors();
    this.dataStorageService.fetchDistributors();
    this.distributors = this.distributorService.getDistributors();
  }

}
