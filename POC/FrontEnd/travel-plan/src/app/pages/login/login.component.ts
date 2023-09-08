import { ServiceResponseBean } from './../../entity/ServiceResponseBean';
import { UserService } from './../../service/user.service';
import { Component } from '@angular/core';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private userService:UserService){}

  onFlip(){
    document.getElementById("flip-box-inner")
    ?.classList.toggle('do-flip');
  }

  username:string = "";
  password:string = "";
  error:boolean = false;

  printData(){
    alert(this.username + " " + this.password);
  }

  getCheckUserValidation(){
    this.userService.checkUserValidation(this.username,this.password).subscribe((result)=>{
      console.log(result);
      if (result.status == true) {
        this.error = !result.data;
      }
    });
  }

}
