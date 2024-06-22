package ru.job4j.chess.firuges.black;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.ImpossibleMoveException;

import static org.assertj.core.api.Assertions.*;

public class BishopBlackTest {

    @Test
    public void whenPositionThenCorrect() {
        BishopBlack bishop = new BishopBlack(Cell.C1);
        assertThat(bishop.position()).isEqualTo(Cell.C1);
    }

    @Test
    public void whenCopyThenCorrectPosition() {
        BishopBlack bishop = new BishopBlack(Cell.C1);
        Figure bishopCopy = bishop.copy(Cell.E3);
        assertThat(bishopCopy.position()).isEqualTo(Cell.E3);
    }

    @Test
    public void whenWayC1toG5ThenCorrectPath() {
        BishopBlack bishop = new BishopBlack(Cell.C1);
        Cell[] expectedPath = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        assertThat(bishop.way(Cell.G5)).isEqualTo(expectedPath);
    }

    @Test
    public void whenWayNotDiagonalThenException() {
        BishopBlack bishop = new BishopBlack(Cell.C1);
        assertThatThrownBy(() -> bishop.way(Cell.C3))
                .isInstanceOf(ImpossibleMoveException.class)
                .hasMessageContaining("Could not move by diagonal from C1 to C3");
    }
}
