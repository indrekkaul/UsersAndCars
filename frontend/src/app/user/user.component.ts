import {Component, OnInit, Output} from '@angular/core';
import { UserService} from "../user.service";
import {User} from "../user";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  users : User[] | undefined;

  searchKeyword = '';
  sortDirection: 'asc' | 'desc' = 'asc';

  constructor(private userService : UserService) {
  }

  // Function to filter users based on the search keyword
  filterUsers(): void {
    this.userService.getUsers().subscribe(data => {
      this.users = data.filter(user =>
        user.name.toLowerCase().includes(this.searchKeyword.toLowerCase())
      );
    });
  }

  toggleSortDirection(): void {
    this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    this.sortUsers();
  }

  sortUsers(): void {
      if (this.sortDirection === 'asc') {
        // @ts-ignore
        this.users.sort((a,b) => (a.name > b.name ? -1 : 1));
      } else {
        // @ts-ignore
        this.users.sort((a,b) => (a.name < b.name ? -1 : 1));
      }
  }

  ngOnInit(): void {
    this.userService.getUsers().subscribe((data : User[])=> {
      console.log(data);
      this.users = data;
    })
  }

}
