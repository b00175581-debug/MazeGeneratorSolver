
public class Maze {

    private int rows;
    private int cols;
    private Cell[][] grid;

    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        grid = new Cell[rows][cols];

        // Create every cell in the grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = new Cell(r, c);
            }
        }
    }

    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public static void main(String[] args) {
}
}