import { Component, Input, ComponentFactoryResolver, ReflectiveInjector, ViewContainerRef, ViewChild } from '@angular/core';
import { Column, DefaultCellComponent } from 'common/table'

@Component({
    selector: 'ap-cell',
    template: ``,
})
export class CellComponent {
    @Input() item: any;
    @Input() column: Column;

    constructor(private vcRef: ViewContainerRef, private resolver: ComponentFactoryResolver) { }

    ngOnInit() {
        //ValueProvider { provide: any; useValue: any;}
        // let providers = [{ provide: "item", useValue: this.item }, { provide: "column", useValue: this.column }];
        // let inputs = ReflectiveInjector.resolve(providers);
         let injector = ReflectiveInjector.fromResolvedProviders([], this.vcRef.parentInjector);
         let factory = this.resolver.resolveComponentFactory(this.column.cell ? this.column.cell : DefaultCellComponent);
         let component = factory.create(injector);
         component.instance.column = this.column;
         component.instance.item = this.item;
         this.vcRef.insert(component.hostView);
    }
}
