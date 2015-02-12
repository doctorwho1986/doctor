var name = "doctor";
var greeting = "hello " + name;
print(greeting);

var undefine;
print(undefine);

function fact(param) {
	if (param == 1) {
		return 1;
	}
	
	return param * fact(param - 1)
}


var array = [11,22,33,55,66];
//数组，map都是对象．枚举可枚举的属性．数组是一种特殊的map,map又是特殊的对象．map中的key就是属性，数组的下标也就是属性了
for (var index in array) {
	print(index+ ":" + array[index]);
}


var map = {"a":"ddd","name":"doctor","age":"8888"};
for (var index in map) {
	print(index + ":" + map[index]);
}


print(fact(9));
