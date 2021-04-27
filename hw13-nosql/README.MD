MongoDB "без фанатизма"

* MongoDB вместо базы данных
* MongoDb listener на удаление автора
* Mongock для инициализации данных
* MongoDb тесты
* embedded mongodb для тестов

Админ: admin/admin
все работает как раньше

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
  mvn clean packag
  
prequelse
	you need mongodb at the machine at the default port or 
	docker run --name mongodb -p27018:27017 mongo
  
to start:  
  java -jar target/hw13-nosql-0.0.1-SNAPSHOT.jar ; be sure port 8080 is open
  	
