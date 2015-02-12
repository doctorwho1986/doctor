/**
 * ｊｓ权威指南第６章 对象
 */

var empty = {};
print(empty);
for ( var v in empty) {
	print("--:" + v);
}

var point = {
	x : 0,
	y : 0
};
print(point);
for ( var v in point) {
	print(v + ":" + point[v]);
}

var point2 = {
	x : point.x + 22,
	y : point.y + 22
};
for ( var v in point2) {
	print(v + ":" + point2[v]);
}

var book = {
	"main tile" : "book",
	"subTile" : "book-book",
	author : {
		firstName : "doctor",
		lastName : "who"
	}
}




var printObject = function(o) {
	for(var p in o){
		var ob = o[p];
		print(p + " is object " +  (typeof(p) == "object"));
		if(typeof(ob)!== "object"){
			print(p + ":" + ob);
		}else{
			print(p+":");
			printObject(ob);
		}
	}
	
}

printObject(book);

var str = "dkjfkd";
for(var p in str){
	print(p + ":" + str[p]);
}

