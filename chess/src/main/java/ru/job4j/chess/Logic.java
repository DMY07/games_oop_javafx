package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.util.ArrayList;
import java.util.List;

public final class Logic {
    private final List<Figure> figures = new ArrayList<>();

    public void add(Figure figure) {
        figures.add(figure);
    }

    public void move(Cell source, Cell dest)
            throws FigureNotFoundException, ImpossibleMoveException, OccupiedCellException {
        int index = findBy(source);
        Cell[] steps = figures.get(index).way(dest);
        free(steps);
        figures.set(index, figures.get(index).copy(dest));
    }

    private boolean free(Cell[] steps) throws OccupiedCellException {
        for (Cell step : steps) {
            for (Figure figure : figures) {
                if (figure != null && figure.position().equals(step)) {
                    throw new OccupiedCellException("Cell " + step + " is occupied");
                }
            }
        }
        return true;
    }

    public void clean() {
        figures.clear();
    }

    int findBy(Cell cell) throws FigureNotFoundException {
        for (int index = 0; index != figures.size(); index++) {
            Figure figure = figures.get(index);
            if (figure != null && figure.position().equals(cell)) {
                return index;
            }
        }
        throw new FigureNotFoundException("Figure not found on the board.");
    }

    public List<Figure> getFigures() {
        return figures;
    }
}
