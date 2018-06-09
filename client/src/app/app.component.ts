import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../environments/environment";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent  implements OnInit {

  private readonly url = `${environment.employeeServiceUrl}/list`;

  data;

  constructor(public http: HttpClient) {
  }

  ngOnInit() {
    this.refresh();
  }

  refresh() {
    this.data = null;
    this.http.get(this.url, {})
      .subscribe(data => {
        console.log("Result:" + data);
        this.data = data;
      });
  }
}


