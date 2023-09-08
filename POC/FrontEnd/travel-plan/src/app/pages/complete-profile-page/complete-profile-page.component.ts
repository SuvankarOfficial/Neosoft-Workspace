import { UserService } from './../../service/user.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-complete-profile-page',
  templateUrl: './complete-profile-page.component.html',
  styleUrls: ['./complete-profile-page.component.css']
})
export class CompleteProfilePageComponent {

  constructor(private userService:UserService){}

  username: string = "";
  password: string = "";
  confirmPassword: string = "";
  otp: string = "";
  passwordError: boolean = false;
  usernameError: boolean = false;

  checkUsername(){
    this.userService.checkUsername(this.username).subscribe((result)=>{
      console.log(result);
      if (result.status) {
        this.usernameError = result.data; 
      }else{
        this.usernameError = true;
      };
    });
  }

  verifyOTP() {
    console.log(this.password);
    console.log(this.confirmPassword);
    
    if (this.password !== this.confirmPassword) {
      this.passwordError = true;
    }
    

  }

}
