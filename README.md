# SWE 307 Big Data Project 1

Java Spring Boot application that reads one value per second from MongoDB, sends it to an R function through GraalVM Polyglot, and displays the generated SVG plot in the browser.

## Requirements

- Ubuntu
- GraalVM JDK 17
- Maven
- MongoDB
- GraalVM R component

Install the R component if it is not already installed:

```bash
gu install r
```

## MongoDB Import

The application is configured to use:

```text
Database: pro1
Collection: pro1_data
```

Import the dataset with:

```bash
mongoimport --db pro1 --collection pro1_data --file swe307_db.project_data.json --jsonArray
```

## Run

```bash
mvn spring-boot:run
```

Open the plot page:

```text
http://localhost:8080/plot
```

The page refreshes every second and shows the latest R-generated SVG plot.
