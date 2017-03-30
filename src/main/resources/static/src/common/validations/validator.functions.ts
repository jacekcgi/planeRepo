import { ValidatorFn, FormGroup } from "@angular/forms";

export class AppValidators {

    static password(key1: string, key2: string) : ValidatorFn {
        return (group: FormGroup): {[key: string]: any} => {
            let field1 = group.get(key1);
            let field2 = group.get(key2);
            if (field1.value && field2.value && field1.value !== field2.value) {
                return {password: true}
            }
        }
    }
}