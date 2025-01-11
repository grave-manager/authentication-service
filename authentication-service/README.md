## How to run service locally

1. Navigate to the backend service directory:
```shell
cd authentication-service
```
2. Build the project:
```shell
./gradlew clean build
```
Mention above command doesn't include integration tests.
If you want run build and integration tests in one command use:
```shell
./gradlew clean build testIntegration
```
3. To run application from terminal:
```shell
java -jar -Dspring.profiles.active=local build/libs/authentication-*.jar
