import './polyfills.ts';

import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { enableProdMode } from '@angular/core';
import { AppModule } from './app/app.module';

declare var module: any;
if (process.env.ENV === 'production') {
  enableProdMode();
} else if (process.env.ENV === 'development') {
  // Enables Hot Module Replacement.
  if (module.hot) {
      module.hot.accept();
  }
}
platformBrowserDynamic().bootstrapModule(AppModule);
