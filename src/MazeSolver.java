import java.util.ArrayList;
import java.util.List;

public class MazeSolver {

    private Maze maze;
    private boolean[][] visited;
    private Cell[][] previous;

    public MazeSolver(Maze maze) {
        this.maze = maze;

        visited = new boolean[maze.getRows()][maze.getCols()];
        previous = new Cell[maze.getRows()][maze.getCols()];
    }

    public List<Cell> solveMaze() throws QueueException {

        ArrayQueue<Cell> queue = new ArrayQueue<>(
                maze.getRows() * maze.getCols()
        );

        Cell start = maze.getCell(0, 0);

        visited[0][0] = true;
        queue.enqueue(start);

        while (!queue.isEmpty()) {

            Cell current = queue.dequeue();

            int row = current.getRow();
            int col = current.getCol();

            // Check if we reached the bottom-right cell
            if (row == maze.getRows() - 1 &&
                    col == maze.getCols() - 1) {
                return buildPath(current);
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
        }

        // No path was found
        return new ArrayList<>();
    }

    private List<Cell> buildPath(Cell current) {

        List<Cell> path = new ArrayList<>();

        while (current != null) {

            path.add(0, current);

            current = previous[current.getRow()][current.getCol()];
        }

        return path;
    }
}
