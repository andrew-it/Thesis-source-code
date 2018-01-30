# Thesis-source-code

The proposed solution is structured as follows: while parsing AST, a tree consisting of classes representing AST nodes -- semantic language entities, such as functions, variables and many more -- is built.
Every entitie is represented by a separate class, and every class implements the Constructible interface, which contains the construct() method, that returns a text string containing the corresponding code in C. If an class object refers to other objects (i.e representation of function declaration which contains variable declarations), then inside construct() of the parent object construct() for all child objects is called recursively, and this is what happens to all the objects that are Constructible. In other words, the construct() method performs traversal of the tree built while parsing AST, in appropriate order.

Almost all Constructible classes have attributes, which store metadata needed to build a certain construction, and are defined during the object creation, inside a constructor. For example, a function will have such attributes as name, return type, function arguments (signature) and function body. All attributes should also implement Constructible to fit the proposed idea of recursive calls of construct().

To build expressions, certain patterns are used; these are basically the stubs of source code in C, that have fields to be replaces by certain attributes after they are constructed. For instance, the pattern for function declaration looks as follows:

```
    RET_TYPE  FUNC_NAME  ( SIGNATURE )  { BODY }
```
Here we can see four placeholders to be replaced by return type, function name, function argument list and function body respectively.

Interestingly, this approach is suitable for implementation of simplistic examples of code for many different programming languages, e.g. for Python, which is syntactically distant from C:

```
    def  FUNC_NAME  ( SIGNATURE )  ->  RET_TYPE: \n\t BODY
```


This example would only allow to generate simple functions with a single line in function body. Still, the mere possibility to describe different programming language constructs enforces the thought that the concept can be expanded to such a level that in order to enable code generation into one more language you will only need to create a file with patterns to describe it properly.
