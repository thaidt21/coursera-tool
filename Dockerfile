FROM ubuntu:22.04

# Cài Java + MySQL
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven mysql-server && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copy project vào container
COPY . .

# Build jar
RUN mvn clean package -DskipTests

# Khởi tạo MySQL database + user
RUN service mysql start && \
    mysqld_safe & sleep 5 && \
    mysql -u root -e "CREATE DATABASE mydb; CREATE USER 'user'@'%' IDENTIFIED BY 'pass'; GRANT ALL PRIVILEGES ON mydb.* TO 'user'@'%'; FLUSH PRIVILEGES;"

EXPOSE 8080

CMD service mysql start && java -jar target/*.jar --server.port=8080
