package fourier;



import com.sun.javafx.geom.Point2D;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Josh on 3/10/2017.
 */
public class ClosestPair {



    // closest pair of points and their Euclidean distance
    private Point best1, best2;
    private double bestDistance = Double.POSITIVE_INFINITY;

    /**
     * Computes the closest pair of points in the specified array of points.
     *
     * @param  points the array of points
     * @throws NullPointerException if {@code points} is {@code null} or if any
     *         entry in {@code points[]} is {@code null}
     */
    public ClosestPair(Point[] points) {
        int n = points.length;
        if (n <= 1) return;

        // sort by x-coordinate (breaking ties by y-coordinate)
        Point[] pointsByX = points.clone();

        FFT.bubbleSort(pointsByX, 'x');

        // check for coincident points
        for (int i = 0; i < n-1; i++) {
            if (pointsByX[i].equals(pointsByX[i+1])) {
                bestDistance = 0.0;
                best1 = pointsByX[i];
                best2 = pointsByX[i+1];
                return;
            }
        }

        // sort by y-coordinate (but not yet sorted)
        Point[] pointsByY = pointsByX.clone();

        // auxiliary array
        Point[] aux = new Point[n];

        closest(pointsByX, pointsByY, aux, 0, n-1);
    }

    // find closest pair of points in pointsByX[lo..hi]
    // precondition:  pointsByX[lo..hi] and pointsByY[lo..hi] are the same sequence of points
    // precondition:  pointsByX[lo..hi] sorted by x-coordinate
    // postcondition: pointsByY[lo..hi] sorted by y-coordinate
    private double closest(Point[] pointsByX, Point[] pointsByY, Point[] aux, int lo, int hi) {
        if (hi <= lo) return Double.POSITIVE_INFINITY;

        int mid = lo + (hi - lo) / 2;
        Point median = pointsByX[mid];

        // compute closest pair with both endpoints in left subarray or both in right subarray
        double delta1 = closest(pointsByX, pointsByY, aux, lo, mid);
        double delta2 = closest(pointsByX, pointsByY, aux, mid+1, hi);
        double delta = Math.min(delta1, delta2);

        // merge back so that pointsByY[lo..hi] are sorted by y-coordinate
        merge(pointsByY, aux, lo, mid, hi);

        // aux[0..m-1] = sequence of points closer than delta, sorted by y-coordinate
        int m = 0;
        for (int i = lo; i <= hi; i++) {
            if (Math.abs(pointsByY[i].x - median.x) < delta)
                aux[m++] = pointsByY[i];
        }

        // compare each point to its neighbors with y-coordinate closer than delta
        for (int i = 0; i < m; i++) {
            // a geometric packing argument shows that this loop iterates at most 7 times
            for (int j = i+1; (j < m) && (aux[j].y - aux[i].y < delta); j++) {
                double distance = FFT.distance(aux[i], aux[j]);
                if (distance < delta) {
                    delta = distance;
                    if (distance < bestDistance) {
                        bestDistance = delta;
                        best1 = aux[i];
                        best2 = aux[j];
                        // StdOut.println("better distance = " + delta + " from " + best1 + " to " + best2);
                    }
                }
            }
        }
        return delta;
    }

    /**
     * Returns one of the points in the closest pair of points.
     *
     * @return one of the two points in the closest pair of points;
     *         {@code null} if no such point (because there are fewer than 2 points)
     */
    public Point either() {
        return best1;
    }

    /**
     * Returns the other point in the closest pair of points.
     *
     * @return the other point in the closest pair of points
     *         {@code null} if no such point (because there are fewer than 2 points)
     */
    public Point other() {
        return best2;
    }

    /**
     * Returns the Eucliden distance between the closest pair of points.
     *
     * @return the Euclidean distance between the closest pair of points
     *         {@code Double.POSITIVE_INFINITY} if no such pair of points
     *         exist (because there are fewer than 2 points)
     */
    public double distance() {
        return bestDistance;
    }

    // is v < w ?
    private static boolean less(Point v, Point w) {
        return v.y-w.y < 0;
    }

    // stably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo .. hi]
    // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
    private static void merge(Point[] a, Point[] aux, int lo, int mid, int hi) {
        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        // merge back to a[]e
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }
    }



    /**
     * Unit tests the {@code ClosestPair} data type.
     * Reads in an integer {@code n} and {@code n} points (specified by
     * their <em>x</em>- and <em>y</em>-coordinates) from standard input;
     * computes a closest pair of points; and prints the pair to standard
     * output.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        Point[] _points;

        Random rand = new Random(5);
        _points = new Point[5];
        StringBuilder pointsString = new StringBuilder();

        for(int p = 0; p < _points.length; p++) {
            _points[p] = new Point();
            _points[p].x = rand.nextInt(50) - 25;
            _points[p].y = rand.nextInt(50)  - 25;
        }

        ClosestPair cp = new ClosestPair(_points);

    }
}
