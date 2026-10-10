# SWE 307 Big Data Project 1

Java Spring Boot application that reads one value per second from MongoDB, sends it to an R function through GraalVM Polyglot, and displays the generated SVG plot in the browser.

## Requirements

- Ubuntu
- GraalVM JDK 17
- Maven
- MongoDB
- GraalVM R component

##How It Works
1.Numerical data is imported into MongoDB.
2.The Java application retrieves the data from the database.
3.Java passes the retrieved value to an R plotting function through GraalVM.
4.R generates the visualization.
5.The generated graph is displayed in the web browser.
6.The visualization updates every second as new data is processed.

Install the R component :
```bash
gu install r
```


## Run
```bash
mvn spring-boot:run
```
Open the plot page:

```text
http://localhost:8080/plot
```

