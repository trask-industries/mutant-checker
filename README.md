# mutant-checker
 Mutant checker microservice

[![Build Status](https://travis-ci.org/trask-industries/mutant-checker.svg?branch=master)](https://travis-ci.org/trask-industries/mutant-checker)

# Como ejecutar el progama
- clonar el repo GIT (git clone)
- ejecutar el comando: docker-compose up -d para ejecutar mysql + db schema y rabbitMQ local
- ejecutar el comando: mvn spring-boot:run

# pruebas locales
- curl http:/localhost:8080/stats/

# pruebas API en Cloud
- curl https://mutant-checker.cfapps.io/stats/
