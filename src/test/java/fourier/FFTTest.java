package fourier;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import plotter.GNUterm;
import plotter.GnuPlotter2D;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Josh on 3/10/2017.
 */
public class FFTTest {

    Point[] _points;

    @Before
    public void buildPoints(){

        Random rand = new Random(5);
        _points = new Point[4];
        StringBuilder pointsString = new StringBuilder();

        for(int p = 0; p < _points.length; p++) {
            _points[p] = new Point();
            _points[p].x = rand.nextInt(50) - 25;
            _points[p].y = rand.nextInt(50)  - 25;
        }

    }

    @Test
    public void closestPoints() throws Exception {

        GnuPlotter2D _gnu;
        StringBuilder pointsString = new StringBuilder();
        Point[] px = _points.clone();
        Point[] py = _points.clone();

        FFT.bubbleSort(px, 'x');
        FFT.bubbleSort(py, 'y');
        FFT.bubbleSort(_points, 'x');

        Point[] closest = FFT.closestPoints(_points);
        Arrays.stream(_points).forEach(P -> pointsString.append(P.x).append(" ").append(P.y).append(" 4").append("\n\n"));
        pointsString.append(closest[0].x + " " + closest[0].y + " 7\n\n");
        pointsString.append(closest[1].x + " " + closest[1].y + " 7\n\n");

        _gnu = new GnuPlotter2D(pointsString.toString(), "randomPoints");
        _gnu.setTerminal(GNUterm.WINDOWS);
        _gnu.setWithLines(false);
        _gnu.setHasColors(true);
        _gnu.plotGnu();

    }

    @Test
    public void bruteforce() throws Exception {

        GnuPlotter2D _gnu;
        StringBuilder pointsString = new StringBuilder();
        Point[] closest = FFT.bruteforce(_points);
        Arrays.stream(_points).forEach(P -> pointsString.append(P.x).append(" ").append(P.y).append(" 4").append("\n\n"));
        pointsString.append(closest[0].x + " " + closest[0].y + " 7\n\n");
        pointsString.append(closest[1].x + " " + closest[1].y + " 7\n\n");

        _gnu = new GnuPlotter2D(pointsString.toString(), "randomPoints");
        _gnu.setTerminal(GNUterm.WINDOWS);
        _gnu.setWithLines(false);
        _gnu.setHasColors(true);
        _gnu.plotGnu();
    }

    @Test
    public void distance() throws Exception {

    }

}