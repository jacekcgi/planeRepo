import { Component } from '@angular/core';
import { AbstractControl, FormGroup, Validators, FormBuilder, ValidatorFn } from '@angular/forms';
import { UserService } from 'app/services';
import { NotificationService } from 'app/services';
import { ActivatedRoute, Params } from '@angular/router';
import { AppValidators } from 'common/validations';

@Component({
  selector: 'page-user-details',
  templateUrl: './user.details.component.html',
})
export class UserDetailsComponent {
  sid: string = null;
  userForm: FormGroup;

  constructor(private fb: FormBuilder, private userService: UserService, private ns: NotificationService, private route: ActivatedRoute) {
    route.queryParams.subscribe((params: Params) => {
        this.sid = params['sid'];
      });
   }

  ngOnInit() {
    this.createForm();
    if (this.sid) {
      this.userService.getUser(this.sid).then((user) => {
        this.userForm.get('user').patchValue(user);
      });
    }     
  }

  createForm() {
    this.userForm = this.fb.group({
      user: this.fb.group({
        sid: [''],
        login: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(32)]],
        name: ['', [Validators.required, Validators.maxLength(32)]],
        surname: ['', [Validators.required, Validators.maxLength(64)]],
        active: [true]
      }),
      password: ['', Validators.required],
      repeatedPassword: ['', Validators.required]
    },
    {validator: AppValidators.password('password', 'repeatedPassword')});
  }
  

  onSubmit() {
    if (this.sid) {
      this.userService.update(this.userForm).then((response) => {
        this.ns.success('user.successUpdated');
        this.sid = response["sid"];
        this.userForm.get('user').patchValue(response);
      });
    } else {
       this.userService.save(this.userForm).then((response) => {
        this.ns.success('user.successCreated');
        this.sid = response["sid"];
        this.userForm.get('user').patchValue(response);
      });
    }
  }

}