run:
	./gradlew run

run-dist: build
	./build/install/app/bin/app

build: clear
	./gradlew installDist

clear:
	./gradlew clean

test:
	./gradlew test

check:
	./gradlew check

tasks:
	./gradlew tasks

setup:
	./gradlew wrapper --gradle-version 7.4.2