import { TestBed, ComponentFixture } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { AppModule } from './app.module';
import { APP_BASE_HREF } from '@angular/common';

import { By } from '@angular/platform-browser';
import { } from 'jasmine';

// another test to show that all *.spec.ts files are reading 
describe('AppComponent tests', () => {

    let fixture: ComponentFixture<AppComponent>;
    let comp: AppComponent;

    // execute before each test: `it`
    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [
                AppModule
            ],
            providers: [{ provide: APP_BASE_HREF, useValue: '/' }]
        });
        fixture = TestBed.createComponent(AppComponent);
        comp = fixture.componentInstance;
    });

    it('component has exists', () => {
        expect(comp).toBeTruthy();
    });

    it('no title in the DOM until manually call `detectChanges`', () => {
        expect(comp).toBeTruthy();
        let de = fixture.debugElement.query(By.css('a.navbar-brand'));
        let el = de.nativeElement;
        expect(el.textContent).toEqual('');
    });

    it('title test after detect changes', () => {
        let de = fixture.debugElement.query(By.css('a.navbar-brand'));
        let el = de.nativeElement;
        // look in console, to know how detect changes works
        console.log(el);
        fixture.detectChanges();
        console.log(el);
        expect(el.textContent).toEqual(comp.title);
    });

    it('should display a different test title', () => {
        const anotherTitle = 'Example another text as title';
        let de = fixture.debugElement.query(By.css('nav a.navbar-brand'));
        let el = de.nativeElement;
        comp.title = anotherTitle;
        fixture.detectChanges();
        expect(el.textContent).toContain(anotherTitle);
    });
});