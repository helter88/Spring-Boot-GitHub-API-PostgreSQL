# Spring-Boot-GitHub-API-PostgreSQL

## Description

Build API with CRUD operations which manage data about GitHub repositories inside database.
The Database is run as a docker container.

Requirements:

#### API Endpoint: GET /api/username/{userName}
This endpoint allows you to retrieve information about a GitHub user's repositories. Replace {userName} with the GitHub username you're interested in. T
he application will connect to the external GitHub API and fetch all repositories owned by the specified user that are not forks.

If the downloaded data does not exist in the database, the application will add it.

Please make sure to replace {userName} with the actual GitHub username you want to query.

Expected answer:
```json
[
    {
        "id": 1,
        "owner": "userName",
        "name": "repositoryName"
    },
]
```
#### API Endpoint: GET /api
This endpoint allows you to retrieve all data available in database.

Expected answer:
```json
[
    {
        "id": 1,
        "owner": "userName",
        "name": "repositoryName"
    },
]
```

#### API Endpoint: GET /api/{id}
This endpoint allows you to retrieve data with specific id.

Expected answer:
```json
{
  "id": 1,
  "owner": "userName",
  "name": "repositoryName"
}
```
#### API Endpoint: POST /api
This endpoint allows you to store data into database.
You should add body with data:
```json

{
  "owner": "userName",
  "name": "repositoryName"
}
```
Expected answer:
204 No Content

#### API Endpoint: PUT /api/{id}
This endpoint allows you to update data with specific id.

You should add body with data:
```json
{
  "owner": "userName",
  "name": "repositoryName"
}
```
Expected answer:
204 No Content

#### API Endpoint: DELETE /api/{id}
This endpoint allows you to delete data with specific id.

Expected answer:
```json
{
  "id": 1,
  "owner": "userName",
  "name": "repositoryName"
}
```
# How to run project

1. Download repo from Github to one of your chosen folder.
2. Run Terminal and select your folder with downloaded project.
3. Create docker container with PostgreSQL. To do so you should write `docker-compose up -d`
4. Check if container is running by writing in terminal: `docker-compose ps -a`
5. You can run Spring Boot application. To do so you should write: `mvn spring-boot:run`
6. Now you can start making requests to API

## Built using

- Spring Boot
- Spring Data
- Open Feign
- Tailwind CSS
- [The GitHub API](https://docs.github.com/en "click to visit API website")
