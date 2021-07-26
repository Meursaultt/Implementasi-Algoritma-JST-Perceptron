import java.util.Scanner;

public class Main
{
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        int banyakData;
        int banyakX;
        double nilaiThreshold;
        int alpha = 1;

        System.out.print("Input banyak data : ");
        banyakData = scanner.nextInt();
        System.out.print("Input banyak x : ");
        banyakX = scanner.nextInt();
        System.out.print("Input nilai threshold : ");
        nilaiThreshold = scanner.nextDouble();
        System.out.print("Input nilai alpha(default 1) : ");
        alpha = scanner.nextInt();
        System.out.println();

        // inisialisasi bobot dan bias
        int[] weight = new int[banyakX];
        for (int w : weight)
        {
            w = 0;
        }
        int bias = 0;

        // inputan
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
        System.out.println("\nLearning");
        System.out.println("===========");
        int accuration = 0;

        int epoch = 1;
        while (accuration < banyakData)
        {
            System.out.println("Epoch ke-" + epoch);

            for (int i = 0; i < banyakX; i++)
            {
                System.out.print("X" + (i + 1) + "\t");
            }

            System.out.print("T" + "\t");
            System.out.print("F(X)" + "\t");
            System.out.print("Out" + "\t");
            System.out.print("Akur" + "\t");

            for (int i = 0; i < banyakX; i++)
            {
                System.out.print("W" + (i + 1) + "\t");
            }

            System.out.println("b");

            for (int i = 0; i < banyakData; i++)
            {
                for (int j = 0; j < banyakX; j++)
                {
                    System.out.print(x[i][j] + "\t");
                }

                System.out.print(target[i] + "\t");

                // fungsi summary
                int fungsiSummary = 0;
                for (int j = 0; j < banyakX; j++)
                {
                    fungsiSummary += weight[j] * (x[i][j]);
                }
                fungsiSummary += bias;
                System.out.print(fungsiSummary + "\t\t");

                // cek output
                int out = 0;
                if (fungsiSummary > nilaiThreshold)
                {
                    out = 1;
                }
                else if (fungsiSummary >= -nilaiThreshold && fungsiSummary <= nilaiThreshold)
                {
                    out = 0;
                }
                else if (fungsiSummary < -nilaiThreshold)
                {
                    out = -1;
                }
                System.out.print(out + "\t");

                // akurasi
                boolean outputBenar;
                if (out == target[i])
                {
                    accuration += 1;
                    System.out.print("1" + "\t\t");
                    outputBenar = true;
                }
                else
                {
                    System.out.print("0" + "\t\t");
                    outputBenar = false;
                }

                // nilai w
                for (int j = 0; j < banyakX; j++)
                {
                    if (outputBenar)
                    {
                        System.out.print(weight[j] + "\t");
                    }
                    else
                    {
                        weight[j] += (alpha * x[i][j] * target[i]);
                        System.out.print(weight[j] + "\t");
                    }
                }

                // bias
                if (outputBenar)
                {
                    System.out.println(bias);
                }
                else
                {
                    bias += (alpha * target[i]);
                    System.out.println(bias);
                }


            }

            System.out.println();
            for (int i = 0; i < banyakX; i++)
            {
                System.out.println("W" + (i + 1) + "= " + weight[i]);
            }
            System.out.println("B = " + bias);

            System.out.println();
            epoch += 1;
        }

        // tahap testing
        System.out.println();
        System.out.println("Testing");
        System.out.println("===========");
        int[] xTesting = new int[banyakX];
        int fungsiSummary = 0;
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
        if (fungsiSummary > nilaiThreshold)
        {
            out = 1;
        }
        else if (fungsiSummary >= -nilaiThreshold && fungsiSummary <= nilaiThreshold)
        {
            out = 0;
        }
        else if (fungsiSummary < -nilaiThreshold)
        {
            out = -1;
        }
        System.out.print("Output yang diperoleh adalah Y = " + out);
    }
}
