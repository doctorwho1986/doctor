var hello = function() {
	print("hello Nashorn !");
};

hello();

var data = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

var filtered = data.filter(function(i) {
  return i % 2 == 0;
});
print(filtered);

filtered = data.filter(function(i) {
	return i % 3 == 0;
});

print(filtered);

filtered = data.reduce(function(a,b) {
	return a+b;
});

print(filtered);

var object = {
		name:"doctor",
		age:66666
};

print(object.name);

var files = 'ls -alF'.split("\n");
for ( var file in files) {
	print(file);
};

var add = function(a,b) {
	return a+b;
}




