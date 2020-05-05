## Radoslav Mihov

### Assumptions made

- The code does not test whether strings separated by whitespaces are valid words, however,
  characters apart from apostrophe (') and hyphen (-) are replaced with whitespaces. This 
  decision was made so that missing spaces after periods do not produce false results.
 
- If more than one word has the smallest/largest length all words with that length are returned.
 
- Possessions, e.g. `students'` and contractions, e.g. `weren't` are counted as one word
  but the apostrophe is not counted as a letter
  
- Hyphenated words are counted as one. `seven-year-old` is counted as one word with 14 letters.
  Includes hyphens.
  
- Hyphenated words starting with numerical characters are ignored. `7-year-old` is completely ignored.


### Building the project and running tests

The project uses Maven as dependency management.

To run the tests:

```shell
mvn clean test
```

To build the project from command line (also runs tests):

```shell
mvn clean install
```
