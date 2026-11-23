import java.util.List;

public class SolverTest {

    public static void main(String[] args)
            throws StackException, QueueException {

        Maze maze = new Maze(10, 10);

        MazeGenerator generator = new MazeGenerator(maze);
        generator.generateMaze();

        MazeSolver solver = new MazeSolver(maze);
        List<Cell> path = solver.solveMaze();

        System.out.println("Maze generated successfully!");
        System.out.println("Path found: " + !path.isEmpty());
        System.out.println("Path length: " + path.size());

        if (!path.isEmpty()) {

            Cell start = path.get(0);
            Cell end = path.get(path.size() - 1);

            System.out.println("Start: (" +
                    start.getRow() + ", " + start.getCol() + ")");

            System.out.println("End: (" +
                    end.getRow() + ", " + end.getCol() + ")");
        }
    }
}
