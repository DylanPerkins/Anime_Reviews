# Anime_Reviews

This is my final project at Promineo Tech. It is a back end application utilizing HTTP requests that allows users to review, rate and track anime.

## Table of Contents

- [Description](#description)
- [Installation](#installation)
- [Usage](#usage)
- [API](#api)

## Description

The Anime_Reviews project is a back end application built using Java, MySQL, and Spring Boot. It allows users to browse and search for anime and add them to one of their various lists, read and write reviews, and rate their favorite anime.

## Installation

To install and run the Anime_Reviews project locally, follow these steps:

1. Clone the repository: `git clone https://github.com/DylanPerkins/Anime_Reviews.git`
2. Ensure that you have Java 17, MySQL and some sort of API testing tool such as Postman or ARC installed on your machine.
3. Create a new MySQL database and input the following information into the `application.yaml` file:

```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
```

## Usage

To start the Anime_Reviews application, simply run the `AnimeReviewsApplication.java` file. Once the application is running, you can use an API testing tool to send HTTP requests to the server.

## API

The Anime_Reviews API has the following endpoints:

- **POST**   | `/anime-reviews/anime` - Create a new anime
- **PUT**    | `/anime-reviews/anime/{animeId}` - Update an anime
- **GET**    | `/anime-reviews/anime` - Get all anime and associated tags (if any)
- **GET**    | `/anime-reviews/anime/{animeId}` - Get a specific anime and associated tags (if any)
- **POST**   | `/anime-reviews/tag` - Create a new tag
- **PUT**    | `/anime-reviews/tag/{tagId}` - Update a tag
- **GET**    | `/anime-reviews/tag/{tagId}` - Get a tag's info
- **POST**   | `/anime-reviews/anime/{animeId}/tag/{tagId}` - Add a tag to an anime
- **POST**   | `/anime-reviews/user` - Create a new user
- **PUT**    | `/anime-reviews/user/{userId}` - Update a user's info
- **GET**    | `/anime-reviews/user/{userId}` - Get all of a user's info
- **POST**   | `/anime-reviews/user/{userId}/watched/{animeId}` - Add anime to user's watched list
- **POST**   | `/anime-reviews/user/{userId}/watching/{animeId}` - Add anime to user's watching list
- **POST**   | `/anime-reviews/user/{userId}/want-to-watch/{animeId}` - Add anime to user's want to watch list
- **POST**   | `/anime-reviews/user/{userId}/wont-watch/{animeId}` - Add anime to user's wont watch list
- **DELETE** | `/anime-reviews/user/{userId}/watched/{animeId}` - Remove an anime from user's watched list
- **DELETE** | `/anime-reviews/user/{userId}/watching/{animeId}` - Remove an anime from user's watching list
- **DELETE** | `/anime-reviews/user/{userId}/want-to-watch/{animeId}` - Remove an anime from user's want to watch list
- **DELETE** | `/anime-reviews/user/{userId}/wont-watch/{animeId}` - Remove an anime from user's wont watch list
- **POST**   | `/anime-reviews/user/{userId}/anime/{animeId}/review` - Create a new review
- **PUT**    | `/anime-reviews/user/{userId}/anime/{animeId}/review/{reviewId}` - Update a review
- **DELETE** | `/anime-reviews/user/{userId}/anime/{animeId}/review/{reviewId}` - Delete a review by its ID
