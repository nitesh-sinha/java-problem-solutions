//        Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.
//
//        In a UNIX-style file system, a period . refers to the current directory. Furthermore, a double period .. moves the directory up a level.
//
//        Note that the returned canonical path must always begin with a slash /, and there must be only a single slash / between two directory names. The last directory name (if it exists) must not end with a trailing /. Also, the canonical path must be the shortest string representing the absolute path.
//
//
//
//        Example 1:
//
//        Input: "/home/"
//        Output: "/home"
//        Explanation: Note that there is no trailing slash after the last directory name.
//        Example 2:
//
//        Input: "/../"
//        Output: "/"
//        Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
//        Example 3:
//
//        Input: "/home//foo/"
//        Output: "/home/foo"
//        Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
//        Example 4:
//
//        Input: "/a/./b/../../c/"
//        Output: "/c"
//        Example 5:
//
//        Input: "/a/../../b/../c//.//"
//        Output: "/c"
//        Example 6:
//
//        Input: "/a//b////c/d//././/.."
//        Output: "/a/b/c"

// Some more Test cases: "/a/..a/" ==> "/a/..a"     --- Directory or filenames can contain one ore more dots
//             "/a/." --> "/a"
//             "/.home" --> "/.home"
//             "/a/.."  --> "/"
//             "/a/../" --> "/"
//             "/a/....." ----> "/a/....."
//             "/a/.b/./....."     ---->  "/a/.b/....."


// So, here's a possible logic??
// only single dot followed by slash ---> ignore
// two dots followed by slash    ----> pop latest element which was stored in stack
// slash       ---> ignore


package com.nitesh.string;

import java.util.Deque;
import java.util.LinkedList;

public class simplifyPath {
    // "/a/..a"
    public String simplifyPathFn(String path) {
        Deque<String> pathParts = new LinkedList<>();
        int len=path.length(), numPathParts;
        StringBuilder cur = new StringBuilder();

        for(int i=0;i<len;i++) {
            char c = path.charAt(i);
            if(c=='/') {
                // Add the current string to deque
                addToPathParts(cur, pathParts);
                // reset the current string
                cur = new StringBuilder();
            } else {
                cur.append(c);
            }
        }

        // Add the final String to pathParts
        addToPathParts(cur, pathParts);
        cur=new StringBuilder();
        numPathParts = pathParts.size();

        // Create the final result
        for(int i=0;i<numPathParts;i++) {
            cur.append("/");
            cur.append(pathParts.remove());
        }

        // Return result or "/" if empty
        if(cur.toString().equals(""))
            return "/";
        return cur.toString();
    }

    // Adds a new node to the deque based on current string
    private void addToPathParts(StringBuilder cur, Deque<String> pathParts) {
        String curStr;
        curStr=cur.toString();
        if(curStr.equals(".."))
            pathParts.pollLast();
        else if(!curStr.equals(".") && !curStr.equals("")) {
            pathParts.add(curStr);
        }
    }
}
