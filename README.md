# Local Register Allocator

The contents of the alloc package make up the bottom-up Local Register Allocator.
It contains three files. They are as follows:
1. Alloc.java - contains the main function and the bottom-up register allocator
2. Instruction.java - simple POJO to represent one instruction in the ILOC file
3. Register.java - simple POJO to represent a virtual register

The package expects three command line arguments, namely, the number of physical registers, input ILOC instruction file and output ILOC instruction file. Output file with the argument name is created if it does not exist. 

Commands to compile and run the package:
``` 
javac alloc/Alloc.java 
java alloc/Alloc 3 input.i output.i 
```