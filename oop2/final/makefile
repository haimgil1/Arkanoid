# 203676945 305720054
# gilhaim dunetzn
compile: bin
	find src -name "*.java" > sources.txt
	javac -d bin -cp biuoop-1.4.jar @sources.txt
run: jar
	java -jar ass6game.jar
jar: 
	jar cfm ass6game.jar Manifest.mf -C bin . -C resources .
bin:
	mkdir bin
