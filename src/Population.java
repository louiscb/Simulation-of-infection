import java.util.ArrayList;
import java.util.Random;

public class Population {
    int numberOfInitialIll;
    double probOfInfection;
    double probOfDead;
    int daysMinIll;
    int daysMaxIll;
    int totalBodyCount;
    int totalImmuneCount;
    int totalIllCount;
    int illCount;
    int size = 0;
    Member[][] matrix;

    public Population(int populationNumber) {
        populateMatrix(populationNumber);
    }

    private void populateMatrix(int populationNumber) {
        size = populationNumber;
        int hozVertNumber = (int) Math.sqrt(populationNumber);
        matrix = new Member[hozVertNumber][hozVertNumber];

        for (int y = 0; y < matrix.length; y++)
            for (int x = 0; x < matrix[y].length; x++)
                matrix[y][x] = new Member(x, y);
    }

    public void printMatrix() {
        System.out.println("------------------------------");
        for (Member[] members : matrix) {
            for (Member member : members) {
                System.out.print(member.getStatus());
            }
            System.out.println("");
        }
        System.out.println("------------------------------");
    }

    public void setMemberStatus(int x, int y, String status) {
        matrix[x][y].setStatus(status);
    }

    public void runSimulation() {
        setSeed(0);
        totalIllCount = numberOfInitialIll;
        illCount = totalIllCount;
        int bodyCount = 0;

        //Run program until number of Ill reaches 0
        do {
            int recoverCount = 0;
            for (Member[] members : matrix) {
                for (Member member : members) {
                    if (member.getStatus().equals(StatusType.INFECTED)) {
                        member.incremenetDaysIll();

                        if (member.getDaysIll() > daysMaxIll) {
                            illCount--;
                            member.setStatus(StatusType.RECOVERED);
                            recoverCount++;
                        } else {
                            //PROBABILY TO DIE
                            if (member.getDaysIll() > daysMinIll && random.nextDouble() < probOfDead) {
                                member.setStatus(StatusType.DEAD);

                                illCount--;
                                bodyCount++;
                            } else {
                                infectNeighbours(member);
                            }
                        }
                    }
                }
            }
            System.out.println("Number of infected today was " + illCount);
            System.out.println("Number of recovered today was " + recoverCount);
            System.out.println("Number of dead today was " + bodyCount);
            totalBodyCount += bodyCount;
            totalImmuneCount += recoverCount;
        } while (illCount > 0);

        printMatrix();
        System.out.println("Total number of infected was " + totalIllCount);
        System.out.println("Total number of recovered was " + totalImmuneCount);
        System.out.println("Total number of dead was " + totalBodyCount);
    }

    private void infectNeighbours(Member member) {
        ArrayList<Member> neighbours = getNeighbours(member);

        for (Member neighbour : neighbours) {
            double y = random.nextDouble();
            //System.out.println("RANDOM is " + y);
            if (y < probOfInfection) {
                neighbour.setStatus(StatusType.INFECTED);
                illCount++;
                totalIllCount++;
                printMatrix();
            }
        }
    }

    private ArrayList<Member> getNeighbours(Member member) {
        ArrayList<Member> neighbours = new ArrayList<>();
        int x = member.getX();
        int y = member.getY();
        for (int j = y-1; j <= y + 1; j++)
            for (int i = x - 1; i <= x + 1; i++)
                if (coordAllowed(j, i) && matrix[j][i].getStatus().equals(StatusType.HEALTHY))
                    neighbours.add(matrix[j][i]);

        return neighbours;
    }

    private boolean coordAllowed(int j, int i) {
        return (j > -1 && j < matrix.length && i > -1 && i < matrix[0].length);
    }

    public Random random = new Random();

    public long[] seeds = {
            6116947, 6116291, 6116323, 6116371, 6116381, 6116401, 6116443, 6116489,
            6116491, 6116567, 6116573, 6116603, 6116623, 6116629, 6116633, 6116647,
            6116657, 6116659, 6116683, 6116687, 6116699, 6116711, 6116713, 6116717,
            6116731, 6116771, 6116779, 6116797, 6116801, 6116807, 6116813, 6116833,
            6116843, 6116867, 6116921, 6116927, 6116933, 6116947, 6116953, 6116963,
            6116977, 6116987, 6116989, 6117037, 6117053, 6117071, 6117073, 6117079,
            6117091, 6117109, 6117131, 6117151, 6117169, 6117193, 6117197, 6117203,
            6117239, 6117253, 6117269, 6117281, 6117299, 6117311, 6117337, 6117341,
            6117343, 6117361, 6117367, 6117383, 6117389, 6117401, 6117403, 6117409,
            6117437, 6117451, 6117491, 6117541, 6117557, 6117581, 6117583, 6117599,
            6117607, 6117613, 6117641, 6117647, 6117649, 6117667, 6117689, 6117697,
            6117721, 6117743, 6117763, 6117767, 6117779, 6117781, 6117817, 6117821,
            6117823, 6117833, 6117851, 6117863
    };

    public void setSeed(int i) {
        this.random.setSeed(seeds[i]);
    }
}
