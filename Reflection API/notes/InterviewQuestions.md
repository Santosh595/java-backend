Perfect 👍
Below is the **interview questions content converted into clean, GitHub-ready Markdown (`.md`) notes**.

You can save this file as:

```
Java-Reflection-Interview-Questions.md
```

and push it directly to your GitHub study repository.

---

````md
# Java Reflection API – Interview Questions for Backend Developers

This document contains commonly asked **Java Reflection API interview questions**
for **Backend / Java / Spring developers**, ranging from **basic to advanced**, including **Spring-specific internals**.

---

## 🔹 BASIC REFLECTION INTERVIEW QUESTIONS

### 1. What is Reflection in Java?

Reflection is an API that allows a Java program to **inspect and manipulate classes, methods, fields, and constructors at runtime**, even if they are private.

It is heavily used in frameworks like **Spring, Hibernate, JUnit, Jackson**.

---

### 2. Why do we need Reflection?

Reflection is needed when:
- Class names are not known at compile time
- Objects must be created dynamically
- Methods must be invoked dynamically

It enables **loose coupling and framework-level abstraction**.

---

### 3. In which package is Reflection API present?

```java
java.lang.reflect
````

---

### 4. What are the main classes in Reflection API?

* `Class`
* `Method`
* `Field`
* `Constructor`

---

### 5. How do you get a `Class` object in Java?

```java
Car.class
obj.getClass()
Class.forName("com.example.Car")
```

> `Class.forName()` is commonly used by frameworks like Spring.

---

## 🔹 INTERMEDIATE REFLECTION QUESTIONS

### 6. Can you create an object using Reflection?

Yes.

```java
Class<?> clazz = Car.class;
Object obj = clazz.getDeclaredConstructor().newInstance();
```

Spring creates beans internally using this approach.

---

### 7. How can you access private fields using Reflection?

```java
Field field = clazz.getDeclaredField("engine");
field.setAccessible(true);
field.set(obj, value);
```

This bypasses Java access control.

---

### 8. What is `setAccessible(true)`?

It disables Java access checks, allowing access to **private fields, methods, and constructors**.

---

### 9. How do you invoke a method using Reflection?

```java
Method method = clazz.getMethod("start");
method.invoke(object);
```

---

### 10. Difference between `getMethod()` and `getDeclaredMethod()`?

| Method                | Description                                 |
| --------------------- | ------------------------------------------- |
| `getMethod()`         | Public methods (including inherited ones)   |
| `getDeclaredMethod()` | All methods of the class (private included) |

---

## 🔹 ADVANCED REFLECTION QUESTIONS

### 11. Is Reflection slow? Why?

Yes, reflection is slower because:

* Access checks are bypassed
* JVM optimizations are limited
* Resolution happens at runtime

> Spring uses reflection mainly during **startup**, not during every request.

---

### 12. Can Reflection break the Singleton pattern?

Yes. Reflection can access private constructors.

```java
constructor.setAccessible(true);
```

To prevent this, throw an exception inside the constructor.

---

### 13. Can Reflection modify `final` fields?

Yes (not recommended). This is JVM-dependent and unsafe.

---

### 14. How does Reflection relate to ClassLoader?

* **ClassLoader** loads the class into memory
* **Reflection** inspects and manipulates it afterward

Spring internally uses **both ClassLoader and Reflection**.

---

### 15. Difference between Reflection and normal object creation?

| Normal              | Reflection      |
| ------------------- | --------------- |
| Compile-time safety | Runtime only    |
| Faster              | Slower          |
| Less flexible       | Highly flexible |

---

## 🔹 SPRING-SPECIFIC REFLECTION QUESTIONS (VERY IMPORTANT)

### 16. How does Spring use Reflection?

Spring uses Reflection to:

* Scan classes (`@Component`)
* Create beans
* Inject dependencies (`@Autowired`)
* Invoke lifecycle methods (`@PostConstruct`)

---

### 17. How does `@Autowired` work internally?

1. Spring scans fields with `@Autowired`
2. Finds matching beans
3. Uses reflection to inject the dependency (even into private fields)

---

### 18. How does Spring create beans if constructors are private?

Spring uses:

```java
constructor.setAccessible(true);
constructor.newInstance();
```

---

### 19. How does Spring call `@PostConstruct` methods?

Spring scans methods annotated with `@PostConstruct` and invokes them using reflection **after dependency injection**.

---

### 20. Does Spring use Reflection at runtime or startup?

Mostly at **startup**:

* Bean scanning
* Dependency wiring

At runtime, Spring prefers **proxies and bytecode enhancement**.

---

## 🔹 SCENARIO-BASED QUESTIONS

### 21. When should you avoid Reflection?

Avoid Reflection:

* In business logic
* In performance-critical code
* When compile-time safety is required

---

### 22. Name some frameworks that use Reflection.

* Spring
* Hibernate
* JUnit
* Jackson
* Mockito

---

### 23. Can Reflection be used without annotations?

Yes. Reflection existed before annotations.
Annotations are just **metadata read using Reflection**.

---

### 24. What happens if a method name is wrong in Reflection?

A `NoSuchMethodException` is thrown at runtime.

---

### 25. How does Reflection help in loose coupling?

Classes are discovered and wired **dynamically**, without hard-coded dependencies.

---

## 🔹 QUICK FIRE (1-LINERS)

* Reflection works at **runtime**
* `Class.forName()` loads classes dynamically
* Reflection breaks **encapsulation**
* Reflection is essential for **Spring Core**
* Slower than direct method calls

---

## 🔥 INTERVIEW TIP

If asked:

> **“Explain Reflection in Spring”**

Answer:

> “Spring uses Java Reflection to scan classes, create bean instances, inject dependencies, and invoke lifecycle methods dynamically. This enables loose coupling and configuration-based programming.”

---

