#305687253 308425990
#Shurgil barisya
bin:
	mkdir bin
compile: bin
	javac -d bin -cp biuoop-1.4.jar src/*.java
jar: compile
	jar cfm space-invaders.jar Manifest.txt -C bin . -C resources .
run: jar
	java -jar space-invaders.jar
check:
	java -jar checkstyle-5.7-all.jar -c biuoop.xml src/*java
