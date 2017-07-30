import java.util.*;

/*
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

Example 1

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: true
Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.

Example 2

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: false
Explanation: There is no way for the ball to stop at the destination.

Note:
There is only one ball and one destination in the maze.
Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.

DFS, BFS
 */
public class LT490_The_Maze {
int[][] dirs = new int[][]{ {-1, 0}, {1, 0}, {0, 1}, {0, -1} };
    
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if (Arrays.equals(start, destination)) return true;
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        Queue<int[]> queue = new LinkedList<>();                //queue store new start
        queue.offer(start);
        visited[start[0]][start[1]] = true;
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            if(cur[0]==destination[0] && cur[1]==destination[1]) return true;
            
            //put for direction into queue. 
            for (int i = 0; i < dirs.length; i++) {
                int[] d = dirs[i];
                int row = cur[0];
                int col = cur[1];
                while (isValid(maze, row + d[0], col + d[1])) {        //can roll. reach the wall and change direction
                    row += d[0];
                    col += d[1];
                }
                
                if(!visited[row][col]){
                    visited[row][col]=true;
                    queue.offer(new int[]{row,col});
                }
            }
        }
        
        return false;
    }

    
    private boolean isValid(int[][] maze, int row, int col) {
        return row >= 0 && row < maze.length && col >= 0 && col < maze[0].length && maze[row][col] != 1;
    }
}
