import { Injectable } from '@angular/core';
import { AppConfig }  from 'config';
import { FormGroup, AbstractControl } from '@angular/forms';
import { SERVER_ERROR } from 'common/validations';
import { NotificationsService } from 'angular2-notifications';

import axios, { AxiosRequestConfig, AxiosPromise } from 'axios';
const path = require('path');
const DEFAULT_API = '';

@Injectable()
export class ActionService {

  private defaultConfig : AxiosRequestConfig  = {
      baseURL : path.join(AppConfig.serverContext, DEFAULT_API),
      timeout: AppConfig.defaultTimeout,
      responseType: 'json',
      headers: {}    
  }

  constructor(private notify : NotificationsService) {
    axios.interceptors.request.use(
        request => { 
            return request; 
        }, 
        error => { 
            console.error('Request error', error.stack || error);
            return Promise.reject(error);
        }
    );
    axios.interceptors.response.use(
        response => { 
            return response; 
        },
        error => {        
            this.processErrors(error.response.data)
            console.error('Response error', error.stack || error);
            return Promise.reject(error);
        }
    );
  }

  private processErrors(error :any) {
    if (error.response && error.response.status != 200) {
        if (error.response.status == 404) {
            //redirect404()
        } else if (error.response.status == 403) {
            //redirect403()
        } else if (error.response.status != 409) {
            this.printError(error)
        }
    }
    else {
        this.printError(error)
    }
  }

  private printError = (error: any) => {
    if (error && error.response && error.response.data && error.response.data.message) {
         this.notify.error("", error.response.data.message)
    }
  }

  url(link: string) : string {
    return path.join(AppConfig.serverContext, link);
  }

  get(link: string, config: AxiosRequestConfig = this.defaultConfig) : AxiosPromise { 
     return axios.get(link, config).then((response) => {
         return Promise.resolve(response.data);
     });
  }

  post(link: string, data: any, config: AxiosRequestConfig = this.defaultConfig) : AxiosPromise { 
     return axios.post(link, data, config).then((response) => {
         return Promise.resolve(response.data);
     });
  }

  delete(link: string, config: AxiosRequestConfig = this.defaultConfig) : AxiosPromise { 
     return axios.delete(link, config);
  }

  put(link: string, data: any, config: AxiosRequestConfig = this.defaultConfig) : AxiosPromise { 
     return axios.put(link, data, config);
  }

  postForm(link: string, data: any, form: FormGroup, showErrors = true, config: AxiosRequestConfig = this.defaultConfig) {
    return this.post(link, data, config).catch((error) => {
        if (showErrors && error.response && error.response.status == 400) {
            this.applyErrors(form, error.response.data.fields);
        }
        return Promise.reject(error);
     });

  }

  applyErrors(form: FormGroup, data: any): void {
    // prepare errors object
    if (data && data.length > 0) {
      let errors: any = [];
      _.map(data, (e: any) => {
        let fieldErrors = _.find(errors, { field: e.field });
        if (fieldErrors) {
          fieldErrors.errors.push(e.message)
        } else {
          errors.push({ field: e.field, errors: [e.message] });
        }
      })
      // iterate through all errors fields reponsed from server
      errors.map((fieldError: any) => {
        // get nested form control
        let formControl: AbstractControl = form.get(fieldError.field);
        // prepare object to set errors
        let fieldErrors: any = {};
        // put key as server error and value as messages errors
        fieldErrors[SERVER_ERROR] = fieldError.errors;
        // set it into form control
        formControl.setErrors(fieldErrors);
      });
    }
  }
}
