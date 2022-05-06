# Rest Controllers

## Our first step in developing a RESTful API is to build objects which can handle requests and send meaningful responses.

### To accomplish this, we are going to start with a *different* type of Spring controller

---

### 🚨 `@RestController`

When we place this annotation above a class declaration, it registers the class with Spring's Dependency Injector (*more on that later*) and is
handled in particular ways.

Among those ways is the elimination of a need to annotate every controller method with `@ResponseBody`. 

!!! Less boilerplate == more fun 😄

Most importantly, `@RestController` allows us to signify that a controller exists for the **purpose** of sending/receiving data.

---
#### 🚨 1. Make sure your `pom.xml` has the following dependency (be sure to reload Maven if you add it):

```XML
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
---
#### 🚨 2. Now, let's go create a `Movie` POJO.
- On the same level as the `web` package, create a new package named `data`
- Inside `data`, create a class named `Movie`
- Give `Movie` the below fields, along with an empty constructor, full constructor, and all getters/setters.


```JAVA
private int id;
private String title;
private String year;
private String director;
private String actors;
private String imdbId;
private String movieser;
private String genre;
private String plot;
```
---
#### 🚨 3. In the `web` package, create a class called `MoviesController`.
- Annotate the MoviesController class declaration with `@RestController`

---

## `@RequestMapping`

With the `@RequestMapping` annotation, we are informing Spring of how to direct requests to `MoviesController`, as well
as what type of data is accepted.

`value = "/api/movies"` informs Spring of where to initially route requests. 

Imagine the request is like a mail delivery. `/api/movies` says to send mail to a certain neighborhood. Later we will route requests to specific methods in a class - like from the neighborhood to a specific home!

It will also accept `application/json` data type in
requests - otherwise Spring may not intuit the type (is it text or xml or what??)

```JAVA
@CrossOrigin //this is to help with local dev testing
@RestController
@RequestMapping(value = "/api/movies", headers = "Accept=application/json")
public class MoviesController {
    // we're going to write code stuff here
}
```

From `api/movies`, we can further define what requests go to which methods.

i.e., `GET` requests on `api/movies` go to the method annotated with `@GetMapping`.

and `DELETE` requests on `api/movies/{id}` go to `@DeleteMapping("{id}")`.

!!! 🧐 Serialization/Deserialization in Spring 
    Using *Spring*, we do not need to convert objects to and from JSON with another dependency. 
    
    ***Spring will handle serializing/deserializing for us!***
---

## CRUD Mapping By Method

With Spring, we can be specific about which controller methods listen on what route by use of annotations.

!!! `@GetMapping`

    This handy annotation is what is called a *composed annotation*. 
    
    Meaning, its behaviors are composed of the actions of other annotations. 

    `@GetMapping` acts as a shortcut for 
    
    `@RequestMapping(method = RequestMethod.GET)`.


To use it, simply place `@GetMapping` over your desired REST controller *method*:

```JAVA
@GetMapping
private List<movies> getAll() {
        ...
}
```

From this point, any valid `GET` request sent to `/api/movies` will be routed to `getMovies()`.

---

## 🚨 `getAll()` 🚨
In `MoviesController`, create a `public` method called `getAll()`

- This method will return an `ArrayList` of `movies` objects

- Annotate this method with `@GetMapping`

- In `getAll()`, let's make a local variable list of `movies` objects and add 2-3 movies to that list with believable values.

- Return that list to fulfil the return expectation of the method.

---

## 🧪 Testing 🧪

### Using Postman

Many developers love the ease and efficiency of using Postman for testing API endpoints!

Simply go [here](https://www.postman.com/downloads/) to download!

The Postman team have dozens of great tutorials on how to use this testing tool found [here](https://blog.postman.com/tag/tutorials/).

### Using **Swagger UI**

Now that we are using Spring, could also add this dependency to our `pom.xml`:

```XML
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
    <version>1.5.9</version>
</dependency>
```

With this dependency, we will have a ***very*** handy testing and documentation tool.

Start your application and navigate to `http://localhost:8080/swagger-ui.html`

What you will see is live documentation and endpoint testing tools of currently registered controllers.

You can test any endpoint and even get sample requests/responses to help you along!

For more information on how to effectively use Swagger UI, [start here](https://swagger.io/docs/specification/about/)

---

## `@PathVariable`
We can also annotate additional path extensions for controller methods.

For example, adding `@GetMapping("id")` on
`moviesController.getMovies(...)` would allow a client to make a
`GET` request to `/api/movies/12`, with `12` being the ID of the movie to retrieve.

Then, it is up to us to add a `@PathVariable` in front of a matching parameter of the associated controller method. 

```JAVA
@GetMapping("id")
public movies getById(@PathVariable Long id){
        ...
}
```

!!! 🧐
    It's suggested to name the *method* parameter the same as your incoming path variable in order to save confusion.



## 🚨 `getById()` 🚨

Using examples from above, create another `public` method in `moviesController` named `getById()`

- This method will return a single `movies` object

- It will accept `GET` requests on `api/movies/{id}`

- `getById()` has one parameter mapped by `@PathVariable` to the route's `{id}`.
    - The parameter is of type `Long` and is named `id`.

- Create and return a new `movies` object with all fields populated.

- Test your endpoint in Swagger!

---

!!! 🧐 Afterthoughts 

    You can use `Lombok` to create getters, setters, and constructors for you! 😮


## Next Up: [Data Access](6-data-access-layer.md)





