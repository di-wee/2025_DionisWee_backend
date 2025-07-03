# CoinChange Service - Backend

This is the backend REST API for the Coin Change web application, developed using **Java Dropwizard** and packaged into a runnable JAR using Maven. The backend service provides an endpoint to calculate the minimal combination of coin denominations to reach a given target amount.

---

## Tech Stack

- Java 21  
- Dropwizard 4.0.14  
- Maven  
- Docker  

---

## How to Build the JAR

1. Make sure you have Java and Maven installed.
2. Open terminal in the root directory of the project (where `pom.xml` is located).
3. Run the following command to build the project:

    ```bash
    mvn clean package
    ```

4. After building, the compiled JAR file will be located at:

    ```
    target/coin_change-1.0-SNAPSHOT.jar
    ```

---

## How to Build and Run the Docker Image

### Step 1: Build the Docker image

```bash
docker build -t coin-change-service .
```

### Step 2: Run the Docker container

```bash
docker run -p 8080:8080 coin-change-service
```

---

## How to Run Without Docker (Optional)

If you don't want to use Docker, you can run the built JAR directly with:

```bash
java -jar target/coin_change-1.0-SNAPSHOT.jar server config.yml
```

Make sure your `config.yml` file is in the project root.

---

## Public IP for Testing Backend

Example (replace with your own public IP):

```
http://54.169.133.117:8080/api/v1/coinChange
```

---

## Sample POST Request

**Endpoint:** `/api/v1/coinChange`  
**Method:** `POST`  
**Headers:** `Content-Type: application/json`

**Request Body Example:**

```json
{
  "targetAmount": 8.6,
  "denominations": [1.0, 0.5, 0.2]
}
```

**Example Response:**

```json
[
    0.2,
    0.2,
    0.2,
    1.0,
    1.0,
    1.0,
    1.0,
    1.0,
    1.0,
    1.0,
    1.0
]
```

---

## Unit Testing

To run unit tests:

```bash
mvn test
```

---


