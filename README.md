# Spring-Redis

Simple demo project that shows different ways to use Redis as a cache in a Spring Boot application.

Since it is for demo purposes, the primary database is an in-memory H2 database. This does not
necessarily make sense in a real environment to use with Redis for simple caching purposes. It
could make sense if you are using other Redis features, such as messaging.

## Getting Started

To build the application: `gradle build`

To run the application: `gradle bootRun`

> You should have a Redis server up. You can modify the configuration in `application.properties`.

## Redis Setup

I am using Docker to spin up a Redis server for use.

`docker run -p 6379:6379 --name spring-redis -d redis`