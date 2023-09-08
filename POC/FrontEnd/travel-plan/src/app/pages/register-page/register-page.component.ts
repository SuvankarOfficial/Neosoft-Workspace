import { Router, Routes } from '@angular/router';
import { UserService } from './../../service/user.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent {

  constructor(private userService:UserService, private route:Router){}

  onFlip(){
    document.getElementById("flip-box-inner")
    ?.classList.toggle('do-flip');
  }

  email:string = "";
  firstName:string = "";
  lastName:string = "";

  printData(){
    alert(this.email + " " + this.firstName + " " + this.lastName);
  }

  getOTP(){
    this.userService.getOtp(this.firstName,this.lastName,this.email).subscribe((result)=>{
      console.log(result);
      if (result.status == true) {
        this.route.navigate(["/complete-profile"])
        console.log(result.data);
      }
    });
  }

}
