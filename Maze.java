package Maze;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;

public class Maze
{
    static int w;
    static int h;
    static int[][] maze;
    
    static {
        Maze.w = 41;
        Maze.h = 41;
        Maze.maze = new int[Maze.w][Maze.h];
    }
    
    public static void inputMaze(final String file) {
        String line = "";
        BufferedReader br = null;
        int y = 0;
        try {
            br = new BufferedReader(new FileReader(file));
            line = br.readLine();
            final String cvsSplitBy = ",";
            while ((line = br.readLine()) != null) {
                final String[] maze1 = line.split(cvsSplitBy);
                for (int i = 0; i < 41; ++i) {
                    int count = 0;
                    count = Integer.parseInt(maze1[i + 1]);
                    Maze.maze[y][i] = count;
                }
                ++y;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                }
                catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        }
        if (br != null) {
            try {
                br.close();
            }
            catch (IOException e3) {
                e3.printStackTrace();
            }
        }
        System.out.println("Maze is loaded");
        System.out.println("5's are paths it went through");
        System.out.println("7's is the succesful path");
        System.out.println();
    }
    
    public static boolean solveMaze(final int r, final int c) {
        boolean mazeIsSolveable = false;
        if (r >= 0 && r < Maze.maze.length && c >= 0 && c < Maze.maze[0].length && Maze.maze[r][c] == 1) {
            Maze.maze[r][c] = 5;
            if (r == 40 && c == 17) {
                mazeIsSolveable = true;
                Maze.maze[r][c] = 7;
            }
            else {
                for (int i = 0; i < 6; ++i) {
                    switch (i) {
                        case 0: {
                            if (!mazeIsSolveable) {
                                mazeIsSolveable = solveMaze(r + 1, c);
                                break;
                            }
                        }
                        case 1: {
                            if (!mazeIsSolveable) {
                                mazeIsSolveable = solveMaze(r - 1, c);
                                break;
                            }
                        }
                        case 2: {
                            if (!mazeIsSolveable) {
                                mazeIsSolveable = solveMaze(r, c + 1);
                                break;
                            }
                        }
                        case 3: {
                            if (!mazeIsSolveable) {
                                mazeIsSolveable = solveMaze(r, c - 1);
                                break;
                            }
                        }
                        case 4: {
                            if (mazeIsSolveable) {
                                Maze.maze[r][c] = 7;
                                break;
                            }
                            break;
                        }
                    }
                }
            }
        }
        return mazeIsSolveable;
    }
    
    public static void printMaze() {
        for (int r = 0; r < Maze.maze.length; ++r) {
            for (int c = 0; c < Maze.maze[r].length; ++c) {
                System.out.print(Maze.maze[r][c]);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public static void main(final String[] args) {
        inputMaze("C:/Users/Owner/Desktop/Comp182/Comp182Project_4/Maze - Input.csv");
        solveMaze(0, 27);
        printMaze();
    }
}