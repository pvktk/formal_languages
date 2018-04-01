## Системные требования
Нужно чтобы было JRE и JDK (для сборки), а так же Bash. Тестировалось на Ubuntu.

Если их нет, то для их установки можно выполнить скрипт
```
chmod +x install_java.sh
```

## Запуск
В репозитории всё находится в собранном виде, поэтому можно сразу запустить:

```
chmod +x runLexer.sh
./runLexer.sh filename
``` 
(filename -- имя файла с текстом)

или без аргумента

```
./runLexer.sh
```
тогда запустится на файле-примере.

Код находится в файле ```lexer_runner/runLexer.java``` .

## Сборка

```
chmod +x build.sh
./build.sh
```

## Запуск тестов

```
chmod +x testLexer.sh
./testLexer.sh
```

Тесты находятся в файле ```lexer_test/TestLexer.java```.
