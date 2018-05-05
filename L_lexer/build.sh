#!/bin/bash

java -jar antlr-4.7.1-complete.jar L_grammar.g4 L_parser.g4 -o antlr_generated_files

javac -encoding UTF-8 -cp antlr-4.7.1-complete.jar: lexer_runner/runLexer.java  antlr_generated_files/L_grammar.java -d out

javac -encoding UTF-8 -cp antlr-4.7.1-complete.jar: parser_runner/*.java  antlr_generated_files/*.java -d out

javac -encoding UTF-8 -cp antlr-4.7.1-complete.jar: lexer_test/TestLexer.java  antlr_generated_files/L_grammar.java -d out

javac -encoding UTF-8 -cp antlr-4.7.1-complete.jar: parser_test/TestParser.java  parser_runner/ASTBuildListener.java antlr_generated_files/*.java -d out
