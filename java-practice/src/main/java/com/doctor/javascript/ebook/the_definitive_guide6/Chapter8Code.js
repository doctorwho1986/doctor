/**
 * Created by doctor on 15-2-14.
 */

var calculator = {
    oper1:1,
    oper2:5,
    add:function() {
        this.results = this.oper1+ this.oper2;
    }
}
calculator.add();
print(calculator.results);