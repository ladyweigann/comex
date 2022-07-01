package:
	@ mvn clean package

docker-image-build: package
	@ docker build -t comex-api .

run: docker-image-build
	@ docker-compose up -d

stop:
	@ docker-compose down -v

deploy: docker-image-build
	@ docker login --username=  --password=$$(heroku auth:token) registry.heroku.com
	@ docker build -t comex-api
	@ docker image push registry.heroku.com/comex-api/web:1
	@ make deploy_on_heroku IMAGE_ID=$$(docker image inspect registry.heroku.com/comex-api/web:1 -f {{.Id}})

deploy_on_heroku:
	@ curl -X PATCH \
            -H "Authorization: Bearer $$(heroku auth:token)" \
            -H "Content-Type: application/json" \
            -H "Accept:application/vnd.heroku+json; version=3.docker-releases" \
            -d '{ "updates": [{"type": "web",  "docker_image": "$(IMAGE_ID)"}] }' \
            https://api.heroku.com/apps/comex-api/formation