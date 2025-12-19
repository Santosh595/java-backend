
# Java Reflection API – Complete Beginner to Spring Core Guide

> This note explains Java Reflection API from scratch and how it is used internally by Spring Core.
> No prior knowledge of Reflection is required.

---

## 1. What is Reflection API?

**Reflection** is a feature in Java that allows a program to:
- Inspect its own structure at runtime
- Access class metadata (fields, methods, constructors)
- Create objects dynamically
- Invoke methods dynamically
- Access private members

👉 Reflection works **at runtime**, not compile time.

---

## 2. Normal Java vs Reflection

### Normal Java (Compile-Time)
```java
Car car = new Car();
car.drive();
````

* Class known at compile time
* Method known at compile time

### Reflection (Runtime)

```java
Class<?> clazz = Class.forName("Car");
Object obj = clazz.getDeclaredConstructor().newInstance();
Method method = clazz.getMethod("drive");
method.invoke(obj);
```

* Class name known at runtime
* Method name known at runtime

---

## 3. Why Reflection Exists?

Frameworks like **Spring**:

* Do not know class names in advance
* Scan packages dynamically
* Create and wire objects automatically

Reflection is the **only way** to achieve this.

---

## 4. Core Reflection Classes

| Class         | Purpose             |
| ------------- | ------------------- |
| `Class`       | Metadata of a class |
| `Constructor` | Constructor details |
| `Method`      | Method details      |
| `Field`       | Field details       |

All are in:

```java
java.lang.reflect.*
```

---

## 5. Getting Class Object (Entry Point)

### 1. Using `.class`

```java
Class<Car> c1 = Car.class;
```

### 2. Using `getClass()`

```java
Car car = new Car();
Class<?> c2 = car.getClass();
```

### 3. Using `Class.forName()` (Used by Spring)

```java
Class<?> c3 = Class.forName("com.example.Car");
```

---

## 6. Inspecting Class Metadata

### Example Class

```java
public class Car {
    private String model;

    public Car() {}

    public void drive() {
        System.out.println("Car is driving");
    }
}
```

### Get Class Name

```java
Class<?> clazz = Car.class;

clazz.getName();        // com.example.Car
clazz.getSimpleName(); // Car
```

---

### Get Constructors

```java
Constructor<?>[] constructors = clazz.getDeclaredConstructors();
```

---

### Get Methods

```java
Method[] methods = clazz.getDeclaredMethods();
```

---

### Get Fields

```java
Field[] fields = clazz.getDeclaredFields();
```

---

## 7. Creating Objects Using Reflection

### Normal Way

```java
Car car = new Car();
```

### Reflection Way

```java
Class<?> clazz = Car.class;
Object obj = clazz.getDeclaredConstructor().newInstance();
Car car = (Car) obj;
```

👉 Spring creates all beans this way.

---

## 8. Invoking Methods Using Reflection

```java
Method method = clazz.getMethod("drive");
method.invoke(obj);
```

* Method name can come from annotations or config
* No direct method call

---

## 9. Accessing Private Fields (Dependency Injection)

```java
Field field = clazz.getDeclaredField("model");
field.setAccessible(true);   // bypass private access
field.set(obj, "BMW");

System.out.println(field.get(obj));
```

👉 Spring uses this for `@Autowired` field injection.

---

## 10. Dependency Injection Without Spring (Using Reflection)

### Classes

```java
class Engine {
    void start() {
        System.out.println("Engine started");
    }
}

class Car {
    private Engine engine;

    void drive() {
        engine.start();
    }
}
```

### Manual DI using Reflection

```java
Car car = Car.class.getDeclaredConstructor().newInstance();
Engine engine = Engine.class.getDeclaredConstructor().newInstance();

Field field = Car.class.getDeclaredField("engine");
field.setAccessible(true);
field.set(car, engine);

car.drive();
```

🔥 This is exactly how Spring performs Dependency Injection.

---

## 11. How Spring Uses Reflection Internally

### 1. Component Scanning

```java
@Component
public class Car {}
```

Spring internally:

```java
Class.forName("com.example.Car");
clazz.isAnnotationPresent(Component.class);
```

---

### 2. Bean Creation

```java
clazz.getDeclaredConstructor().newInstance();
```

---

### 3. `@Autowired` Injection

```java
Field field = clazz.getDeclaredField("engine");
field.setAccessible(true);
field.set(bean, dependency);
```

---

### 4. Lifecycle Callbacks (`@PostConstruct`)

```java
Method method = clazz.getDeclaredMethod("init");
method.invoke(bean);
```

---

### 5. XML Configuration (Old Spring)

```xml
<bean class="com.example.Car"/>
```

Spring:

```java
Class.forName("com.example.Car");
```

---

## 12. Advantages and Disadvantages of Reflection

### Advantages

* Dynamic behavior
* Framework development
* Loose coupling

### Disadvantages

* Slower than normal calls
* Breaks encapsulation
* Harder to debug

👉 Spring minimizes impact by using reflection mainly at startup.

---

## 13. Spring Core Mental Model

> **Spring = Reflection + Design Patterns**

* Reflection → object creation & wiring
* IoC → control inversion
* DI → dependency injection

---

## 14. When Should You Use Reflection?

### Use Reflection When:

* Writing frameworks
* Writing libraries
* Creating generic utilities

### Avoid Reflection When:

* Writing normal business logic
* Performance-critical code

---

## 15. Recommended Learning Path

1. Java Reflection API ✅
2. Java Annotations
3. IoC & DI concepts
4. Spring Bean Lifecycle
5. Spring Core internals

---


