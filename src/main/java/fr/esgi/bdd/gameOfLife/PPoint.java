package fr.esgi.bdd.gameOfLife;

import javax.annotation.processing.Generated;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SuppressWarnings({"checkstyle:MemberName", "PMD.ShortVariable"})
public record PPoint(@Generated({}) int x, @Generated({}) int y) implements Point<PPoint> {
    @SuppressWarnings({"checkstyle:ParenPad", "CommentsIndentation"})
    private static final Set<PPoint> NEIGHBORS_OF_ORIGIN =
            (Set<PPoint>) P(-1, -1).rangeTo(P(1, 1)).filter(it -> !it.equals(P(0, 0))).collect(Collectors.toSet());

    // Using the unusual name P for creating a DSL.
    @SuppressWarnings({"squid:S00100", "checkstyle:MethodName", "PMD.ShortMethodName", "PMD.MethodNamingConventions"})
    public static PPoint P(final int x, final int y) {
        return new PPoint(x, y);
    }

    @Override
    public PPoint plus(final PPoint p) {
        return new PPoint(x + p.x, y + p.y);
    }

    @Override
    public Stream<PPoint> rangeTo(final PPoint p) {
        return IntStream.range(this.x, p.x + 1).boxed().flatMap(
                nx -> IntStream.range(this.y, p.y + 1).mapToObj(
                        ny -> P(nx, ny)
                )
        );
    }

    @Override
    public Stream<PPoint> neighbors() {
        return NEIGHBORS_OF_ORIGIN.stream().map(this::plus);
    }

    @Override
    public String toString() {
        return "P(" + x + ", " + y + ")";
    }
}

