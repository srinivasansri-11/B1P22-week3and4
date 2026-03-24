public class AccountIDLookupinTransactionLogs {
}
import java.util.Arrays;

public class AccountIDLookupinTransactionLogs {


    public static void linearSearch(String[] logs, String target) {
        int first = -1, last = -1;
        int comparisons = 0;

        for (int i = 0; i < logs.length; i++) {
            comparisons++;
            if (logs[i].equals(target)) {
                if (first == -1) first = i;
                last = i;
            }
        }

        System.out.println("Linear Search for " + target + ":");
        System.out.println("First occurrence index = " + first);
        System.out.println("Last occurrence index = " + last);
        System.out.println("Comparisons = " + comparisons);
        System.out.println("Time Complexity = O(n)");
    }


    public static void binarySearch(String[] logs, String target) {
        int low = 0, high = logs.length - 1;
        int comparisons = 0;
        int foundIndex = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            if (logs[mid].equals(target)) {
                foundIndex = mid;
                break;
            } else if (logs[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        int count = 0;
        if (foundIndex != -1) {
            int left = foundIndex;
            while (left >= 0 && logs[left].equals(target)) {
                count++;
                left--;
            }
            int right = foundIndex + 1;
            while (right < logs.length && logs[right].equals(target)) {
                count++;
                right++;
            }
        }

        System.out.println("Binary Search for " + target + ":");
        System.out.println("Found index = " + foundIndex);
        System.out.println("Occurrences = " + count);
        System.out.println("Comparisons = " + comparisons);
        System.out.println("Time Complexity = O(log n)");
    }

    public static void main(String[] args) {
        String[] logs = {"accB", "accA", "accB", "accC"};

        Arrays.sort(logs);
        System.out.println("Sorted logs: " + Arrays.toString(logs));

        linearSearch(logs, "accB");

        binarySearch(logs, "accB");
    }
}