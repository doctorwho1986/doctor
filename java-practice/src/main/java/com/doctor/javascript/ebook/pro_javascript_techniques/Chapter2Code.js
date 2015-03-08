/**
 * 
 */
// 引用特性
var obj = new Object();
obj.oneProp = false;

var obj2 = obj;
obj2.oneProp = true;

print(obj.oneProp === obj2.oneProp) // true

var items = [ "one", "tow" ];
var itemsRef = items;

items.push("three");
print(items.length == itemsRef.length);
print(items);
print(itemsRef);

// 对象的创建
print("对象的创建");

function Person(name) {
	this.name = name;
}

var p = new Person("doctor who");
print(p.name);
print(p.constructor == Person);

Person("doctor");// 无上下文对象，this那就默认全局那个对象了．
print(name);

//对象的方法通过prototype添加
function User(name, age) {
	this.name = name;
	this.age = age;
}

User.prototype.getName = function(){
	return this.name;
}

User.prototype.getAge = function(){
	return this.age;
}

var u = new User("doctor who",2000);
print(u.getName());print(u.getAge());
