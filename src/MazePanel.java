
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

public class MazePanel extends JPanel {

    private Maze maze;
    private List<Cell> solutionPath;
    private List<Cell> exploredCells;
    private boolean showExploredCells;

    public MazePanel(Maze maze) {
        this.maze = maze;
        solutionPath = null;
        exploredCells = new ArrayList<>();
        showExploredCells = false;
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (maze == null) {
            return;
        }

        int cellWidth = getWidth() / maze.getCols();
        int cellHeight = getHeight() / maze.getRows();

        // Draw every cell
        for (int row = 0; row < maze.getRows(); row++) {

            for (int col = 0; col < maze.getCols(); col++) {

                Cell cell = maze.getCell(row, col);

                int x = col * cellWidth;
                int y = row * cellHeight;

                // Starting cell
                if (row == 0 && col == 0) {
                    g.setColor(Color.GREEN);
                    g.fillRect(x, y, cellWidth, cellHeight);
                }

                // Finishing cell
                else if (row == maze.getRows() - 1 &&
                         col == maze.getCols() - 1) {
                    g.setColor(Color.RED);
                    g.fillRect(x, y, cellWidth, cellHeight);
                }

                // Final solution path
                else if (solutionPath != null &&
                         solutionPath.contains(cell)) {
                    g.setColor(Color.BLUE);
                    g.fillRect(x, y, cellWidth, cellHeight);
                }

                // Cells explored by BFS
                else if (showExploredCells &&
                         exploredCells.contains(cell)) {
                    g.setColor(Color.YELLOW);
                    g.fillRect(x, y, cellWidth, cellHeight);
                }

                // Cells visited during generation
                else if (cell.isVisited()) {
                    g.setColor(Color.CYAN);
                    g.fillRect(x, y, cellWidth, cellHeight);
                }

                // Draw the walls
                g.setColor(Color.BLACK);

                if (cell.hasTopWall()) {
                    g.drawLine(x, y, x + cellWidth, y);
                }

                if (cell.hasBottomWall()) {
                    g.drawLine(x, y + cellHeight,
                            x + cellWidth, y + cellHeight);
                }

                if (cell.hasLeftWall()) {
                    g.drawLine(x, y, x, y + cellHeight);
                }

                if (cell.hasRightWall()) {
                    g.drawLine(x + cellWidth, y,
                            x + cellWidth, y + cellHeight);
                }
            }
        }
    }

    // Updates the solution path
    public void setSolutionPath(List<Cell> path) {
        solutionPath = path;
        repaint();
    }

    // Updates the cells explored by BFS
    public void setExploredCells(List<Cell> cells) {
        exploredCells = cells;
        showExploredCells = true;
        repaint();
    }
}

