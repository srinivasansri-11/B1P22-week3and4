class Client {
    String id;
    int riskScore;
    double accountBalance;

    Client(String id, int riskScore, double accountBalance) {
        this.id = id;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return id + "(" + riskScore + ")";
    }
}

public class ClientRiskScoreRanking {

    public static void bubbleSort(Client[] clients) {
        int n = clients.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (clients[j].riskScore > clients[j + 1].riskScore) {
                    // visualize swap
                    System.out.println("Swapping " + clients[j] + " <-> " + clients[j + 1]);
                    Client temp = clients[j];
                    clients[j] = clients[j + 1];
                    clients[j + 1] = temp;
                    swaps++;
                }
            }
        }

        System.out.print("BubbleSort (asc): ");
        for (Client c : clients) System.out.print(c + " ");
        System.out.println("\nTotal swaps: " + swaps);
    }

    public static void insertionSort(Client[] clients) {
        int n = clients.length;
        for (int i = 1; i < n; i++) {
            Client key = clients[i];
            int j = i - 1;

            while (j >= 0 &&
                    (clients[j].riskScore < key.riskScore ||
                            (clients[j].riskScore == key.riskScore &&
                                    clients[j].accountBalance > key.accountBalance))) {
                clients[j + 1] = clients[j];
                j--;
            }
            clients[j + 1] = key;
        }

        System.out.print("InsertionSort (desc): ");
        for (Client c : clients) System.out.print(c + " ");
        System.out.println();
    }

    public static void topRisks(Client[] clients, int topN) {
        System.out.print("Top " + topN + " risks: ");
        for (int i = 0; i < Math.min(topN, clients.length); i++) {
            System.out.print(clients[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Client[] clients = {
                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 2000),
                new Client("clientB", 50, 3000)
        };


        bubbleSort(clients.clone());

        Client[] clientsCopy = clients.clone();
        insertionSort(clientsCopy);

        topRisks(clientsCopy, 3);
    }
}