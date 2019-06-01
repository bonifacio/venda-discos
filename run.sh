./mvnw clean package
docker build -t venda-discos:latest .
docker run -e DB_USER=servico -e DB_PASSWORD=123456 -p 8080:8080 venda-discos:latest