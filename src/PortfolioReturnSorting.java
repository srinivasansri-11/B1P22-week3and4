import java.util.*;

class Asset {
    String symbol;
    double returnRate;
    double volatility;

    Asset(String symbol, double returnRate, double volatility) {
        this.symbol = symbol;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    @Override
    public String toString() {
        return symbol + ":" + returnRate + "%";
    }
}

public class PortfolioReturnSorting {

    public static void mergeSort(Asset[] assets, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(assets, left, mid);
            mergeSort(assets, mid + 1, right);
            merge(assets, left, mid, right);
        }
    }

    private static void merge(Asset[] assets, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Asset[] L = new Asset[n1];
        Asset[] R = new Asset[n2];

        for (int i = 0; i < n1; i++) L[i] = assets[left + i];
        for (int j = 0; j < n2; j++) R[j] = assets[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i].returnRate <= R[j].returnRate) {
                assets[k++] = L[i++];
            } else {
                assets[k++] = R[j++];
            }
        }
        while (i < n1) assets[k++] = L[i++];
        while (j < n2) assets[k++] = R[j++];
    }

    public static void quickSort(Asset[] assets, int low, int high) {
        if (low < high) {
            int pi = partition(assets, low, high);
            quickSort(assets, low, pi - 1);
            quickSort(assets, pi + 1, high);
        }
    }

    private static int partition(Asset[] assets, int low, int high) {
        int mid = (low + high) / 2;
        Asset pivot = medianOfThree(assets[low], assets[mid], assets[high]);
        double pivotReturn = pivot.returnRate;
        double pivotVol = pivot.volatility;

        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (assets[j].returnRate > pivotReturn ||
                    (assets[j].returnRate == pivotReturn && assets[j].volatility < pivotVol)) {
                i++;
                Asset temp = assets[i];
                assets[i] = assets[j];
                assets[j] = temp;
            }
        }
        Asset temp = assets[i + 1];
        assets[i + 1] = assets[high];
        assets[high] = temp;
        return i + 1;
    }

    private static Asset medianOfThree(Asset a, Asset b, Asset c) {
        if (a.returnRate > b.returnRate) {
            if (a.returnRate < c.returnRate) return a;
            else if (b.returnRate > c.returnRate) return b;
            else return c;
        } else {
            if (b.returnRate < c.returnRate) return b;
            else if (a.returnRate > c.returnRate) return a;
            else return c;
        }
    }

    public static void main(String[] args) {
        Asset[] assets = {
                new Asset("AAPL", 12.0, 0.25),
                new Asset("TSLA", 8.0, 0.40),
                new Asset("GOOG", 15.0, 0.20)
        };

        Asset[] mergeSorted = assets.clone();
        mergeSort(mergeSorted, 0, mergeSorted.length - 1);
        System.out.println("MergeSort (asc): " + Arrays.toString(mergeSorted));

        Asset[] quickSorted = assets.clone();
        quickSort(quickSorted, 0, quickSorted.length - 1);
        System.out.println("QuickSort (desc): " + Arrays.toString(quickSorted));
    }
}