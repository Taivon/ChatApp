import {Injectable, NgZone} from '@angular/core';
import {HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(private zone: NgZone) {
  }

  getMessagesByRoom(room: string): Observable<MessageEvent> {
    new HttpParams().set('room', room);
    return new Observable(observer => {

      const eventsource = new EventSource('http://localhost:8080/messages?room=' + room);

      eventsource.onmessage = event => {
        this.zone.run(() => {
          observer.next(event);
        });
      };

      eventsource.onerror = error => {
        this.zone.run(() => {
          observer.error(error);
        });
      };


    })
  }
}
