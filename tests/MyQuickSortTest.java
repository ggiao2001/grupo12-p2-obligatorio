import uy.edu.um.prog2.adt.Entities.QuickSort;
import org.junit.Assert;
        import org.junit.Test;

public class MyQuickSortTest {

    @Test
    public void testQuickSortAscending() {
        int[] arr = {5, 2, 9, 1, 3};
        int[] expected = {1, 2, 3, 5, 9};
        QuickSort.quickSort(arr, 0, arr.length - 1, false);
        Assert.assertArrayEquals(expected, arr);
    }

    @Test
    public void testQuickSortDescending() {
        int[] arr = {5, 2, 9, 1, 3};
        int[] expected = {9, 5, 3, 2, 1};
        QuickSort.quickSort(arr, 0, arr.length - 1, true);
        Assert.assertArrayEquals(expected, arr);
    }

    @Test
    public void testQuickSortEmptyArray() {
        int[] arr = {};
        int[] expected = {};
        QuickSort.quickSort(arr, 0, arr.length - 1, false);
        Assert.assertArrayEquals(expected, arr);
    }

    @Test
    public void testQuickSortAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        QuickSort.quickSort(arr, 0, arr.length - 1, false);
        Assert.assertArrayEquals(expected, arr);
    }

    @Test
    public void testQuickSortSingleElement() {
        int[] arr = {42};
        int[] expected = {42};
        QuickSort.quickSort(arr, 0, arr.length - 1, false);
        Assert.assertArrayEquals(expected, arr);
    }
}
