/**
 * Created by doctor on 15-2-11.
 */

var i = 2;
console.log(i);


for(var j=0;j<10;j++){
    console.log(j);
}

for(var a= 1,b=1;a<10,b<10;a++,b++){
    console.log(a*b);
}

var o = ["name","doctor"];
for(var item in o){
    console.log(item + "-" +  o[item]);
}

var scop = "global";
function f(){
    console.log(scop);
    var scop = "local";
    console.log(scop);
}
f();

function ff(){
    var scop;
    console.log(scop);
    scop = "local";
    console.log(scop);
}
ff();
