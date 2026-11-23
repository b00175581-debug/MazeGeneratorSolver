
import java.util.ArrayList;
import java.util.List;

public class MazeSolver {

    private Maze maze;
    private boolean[][] visited;
    private Cell[][] previous;
    private ArrayQueue<Cell> queue;

    private List<Cell> exploredCells;
    private List<Cell> solutionPath;

    private boolean finished;

    public MazeSolver(Maze maze) throws QueueException {
        this.maze = maze;

        visited = new boolean[maze.getRows()][maze.getCols()];
        previous = new Cell[maze.getRows()][maze.getCols()];

        queue = new ArrayQueue<>(
                maze.getRows() * maze.getCols()
        );

        exploredCells = new ArrayList<>();
        solutionPath = new ArrayList<>();

        // Start BFS at the top-left cell
        Cell start = maze.getCell(0, 0);

        visited[0][0] = true;
        queue.enqueue(start);

        finished = false;
    }

    // Performs one step of BFS
    public void step() throws QueueException {

        if (finished) {
            return;
        }

        if (queue.isEmpty()) {
            finished = true;
            return;
        }

        Cell current = queue.dequeue();

        // Record the cell being explored
        exploredCells.add(current);

        int row = current.getRow();
        int col = current.getCol();

        // Check if we reached the finish
        if (row == maze.getRows() - 1 &&
                col == maze.getCols() - 1) {

            solutionPath = buildPath(current);
            finished = true;
            return;
        }

        // Check the cell above
        if (row > 0 && !current.hasTopWall() &&
                !visited[row - 1][col]) {

            Cell next = maze.getCell(row - 1, col);
            visited[row - 1][col] = true;
            previous[row - 1][col] = current;
            queue.enqueue(next);
        }

        // Check the cell below
        if (row < maze.getRows() - 1 &&
                !current.hasBottomWall() &&
                !visited[row + 1][col]) {

            Cell next = maze.getCell(row + 1, col);
            visited[row + 1][col] = true;
            previous[row + 1][col] = current;
            queue.enqueue(next);
        }

        // Check the cell to the left
        if (col > 0 && !current.hasLeftWall() &&
                !visited[row][col - 1]) {

            Cell next = maze.getCell(row, col - 1);
            visited[row][col - 1] = true;
            previous[row][col - 1] = current;
            queue.enqueue(next);
        }

        // Check the cell to the right
        if (col < maze.getCols() - 1 &&
                !current.hasRightWall() &&
                !visited[row][col + 1]) {

            Cell next = maze.getCell(row, col + 1);
            visited[row][col + 1] = true;
            previous[row][col + 1] = current;
            queue.enqueue(next);
        }

        // Check whether there are any cells left to explore
        if (queue.isEmpty()) {
            finished = true;
        }
    }

    // Checks whether BFS has finished
    public boolean isFinished() {
        return finished;
    }

    // Returns all cells explored by BFS
    public List<Cell> getExploredCells() {
        return exploredCells;
    }

    // Returns the final shortest path
    public List<Cell> getSolutionPath() {
        return solutionPath;
    }

    // Original method retained for testing
    public List<Cell> solveMaze() throws QueueException {

        while (!isFinished()) {
            step();
        }

        return solutionPath;
    }

    // Reconstructs the shortest path
    private List<Cell> buildPath(Cell current) {

        List<Cell> path = new ArrayList<>();

        while (current != null) {

            path.add(0, current);

            current = previous[current.getRow()][current.getCol()];
        }

        return path;
    }
}
