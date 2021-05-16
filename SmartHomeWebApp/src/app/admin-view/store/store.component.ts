import { Component, OnInit } from '@angular/core';
import { DataStorageService } from 'src/app/shared/data-storage.service';
import * as XLSX from 'xlsx';

@Component({
  selector: 'app-store',
  templateUrl: './store.component.html',
  styleUrls: ['./store.component.css']
})
export class StoreComponent implements OnInit {

  data: [][];
  flag = false;
  constructor(private dataStorageService: DataStorageService) { }


  ngOnInit(): void {
    this.dataStorageService.getExcel();
  }



  loadExcelFile(event: any) {
    const target : DataTransfer =  <DataTransfer>(event.target);
    
    if (target.files.length !== 1) {throw new Error('Cannot use multiple files')};

    const reader: FileReader = new FileReader();

    this.flag = true;

    reader.onload = (e: any) => {
      const bstr: string = e.target.result;

      const workBook: XLSX.WorkBook = XLSX.read(bstr, { type: 'binary' });

      const workSheetName : string = workBook.SheetNames[0];

      const workSheet: XLSX.WorkSheet = workBook.Sheets[workSheetName];


      this.data = (XLSX.utils.sheet_to_json(workSheet, { header: 1 }));
      this.data = this.data.slice(1);

    };

    reader.readAsBinaryString(target.files[0]);
  }

}
