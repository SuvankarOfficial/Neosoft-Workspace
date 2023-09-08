import { User } from './../entity/User';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from "@angular/common/http";
import { userBaseUrl } from './constant';
import { ServiceResponseBean } from '../entity/ServiceResponseBean';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  checkUsername(username: string) {
    return this.http.get<ServiceResponseBean>(`${userBaseUrl}/check-username/${username}`);
  }

  constructor(private http:HttpClient) { }

  checkUserValidation(username:string, password:string){
    return this.http.post<ServiceResponseBean>(`${userBaseUrl}/check-credential?username=${username}&password=${password}`,null);
  }

  getOtp(firstName:string, lastName:string, email:string){
    const user ={} as User;
    user.firstName = firstName;
    user.lastName = lastName;
    user.email = email;
    return this.http.post<ServiceResponseBean>(`${userBaseUrl}/add`,user);
  }
}
