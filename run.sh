./mvnw clean package -DskipTests
docker build -t venda-discos:latest .
docker run -e DB_USER=servico -e DB_PASSWORD=123456 -e SPOTIFY_CLIENT_ID=b8ca0328ccb84b64b87e898af173aeb9 -e SPOTIFY_CLIENT_SECRET=cd460df6206a49c6aa4395b327bc697e -p 8080:8080 --rm venda-discos:latest
