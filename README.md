# SWE 307 Big Data Project 1

Java Spring Boot application that reads one value per second from MongoDB, sends it to an R function through GraalVM Polyglot, and displays the generated SVG plot in the browser.

## Requirements

- Ubuntu
- GraalVM JDK 17
- Maven
- MongoDB
- GraalVM R component


Install the R component :
```bash
gu install r
```


## How It Works

-Numerical data is imported into MongoDB.
-The Java application retrieves the data from the database.
-Java passes the retrieved value to an R plotting function through GraalVM.
-R generates the visualization.
-The generated graph is displayed in the web browser.
-The visualization updates every second as new data is processed.



## Run
```bash
mvn spring-boot:run
```
Open the plot page:

```text
http://localhost:8080/plot
```

