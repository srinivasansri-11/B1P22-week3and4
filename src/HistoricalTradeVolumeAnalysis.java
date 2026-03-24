import java.util.*;

class Trade {
    String id;
    int volume;

    Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return id + ":" + volume;
    }
}

public class HistoricalTradeVolumeAnalysis {

    public static void mergeSort(Trade[] trades, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(trades, left, mid);
            mergeSort(trades, mid + 1, right);
            merge(trades, left, mid, right);
        }
    }

    private static void merge(Trade[] trades, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Trade[] L = new Trade[n1];
        Trade[] R = new Trade[n2];

        for (int i = 0; i < n1; i++) L[i] = trades[left + i];
        for (int j = 0; j < n2; j++) R[j] = trades[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i].volume <= R[j].volume) {
                trades[k++] = L[i++];
            } else {
                trades[k++] = R[j++];
            }
        }
        while (i < n1) trades[k++] = L[i++];
        while (j < n2) trades[k++] = R[j++];
    }

    public static void quickSort(Trade[] trades, int low, int high) {
        if (low < high) {
            int pi = lomutoPartition(trades, low, high);
            quickSort(trades, low, pi - 1);
            quickSort(trades, pi + 1, high);
        }
    }

    private static int lomutoPartition(Trade[] trades, int low, int high) {
        Trade pivot = trades[high]; // pivot
        int i = low - 1;
        for (int j = low; j < high; j++) {

            if (trades[j].volume >= pivot.volume) {
                i++;
                Trade temp = trades[i];
                trades[i] = trades[j];
                trades[j] = temp;
            }
        }
        Trade temp = trades[i + 1];
        trades[i + 1] = trades[high];
        trades[high] = temp;
        return i + 1;
    }

    public static List<Trade> mergeSortedLists(List<Trade> morning, List<Trade> afternoon) {
        List<Trade> merged = new ArrayList<>();
        int i = 0, j = 0;
        while (i < morning.size() && j < afternoon.size()) {
            if (morning.get(i).volume <= afternoon.get(j).volume) {
                merged.add(morning.get(i++));
            } else {
                merged.add(afternoon.get(j++));
            }
        }
        while (i < morning.size()) merged.add(morning.get(i++));
        while (j < afternoon.size()) merged.add(afternoon.get(j++));
        return merged;
    }

    public static int computeTotalVolume(List<Trade> trades) {
        int total = 0;
        for (Trade t : trades) total += t.volume;
        return total;
    }

    public static void main(String[] args) {
        Trade[] trades = {
                new Trade("trade3", 500),
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        };

        Trade[] mergeSorted = trades.clone();
        mergeSort(mergeSorted, 0, mergeSorted.length - 1);
        System.out.println("MergeSort (asc): " + Arrays.toString(mergeSorted));

        Trade[] quickSorted = trades.clone();
        quickSort(quickSorted, 0, quickSorted.length - 1);
        System.out.println("QuickSort (desc): " + Arrays.toString(quickSorted));

        List<Trade> morning = Arrays.asList(new Trade("m1", 200), new Trade("m2", 400));
        List<Trade> afternoon = Arrays.asList(new Trade("a1", 100), new Trade("a2", 300));
        List<Trade> merged = mergeSortedLists(morning, afternoon);
        System.out.println("Merged list: " + merged);

        int total = computeTotalVolume(merged);
        System.out.println("Total volume: " + total);
    }
}