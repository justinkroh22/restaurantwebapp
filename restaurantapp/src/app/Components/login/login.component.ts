import { Component, OnInit } from '@angular/core';

import { LoginClass } from '../../Models/loginclass';
import { Router } from '@angular/router';

import { AuthService } from '../../Services/auth.service';
import { FormBuilder, FormGroup } from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginStatus: boolean = false;
  userType: string = '';
  firstName: string = '';

  loginForm: FormGroup;


  message: string = '';

  constructor(private formBuilder: FormBuilder, private authService: AuthService, public router: Router) {
    this.loginForm = this.formBuilder.group({

      email: '',
      password: ''

    })

   }



   submitLoginForm() {

    console.log(this.loginForm.value);
    
    console.log(this.loginForm.get('email')?.value);

    var password = this.loginForm.get('password')?.value;
    var email = this.loginForm.get('email')?.value;


    //let userLogin: LoginClass = new LoginClass(email, password);

    //console.log(userLogin);

    // This data is returned data after post just use .whatever field
    this.authService.login(email, password)
    .subscribe(data => {
      if (data != null){this.loginStatus = true;}
      else this.loginStatus = false;
    this.userType = data.body.user_type;
    this.firstName = data.body.firstName},
     error => {console.log(error)},
    () => this.setLoginFunction());
  }

//(data => {console.log(data.body.customer_id)})

/*
this.authService.login(email, password)
.subscribe((data => this.loginStatus = data.body),
 error => {console.log(error)},
() => this.setLoginFunction);
}
*/


  setLoginFunction(){
    console.log(this.loginStatus);

    console.log()
    this.setLogin(this.loginStatus);
    this.setUserType(this.userType);
    this.setFirstName(this.firstName);

    

    if (this.authService.isLoggedIn){
      this.authService.setSession(this.userType, this.firstName);
      this.router.navigate([this.authService.redirectUrl]);
    }
    else this.setMessage('You Suck at Loggin in, try again');
  }


  setUserType(user_type:string){
    this.authService.setUserType(user_type);
  }

  setFirstName(firstName:string){
    this.authService.setFirstName(firstName);
  }


  setLogin(correctCredentials: boolean) {

    this.authService.setLogin(correctCredentials);

  }

  setMessage(message:string){

    this.message = message;
  }

  ngOnInit(): void {
  }

}
