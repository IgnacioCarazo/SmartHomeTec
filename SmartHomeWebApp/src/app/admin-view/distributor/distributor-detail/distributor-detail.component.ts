import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Distributor } from 'src/app/models/distributor.model';
import { DistributorService } from '../distributor.service';

@Component({
  selector: 'app-distributor-detail',
  templateUrl: './distributor-detail.component.html',
  styleUrls: ['./distributor-detail.component.css']
})
export class DistributorDetailComponent implements OnInit {
  distributor: Distributor;
  id: number;

  constructor(private distributorService: DistributorService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
 
  }


}
