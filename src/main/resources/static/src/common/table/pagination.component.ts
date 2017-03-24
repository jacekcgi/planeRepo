import { Component, Input, HostListener, Output, EventEmitter, OnChanges } from '@angular/core';

@Component({
  selector: 'ap-pagination',
  template: `
    <ul class="pagination pagination-sm">
        <li *ngFor="let page of pages" class="page-item {{page.clazz}}">
            <a href="#" class="page-link" innerHtml="{{page.label}}" (click)="selectPage(page.index); false;"></a>
        </li>
    </ul>
  `
})
export class Pagination implements OnChanges {
    @Input() rows: number;
    @Input() size: number;
    @Input() current: number;
    @Input() neighbors: number = 2;

    @Output() onPage: EventEmitter<number> = new EventEmitter();

    private pages: Array<Page>;

    selectPage(selection: number) {
        this.current = selection;
        this.onPage.emit(this.current);
    }

    ngOnChanges(){
        if (this.neighbors == null || this.neighbors < 2) {
            this.neighbors = 2
            console.warn("[PAGINATION] Neighbors cannot be less than 2!")
        }

        let pagesCount = Math.ceil(this.rows / this.size);

        this.pages = new Array<Page>();
        let prevPage = {label: "&laquo;", index: this.current - 1, clazz: this.current == 0 ? "disabled" : ""};
        let nextPage = {label: "&raquo;", index: this.current + 1, clazz: this.current == (pagesCount - 1) ? "disabled" : ""};
        let firstPage = {label: "1", index: 0, clazz: this.current == 0 ? "active" : ""};
        let lastPage = {label: pagesCount.toString(), index: pagesCount - 1, clazz: this.current == (pagesCount - 1) ? "active" : ""};
        let pageBreak = {label: "...", index: 0, clazz: "disabled"};

        this.pages.push(prevPage);
        this.pages.push(firstPage);

        //add break
        if (this.current - this.neighbors > 1) {
            this.pages.push(pageBreak);
        }

        //middle pages
        for (let i = 1; i < pagesCount - 1 ; i++) {
            let page = {label: (i + 1).toString(), index: i, clazz: this.current == i ? "active" : ""};
            //add only few pages each side on current page
            if (this.current - this.neighbors < i && i < this.current + this.neighbors) {
                this.pages.push(page);
            }
        }

        //add break
        if (this.current + this.neighbors < (pagesCount - 1)) {
            this.pages.push(pageBreak);
        }

        if (pagesCount > 1) {
            this.pages.push(lastPage);
        }
        this.pages.push(nextPage);
    }
}

interface Page {
    label: string,
    index: number,
    clazz: string,
}