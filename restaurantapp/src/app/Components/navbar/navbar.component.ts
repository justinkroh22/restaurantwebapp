import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/Services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  public isLoggedIn: boolean;
  public login_type: string;

  constructor(public authService: AuthService) {
    this.isLoggedIn = authService.isLoggedIn;
    this.login_type = authService.user_type;
   }

  ngOnInit(): void {
  }

  logout():void{
    this.authService.logout();
  }

}
