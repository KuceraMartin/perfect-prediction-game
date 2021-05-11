# Perfect Prediction Game

## Report
[Read](https://gitlab.inf.ethz.ch/gfourny/perfect-prediction-game/-/jobs/artifacts/master/file/report/report.pdf?job=build_report) | [Download](https://gitlab.inf.ethz.ch/gfourny/perfect-prediction-game/-/jobs/artifacts/master/raw/report/report.pdf?job=build_report) (latest build in master branch)

## Build
```sh
docker-compose run sbt assembly
```

## Run API
```sh
docker-compose run web
```

## Run terminal interface
```sh
docker-compose run console
```

## Run Spark data analysis
Run Spark Shell:
```sh
docker-compose run analysis
```
Run predefined Spark command:
```sh
docker-compose run analysis <ClassName> [ARGS]
```
(`ClassName` must be defined in the `analysis` package and built with sbt assembly)


## Test
```sh
docker-compose run sbt test
```
