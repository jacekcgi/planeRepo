import { Injectable, Inject } from '@angular/core';
import { AppConfig } from 'config';
import { TranslateService } from 'ng2-translate';
import { NotificationsService } from 'angular2-notifications';
import { defaultIcons } from 'angular2-notifications/src/icons';

export class NotificationService {

    private notificationOptions = {
        timeOut: AppConfig.defaultNotificationTimeout,
        lastOnBottom: true,
        clickToClose: true,
        maxLength: 0,
        maxStack: 7,
        showProgressBar: true,
        pauseOnHover: true,
        preventDuplicates: false,
        preventLastDuplicates: 'visible',
        rtl: false,
        animate: 'scale',
        position: ['right', 'top']
    }

    constructor(@Inject(TranslateService) private translateService: TranslateService, @Inject(NotificationsService) private ans: NotificationsService) {

    }

    success(contentProperty: string) {
        this.display('successTitle', contentProperty, 'success', defaultIcons.success);
    }

    info(contentProperty: string) {
        this.display('infoTitle', contentProperty, 'info', defaultIcons.info);
    }

    warn(contentProperty: string) {
        this.display('warnTitle', contentProperty, 'alert', defaultIcons.alert);
    }

    error(contentProperty: string) {
        this.display('errorTitle', contentProperty, 'error', defaultIcons.success);
    }

    display(titleProperty: string, contentProperty: string, type: string, icon: string, override?: any) {
        let title: string, content: string;
        this.translateService.get(titleProperty).subscribe((value: string) => title = value);
        this.translateService.get(contentProperty).subscribe((value: string) => content = value);
        this.ans.set({ title: title, content: content, type: type, icon: icon, override: override }, true);
    }

    getOptions() {
        return this.notificationOptions;
    }
}