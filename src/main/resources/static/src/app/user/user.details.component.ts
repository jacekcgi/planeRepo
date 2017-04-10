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
      password: [null, [Validators.minLength(4), Validators.maxLength(32)]],
      repeatedPassword: [null, [Validators.minLength(4), Validators.maxLength(32)]]
    },
    {validator: AppValidators.password('password', 'repeatedPassword')});
  }
  

  onSubmit() {
    if (this.sid) {
      this.userService.update(this.userForm).then((data) => {
        this.ns.success('user.successUpdated');
        this.sid = data['sid'];
        // clean password after update - do not show dots in gui
        this.userForm.get('password').reset();
        this.userForm.get('repeatedPassword').reset();
        this.userForm.get('user').patchValue(data);
      });
    } else {
       this.userService.save(this.userForm).then((data) => {
        this.ns.success('user.successCreated');
        this.sid = data['sid'];
        this.userForm.get('password').reset();
        this.userForm.get('repeatedPassword').reset();
        this.userForm.get('user').patchValue(data);
      });
    }
  }

}