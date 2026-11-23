
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.util.List;

public class MazePanel extends JPanel {

    private Maze maze;

    private List<Cell> solutionPath;

    public MazePanel(Maze maze) {
    this.maze = maze;
    solutionPath = null;
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

                // Colour the starting cell green
if (row == 0 && col == 0) {
    g.setColor(Color.GREEN);
    g.fillRect(x, y, cellWidth, cellHeight);
}

// Colour the finishing cell red
else if (row == maze.getRows() - 1 &&
         col == maze.getCols() - 1) {
    g.setColor(Color.RED);
    g.fillRect(x, y, cellWidth, cellHeight);
}

// Colour the solution path blue
else if (solutionPath != null && solutionPath.contains(cell)) {
    g.setColor(Color.BLUE);
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
        public void setSolutionPath(List<Cell> path) {
    solutionPath = path;
    repaint();
    }
}

