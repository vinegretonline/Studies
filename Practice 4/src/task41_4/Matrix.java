package task41_4;

public class Matrix {

    private double[][] data;
    private int rows;
    private int cols;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new double[rows][cols];
    }

    public Matrix(double[][] data) {
        this.data = data;
        rows = data.length;
        cols = data[0].length;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public double get(int row, int col) {
        return data[row][col];
    }

    public void set(int row, int col, double value) {
        data[row][col] = value;
    }

    public Matrix add(Matrix other) {
        if (rows != other.getRows() || cols != other.getCols()) {
            System.out.println("Размеры матриц не совпадают, сложение невозможно");
            return null;
        }

        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.set(i, j, data[i][j] + other.get(i, j));
            }
        }
        return result;
    }

    public Matrix multiply(double number) {
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.set(i, j, data[i][j] * number);
            }
        }
        return result;
    }

    public Matrix multiply(Matrix other) {
        if (cols != other.getRows()) {
            System.out.println("Число столбцов первой матрицы не равно числу строк второй");
            return null;
        }

        Matrix result = new Matrix(rows, other.getCols());
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < other.getCols(); j++) {
                double sum = 0;
                for (int k = 0; k < cols; k++) {
                    sum = sum + data[i][k] * other.get(k, j);
                }
                result.set(i, j, sum);
            }
        }
        return result;
    }

    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("%8.2f", data[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Matrix a = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}});
        Matrix b = new Matrix(new double[][]{{6, 5, 4}, {3, 2, 1}});

        System.out.println("Матрица A:");
        a.print();
        System.out.println("Матрица B:");
        b.print();

        System.out.println("A + B:");
        a.add(b).print();

        System.out.println("A * 2.5:");
        a.multiply(2.5).print();

        Matrix c = new Matrix(new double[][]{{1, 2}, {3, 4}, {5, 6}});
        System.out.println("Матрица C:");
        c.print();
        System.out.println("A * C:");
        a.multiply(c).print();
    }
}
