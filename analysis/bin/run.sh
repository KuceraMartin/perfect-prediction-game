#!/bin/bash

/opt/spark/bin/spark-submit --class "analysis.$1" --master local[4] analysis/target/scala-2.12/analysis-assembly-0.1.jar "${@:2}"
