docker ps -q --filter ancestor=postgres > postgres-container-id.txt
set /p POSTGRES_CONTAINER_ID=<postgres-container-id.txt
del postgres-container-id.txt
docker stop %POSTGRES_CONTAINER_ID%
docker rm postgres
set POSTGRES_CONTAINER_ID=
cmd /k