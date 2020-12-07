import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { AuthService } from './../services/auth.service'

@Injectable({
  providedIn: 'root'
})
export class CheckTokenGuard implements CanActivate 
{
  constructor
  (
    private authService: AuthService,
    private router: Router
  ){}

  canActivate(): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    
    return this.authService.checkToken()
        .pipe(            
            map(() => true),            
            catchError(() => this.router.navigate(['/login']))             
        );
    }  
  
}