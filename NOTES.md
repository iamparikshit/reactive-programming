# Reactive Programming

Reactive programming is programming with asynchronous data streams.
The "listening" to the stream is called subscribing
The functions that we are defining are observers
The stream is the subject (or "observable") being observed.


### Flux and Mono :
### Flux : 
can emit 0 to many values

### Mono :  
can Emily 0 or 1 value

Flux and Mono both are lazy, here  lazy means they will only get used when we will consume it.

### Operators :

### 1] Map : 
transform element from one form to another.

### 2] Filter : 
takes the predicate and returns

### 3] flatMap : 
transform and flatten. It is asynchronous. It emits of same type. Like if we use flatMap on Mono it will return Mono.If we use flatMap on Flux it will return Flux
FlatMap is not sequential.

### 4] flatMapMany : 
is also asynchronous. It will always return Flux.
However,
Mono#flatMap -> 1 to 1 and will give Mono
Mono#FlatMapManty -> 1 to N and will give Flux

### 5] Transform :
When you repeatedly want to transform your data into desire type then we can write a method which we can pass as parameter in transform method.

### 6]  defaultIfEmpty :
If we are not able to emmit any type of data in mono or flux, then at such time we can send a default type of data that will get return. It provides a single value

### 7] SwitchIfEmpty :
It is similar to defaultIfEmpty, however it also take the observables..
defaultIfEmpty(“defaultValue”) —> send default value
switchIfEmpty{ mono.just(“ss”)} ——> switch to default data set

### 8] concat :
This method is only available for flux and will return flux only. It is static method of Flux. This is not for mono.

### 9] concatWith : 
similar as concat. But not a static method, it require
Object to call this method.
Mono can also use this operator. But will return flux
Mono1.concatWith(Mono2) —> will return flux
Both concat and concatWith is a sequential subscribing.


### 10] merge :
It is similar to concat. However merge is async. And is static function and will return flux. This is not for mono.

### 11] mergeWIth :
It is same as concatWith, however it async.

### 12] mergeSequential :
This operator will merge in sequence as it was subscribe. It is very similar to concat. Will also return Flux. This is not for mono.
