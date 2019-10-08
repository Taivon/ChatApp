import {Component, OnInit} from '@angular/core';
import {AppService} from "./app.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  messages = [];

  constructor(private appService: AppService) {
  }

  ngOnInit(): void {
    this.appService.getMessagesByRoom('developers').subscribe(res => {
      this.messages.push(res.data);
      console.log(res.data);
    });
  }
  title = 'helloWorld';
}
