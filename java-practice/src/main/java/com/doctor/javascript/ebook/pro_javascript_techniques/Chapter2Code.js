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