import java.util.Scanner;

public class Adaline
{
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        float batasToleransi;
        float learningRate;
        int banyakData;
        int banyakX;

        System.out.print("Input banyak data : ");
        banyakData = scanner.nextInt();
        System.out.print("Input banyak x : ");
        banyakX = scanner.nextInt();
        System.out.print("Input nilai batas toleransi : ");
        batasToleransi = scanner.nextFloat();
        System.out.print("Input nilai learning rate : ");
        learningRate = scanner.nextFloat();
        System.out.println();

        // inisialisasi bobot dan bias
        float[] weight = new float[banyakX];
        float[] deltaWeight = new float[banyakX];
        float deltaBias = 0;
        for (int i = 0; i < banyakX; i++)
        {
            weight[i] = 0;
            deltaWeight[i] = 0;
        }
        float bias = 0;

        // tahap inputan
        int[][] x = new int[banyakData][banyakX];
        int[] target = new int[banyakData];

        for (int i = 0; i < banyakData; i++)
        {
            for (int j = 0; j < banyakX; j++)
            {
                System.out.print("Masukkan X-" + (j + 1) + " data ke-" + (i + 1) + "= ");
                x[i][j] = scanner.nextInt();
            }
            System.out.print("Masukkan T data ke-" + (i + 1 + "= "));
            target[i] = scanner.nextInt();
            System.out.println();
        }

        // tahap learning
        boolean deltaWLebihKecil = false;
        System.out.println("\nLearning");
        System.out.println("===========");
        int epoch = 1;

        while (!deltaWLebihKecil || epoch == 1)
        {
            System.out.println("Epoch ke-" + epoch);

            for (int i = 0; i < banyakX; i++)
            {
                System.out.printf("%-5s", "X" + (i + 1));
            }

            System.out.printf("%-15s ", "T");
            System.out.printf("%-15s", "F(X)");
            System.out.printf("%-15s", "Y");
            System.out.printf("%-15s", "T-Y");

            for (int i = 0; i < banyakX; i++)
            {
                System.out.printf("%-15s", "DW" + (i + 1));
            }

            System.out.printf("%-15s", "DB");

            for (int i = 0; i < banyakX; i++)
            {
                System.out.printf("%-15s", "W" + (i + 1));
            }

            System.out.printf("%-15s%n","b");

            float maxDeltaW = 0.0f;
            for (int i = 0; i < banyakData; i++)
            {
                // x
                for (int j = 0; j < banyakX; j++)
                {
                    System.out.printf("%-5d", x[i][j]);
                }

                // target
                System.out.printf("%-15d", target[i]);

                // fungsi summary
                float fungsiSummary = 0;
                for (int j = 0; j < banyakX; j++)
                {
                    fungsiSummary += weight[j] * (x[i][j]);
                }
                fungsiSummary += bias;
                System.out.printf("%-15f", fungsiSummary);


                // y
                int y;
                if (fungsiSummary >= 0)
                {
                    y = 1;
                }
                else
                {
                    y = -1;
                }
                System.out.printf("%-15d", y);

                // t-y
                int tMinY = target[i] - y;
                System.out.printf("%-15d", tMinY);

                // dw
                for (int j = 0; j < banyakX; j++)
                {
                    deltaWeight[j] =  (learningRate * tMinY * x[i][j]);
                    System.out.printf("%-15f", deltaWeight[j]);
                    if(maxDeltaW < deltaWeight[j])
                    {
                        maxDeltaW = deltaWeight[j];
                    }
                }

                // db
                deltaBias = (learningRate * tMinY);
                System.out.printf("%-15f", deltaBias);

                // w
                for (int j = 0; j < banyakX; j++)
                {
                    if (tMinY == 0)
                    {
                        System.out.printf("%-15f", weight[j]);
                    }
                    else
                    {
                        weight[j] += deltaWeight[j];
                        System.out.printf("%-15f", weight[j]);
                    }
                }


                // b
                if (tMinY == 0)
                {
                    System.out.printf("%-15f%n", bias);
                }
                else
                {
                    bias += deltaBias;
                    System.out.printf("%-15f%n", bias);
                }

            }

            System.out.println();
            for (int i = 0; i < banyakX; i++)
            {
                System.out.println("W" + (i + 1) + "= " + weight[i]);
            }
            System.out.println("B = " + bias);
            System.out.println();

            epoch++;
            if(batasToleransi > maxDeltaW) {
                deltaWLebihKecil = true;
            }
        }

        // tahap testing
        System.out.println();
        System.out.println("Testing");
        System.out.println("===========");
        int[] xTesting = new int[banyakX];
        float fungsiSummary = 0;
        for (int i = 0; i < banyakX; i++)
        {
            System.out.print("Masukkan X" + (i + 1) + " = ");
            xTesting[i] = scanner.nextInt();
        }

        for (int i = 0; i < banyakX; i++)
        {
            fungsiSummary += xTesting[i] * weight[i];
        }
        fungsiSummary += bias;

        int out = 0;
        if (fungsiSummary >= 0)
        {
            out = 1;;
        }
        else if (fungsiSummary < 0)
        {
            out = -1;
        }
        System.out.println("Fungsi summary adalah = " + fungsiSummary);
        System.out.print("Output yang diperoleh adalah Y = " + out);
    }
}
