# Controller Integration & Dependency Injection

Dependency injection means that we will ***inject*** a class' ***dependencies*** instead of instantiating them at the point of use.

Behind the scenes, Spring (and many other frameworks) create what is called a **dependency injection container** to store instances of our objects which can be called upon whenever we need without having to use the `new` keyword.

Using DI (dependency injection) can be done as simply as follows:

```java
public class MoviesController {
    // ...
    private final MovieRepository moviesRepository;
    
    public MovieController(MovieRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }
    // ...
}
```

We can use DI in most of the classes in our Spring
application. We can even inject services into other services! 

This is how you can use it in order to get the list of all Ads.

```java
import com.codeup.restblog.data.PostRepository;

public class MoviesController {

    // These two next steps are often called dependency injection, 
    // where we create a Repository instance and 
    // initialize it in the controller class constructor.
    private final MovieRepository moviesRepository;

    public MovieController(MovieRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    @GetMapping
    public List<Movie> getMovies() {
        
        // Because of DI, 
        // we don't have to do this:
       
        // MovieRepository repo = new MovieRepositoryImpl()
        
        // Instead, we get this lovely snippet 
        // and can use MovieRepository over and 
        // again in this class.
        return moviesRepository.findAll();
    }

    // ...
}
```
Now THAT was easy, huh? 

---
## Complete Initial Integration

1. Finish integrating the `MovieRepository` into the `MoviesController` by calling it within the create, update, and delete methods.

1. If you need more acute querying for your endpoints, see [Data Persistence, Pt II](14-data-persistence-iii.md).

---
## The Moment of Truth.

Now, it's time to spin up your application! 

1. Start it, then check your database to see if the `movies` table was created!

2. Manually insert a few movie records on your `POST` route.

3. Use Swagger or Postman to fetch all movies and fetch one movie.

***If you encounter exceptions, be sure to observe the stacktrace, logging statements, and debug the application with breakpoints***
        
Congratulations! You have connected your `Movie` Java classes to your database using JPA!

---
### Further Reading
- [What is Dependency Injection?](http://stackoverflow.com/questions/130794/what-is-dependency-injection)
- [Dependency Injection](https://en.wikipedia.org/wiki/Dependency_injection)
- [Spring Beans and dependency injection](https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-spring-beans-and-dependency-injection.html)

## Next Up: [Seeding the Database](8-seeding-the-database.md)
