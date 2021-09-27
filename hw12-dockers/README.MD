Админу разрешается все
Пользователю только просмотр и добавление жанров и авторов
не авторизованному только root и логин

известный баг - при создании книги надо выбрать автора, т.е. изменить выбор по умолчанию :(

developing: 
  js:
	npm install ; - once
	npm start   ; - before coding
	; browser will be openned at http://localhost:8081/ 
	
  js-alternative 	
	sh watch.sh (or cmd watch.bat) and open browser at http://localhost:8080/
	 
  java: 
    import project and start as spring applicaiton

building:
  mvn clean package ; to start java -jar target/hw9-spa-2-0.0.1-SNAPSHOT.jar ; be sure port 8080 is open
  docker-compose up ; to start all(postgres + application) in docker

additional:
  docker-compose --file docker-compose-no-build.yml up ; to run it when container already built
  docker image rm hw/liba ; to delete image
  	
