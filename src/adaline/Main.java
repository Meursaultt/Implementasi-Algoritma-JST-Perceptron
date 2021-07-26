package adaline;

public class Main
{
    public static void main(String[] args)
    {
        float learningRate = 0.1f;
        float toleransi = 0.05f;
        int[][] x = {
                {1, 1},
                {1, -1},
                {-1, 1},
                {-1, -1}
        };

        int[] target = {1, -1, -1, -1};

        Adaline adaline = new Adaline(learningRate,toleransi,x,target);
        adaline.learning();

    }
}
