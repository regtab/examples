# RegTab Examples

This project contains examples that demonstrate how [RegTab API](https://github.com/regtab/regtab) can be used.

## Prerequisites

- JDK 21+
- Maven 3.9+

## Usage

Clone the repository of the project into this directory as follows:
```
git clone https://github.com/regtab/examples.git
```

Go to the project directory: `examples`.

The project structure should be look this one:
```
examples
|   .gitignore
|   example1.bat
|   example1.sh
|   example2.bat
|   example2.sh
|   pom.xml
|   README.md
|   
+---data
|       table1.csv
|       table2.csv
|       
\---src
    +---main
    |   +---java
    |   |   \---com
    |   |       \---regtab
    |   |           \---examples
    |   |                   Example1.java
    |   |                   Example2.java
    |   |                   
    |   \---resources
    \---test
        \---java
```

Build the executable JAR with dependencies as follows:
```bash
mvn clean package
```

Run the 1st example as follows:
```bash
java -cp target/regtab-examples.jar com.regtab.examples.Example1 data/table1.csv data/recordset1.csv
```

View the extracted recordset in the file: `data/recordset1.csv`.

Run the 2nd example as follows:

```bash
java -cp target/regtab-examples.jar com.regtab.examples.Example2 data/table2.csv data/recordset2.csv
```

View the extracted recordset in the file: `data/recordset2.csv`.