#!/bin/bash

java -ea -cp out/:antlr-4.7.1-complete.jar: TestParser $1
