# Stage 1: Build
FROM maven:3.8.5-openjdk-17 AS build

# Copy source code vào container
COPY . /app

# Đặt thư mục làm việc
WORKDIR /app

# Build ứng dụng với Maven
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM openjdk:17.0.1-jdk-slim

# Copy file JAR từ stage build
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar demo.jar

# Expose port để ứng dụng có thể lắng nghe
EXPOSE 8080

# Lệnh chạy ứng dụng
ENTRYPOINT ["java", "-jar", "demo.jar"]
