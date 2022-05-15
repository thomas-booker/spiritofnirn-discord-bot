docker pull postgres:latest
docker volume create postgres-volume
docker run -d --name=postgres -p 5432:5432 -v postgres-volume:/var/lib/postgresql/data -e POSTGRES_PASSWORD=password postgres
cmd /k