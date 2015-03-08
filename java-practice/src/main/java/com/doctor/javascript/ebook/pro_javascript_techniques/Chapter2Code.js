/**
 * 
 */
//引用特性
var obj = new Object();
obj.oneProp = false;

var obj2 = obj;
obj2.oneProp = true;

print(obj.oneProp === obj2.oneProp) //true

var items = ["one","tow"];
var itemsRef = items;

items.push("three");
print(items.length ==itemsRef.length);
print(items);
print(itemsRef);


//对象的创建
print("对象的创建");

function Person(name) {
 	this.name = name;
}

var  p = new Person("doctor who");
print(p.name)
print(p.constructor == Person)
