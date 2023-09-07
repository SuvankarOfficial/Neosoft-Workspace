import { Component } from '@angular/core';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent {

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

}
