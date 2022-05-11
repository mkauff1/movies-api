# Seeding the Database

Now, it's time to import the movies data we used in our frontend Movies Application.

To do this we have to follow a few fairly straight-forward steps. 

**If you encounter exceptions, be sure to read your stacktrace, print out logging statements, and debug. Often, fixing an error is fairly easy so long as you are observing and working methodically. Don't guess!**

1. Expose an endpoint which can accept the entire List of movie objects. It will accept a `POST` on the route `/api/movies/all`

1. Test to ensure Spring can deserialize the JSON string to a list of Movie objects.

1. Invoke `moviesRepository.saveAll()`, passing in the movies list as the argument to `saveAll()`.

1. Test to ensure you can insert a couple of movies in a list.

1. Go to your Movies Application Glitch server, copy the contents of db.json.

1. Create a JSON file in your new backend project named `movies.json` and paste in the contents of `db.json` from the Glitch server. This is so you don't have to constantly go back to the Glitch server when testing.

1. Open Postman or Swagger and try to send the full request! Open a database console and ensure the movies were posted.


!!! 👀 Did You Know?

    With `spring.jpa.show-sql=true` set inside your `application.properties`, you can open the console output and see the actual SQL statements Hibernate is creating when `saveAll()` or any other repository method is invoked.




