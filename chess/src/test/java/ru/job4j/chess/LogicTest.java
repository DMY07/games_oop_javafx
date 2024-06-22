package ru.job4j.chess;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.BishopBlack;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogicTest {

    @Test
    public void whenMoveThenFigureNotFoundException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        FigureNotFoundException exception = assertThrows(FigureNotFoundException.class, () -> {
            logic.move(Cell.C1, Cell.H6);
        });
        assertThat(exception.getMessage()).isEqualTo("Figure not found on the board.");
    }

    @Test
    public void whenMoveThenImpossibleMoveException() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.C1));
        ImpossibleMoveException exception = assertThrows(ImpossibleMoveException.class, () -> {
            logic.move(Cell.C1, Cell.C4);
        });
        assertThat(exception.getMessage()).isEqualTo("Could not move by diagonal from C1 to C4");
    }

    @Test
    public void wheтSuccess() throws FigureNotFoundException,
            ImpossibleMoveException, OccupiedCellException {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.C1));
        logic.move(Cell.C1, Cell.H6);
        int index = logic.findBy(Cell.H6);
        Figure movedFigure = logic.getFigures().get(index);
        assertThat(movedFigure.position()).isEqualTo(Cell.H6);
    }
}
