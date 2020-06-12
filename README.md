# java-problem-solutions
Scratchpad containing my rough notes and solution to coding exercises found on Leetcode/Hackerrank/elsewhere.

- How to execute the code from command line?
   
   ```
   # Change to the project root dir
   cd java-problem-solutions/src
   javac Solution.java
   java Solution
   ```
   
- If a dependent java class has been updated after it was already compiled, then use filename of that class in javac command as well, to compile it explicitly.
  
  Eg: Some code in *com/nitesh/string/longestSubstring.java* changes **after** it was already compiled once, then use the following command to re-compile it. 
  ```
  javac Solution.java com/nitesh/string/longestSubstring.java
  ```  
