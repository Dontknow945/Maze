package a2.maze;

import java.util.Scanner;
import java.util.ArrayList;

public class Maze {
	public static class Point {
        public int x;
        public int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
	
	public static boolean solve(int n[][],Point s,Point e,ArrayList<Point> ary)
	{
		if(n[1][1]==0 || n[e.x][e.y]==0){return false;}		//check if start or end point is wall
		else if(n[s.x][s.y]==1){
			n[s.x][s.y]=2;
			ary.add(s);
			if(!(solve(n, new Point(s.x, s.y + 1), e, ary) ||
					solve(n, new Point(s.x + 1, s.y), e, ary) ||
					solve(n, new Point(s.x, s.y - 1), e, ary) ||
					solve(n, new Point(s.x - 1, s.y), e, ary))){
				ary.remove(ary.size() - 1);
                n[s.x][s.y] = 1;
			}
		}
		return n[e.x][e.y] == 2;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int a=0;
		int n[][];
		
		System.out.println("Enter the size of the maze(N*N;1<=N<=20)");
		while(a>20||a<1)			//prevent from entering number that over 20 or less than 1
		{
			a=sc.nextInt();
			if(a>20||a<1)
			{
				System.out.println("Error! Enter again.");
			}
		}
		
		n=new int[a+2][a+2];
		System.out.println("Enter the maze(Only 0 or 1)");
		
		for(int i=0;i<a;i++)		//create a wall on each side  
		{
			for(int j=0;j<a;j++)
			{
				if(i==0||j==0||i==a+1||j==a+1)
				{
					n[i][j]=0;
				}
			}
		}
		
		for(int i=1;i<=a;i++)		//entering the maze
		{
			for(int j=1;j<=a;j++)
			{
				n[i][j]=sc.nextInt();
			}
		}
		
		System.out.println("The maze is:");		//show the maze
		for(int i=1;i<a+1;i++)
		{
			for(int j=1;j<a+1;j++)
			{
				System.out.print(n[i][j]+"\t");
			}
			System.out.println("\n");
		}
		
		ArrayList<Point> ary = new ArrayList<Point>();
		boolean ans = solve(n,new Point(1,1),new Point(a,a),ary);
		if(ans==true)
			System.out.println("You can reach the exit.");
		else if(ans==false)
			System.out.println("You can not reach the exit.");
		else
			System.out.println("Error.");
		
		sc.close();
	}
}
