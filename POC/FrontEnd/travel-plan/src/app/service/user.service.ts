import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from "@angular/common/http";
import { userBaseUrl } from './constant';
import { ServiceResponseBean } from '../entity/ServiceResponseBean';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient, private param:HttpParams) { }

  checkUserValidation(username:string, password:string){
    let param = new HttpParams();
    this.param.set("username",username);
    this.param.set("password",password);
    return this.http.post<ServiceResponseBean>(userBaseUrl+"/check-credential",this.param);
  }
}
