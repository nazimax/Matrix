import java.util.ArrayList;

/**
 * Created by kissi on 05/10/17.
 */
public class Matrix {


    private int lineLenght, columnLenght;
    private double[][] matrix;


    public Matrix() {


    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public Matrix(double[][] M) {

        matrix = M;
        if (testSize(M)) {
            lineLenght = M.length;
            columnLenght = M[0].length;
        }


    }

    double columnMoy(double[] col) {
        double moy = 0;

        for (int i = 0; i < col.length; i++) {
            moy += col[i];
        }
        return moy / col.length;
    }

    Matrix centredMatrix() {
        double[][] m = this.matrix;
        double[] tempCol;
        double moy;
        for (int i = 0; i < this.columnLenght; i++) {
            tempCol = this.columnOfMatrix(i);
            moy = columnMoy(tempCol);
            for (int j = 0; j < tempCol.length; j++) {
                tempCol[j] = tempCol[j] - moy;
            }
            this.setColumnOfMatrix(i, tempCol);

        }


        return this;
    }


    Matrix reduceCentredMatrix() {
        double variance, sigma;

        for (int i = 0; i < this.columnLenght; i++) {
            variance = varianceOfColumn(this.columnOfMatrix(i));
            sigma = Math.sqrt(variance);
            double[] col;
            col = this.columnOfMatrix(i);
            for (int j = 0; j < this.lineLenght; j++) {
                col[j] = col[j] / sigma;
            }
            this.setColumnOfMatrix(i, col);
        }

        return this;
    }

    double varianceOfColumn(double[] col) {

        double v = 0;
        for (int i = 0; i < col.length; i++) {
            v += Math.pow(col[i], 2);
        }

        return v / col.length;
    }


// get transposed of matrix


    private Matrix transposedMatrix() {

        double[][] transposed = new double[columnLenght][lineLenght];
        double[] v = new double[lineLenght];
        for (int i = 0; i < columnLenght; i++) {
            transposed[i] = this.columnOfMatrix(i);
        }

        return new Matrix(transposed);
    }

    // multiply a matrix by a scalar
    private Matrix productScalairMatrix(double r) {

        double[][] mat = this.matrix;
        for (int i = 0; i < this.lineLenght; i++) {
            for (int j = 0; j < this.columnLenght; j++) {
                mat[i][j] = r * mat[i][j];
            }
        }

        return new Matrix(mat);
    }


    public int getLineLenght() {
        return lineLenght;
    }

    public void setLineLenght(int lineLenght) {
        this.lineLenght = lineLenght;
    }

    public int getColumnLenght() {
        return columnLenght;
    }

    public void setColumnLenght(int columnLenght) {
        this.columnLenght = columnLenght;
    }


    private boolean testSize(double[][] M) {
        int line = M.length;
        int column = M[0].length;
        for (int i = 1; i < line; i++) {
            if (M[1].length != column) {
                return false;
            }
        }
        return true;
    }

    // verify propreties before multiplying two matrix
    private boolean productIsPossible(Matrix a, Matrix b) {
        return (a.getColumnLenght() == b.getLineLenght());

    }

    private double scalarProduct(double[] a, double[] b) {
        double ps = 0;
        for (int i = 0; i < a.length; i++) {
            ps += a[i] * b[i];
        }
        return ps;
    }

    Matrix identity(int n) {
        double i[][] = new double[n][n];
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {

                if (j == k) {
                    i[j][j] = 1;
                } else {
                    i[j][k] = 0;
                }
            }

        }

        return new Matrix(i);
    }


    void setColumnOfMatrix(int index, double[] column) {
        double mat[][] = this.matrix;
        for (int i = 0; i < this.lineLenght; i++) {
            mat[i][index] = column[i];
        }
        this.matrix = mat;

    }

    // get a column i from a matric
    double[] columnOfMatrix(int index) {
        ArrayList<Double> column = new ArrayList<Double>();
        for (int j = 0; j < this.lineLenght; j++) {
            column.add(this.matrix[j][index]);
        }
        double T[] = new double[column.size()];
        for (int i = 0; i < column.size(); i++) {
            //System.out.print(column.get(i)+"\n");
            T[i] = column.get(i);
        }

        return T;


    }


    Matrix productTwoMatrix(Matrix b) {
        double[][] m = new double[this.lineLenght][b.columnLenght];

        if (productIsPossible(this, b)) {

            for (int i = 0; i < this.lineLenght; i++) {
                for (int j = 0; j < b.columnLenght; j++) {

                    m[i][j] = scalarProduct(this.matrix[i], b.columnOfMatrix(j));
                    // System.out.println(this.metrix[i]+"X"+b.columnOfMetrix(j));
                }

            }
        }

        return new Matrix(m);
    }

    Matrix varCorrelationOfReducedMatrix() {

        Matrix m1 = this;
        double r = (double) 1 / m1.lineLenght;
        Matrix m2 = m1.transposedMatrix();
        Matrix m3 = m2.productTwoMatrix(m1);


        return m3.productScalairMatrix(r);
    }


    Matrix individuCorrelationOfReducedMatrix() {

        Matrix m1 = this;
        //double r= (double)1/m1.lineLenght;
        Matrix m2 = m1.transposedMatrix();
        Matrix m3 = m1.productTwoMatrix(m2);


        return m3;//.productScalairMatrix(r);
    }

    Matrix correlationMatrix() {
        Matrix m1 = this.centredMatrix();
        m1 = m1.reduceCentredMatrix();


        return m1;
    }


    double productMatrixByVector(int lineNumber, double vector[]) {
        double[][] mat = this.matrix;
        double rs = 0;
        for (int i = 0; i < columnLenght; i++) {
            rs += mat[lineNumber][i] * vector[i];

        }
        return -rs;
    }


    Matrix fatcor(double[]... v) {


        double m[][] = new double[lineLenght][v.length];

        int j = -1;
        for (double[] vector : v) {
            j++;
            for (int i = 0; i < lineLenght; i++) {
                m[i][j] = this.productMatrixByVector(i, vector);
            }
        }


        return new Matrix(m);
    }

    Matrix individualContribution(Matrix factors, double... eigenValu) {
        double[][] factor = factors.matrix;
        int j = -1;
        for (double valu : eigenValu) {
            double n = lineLenght * valu;
            j++;
            for (int i = 0; i < lineLenght; i++) {
                factor[i][j] = (Math.pow(factors.matrix[i][j], 2) / n) * 100;
            }

        }

        return new Matrix(factor);
    }

    double someSquerVector(double[] v) {
        double rs = 0;
        for (int i = 0; i < v.length; i++) {
            rs += Math.pow(v[i], 2);
        }
        return rs;
    }

    Matrix qualityOfRepresentation(Matrix factors) {
        double[][] quality = new double[factors.matrix.length][factors.matrix[0].length];

        double val;
        for (int i = 0; i < quality.length; i++) {

            val = 0;
            for (int j = 0; j < quality[0].length; j++) {
                quality[i][j] = Math.pow(factors.matrix[i][j], 2) / someSquerVector(factors.matrix[i]);
            }


        }

        return new Matrix(quality);
    }


    double coordVect(double v1[],double v2[], double lamda){
        double rs=0;
        for (int i = 0; i <v1.length ; i++) {
            rs+=v1[i]*v2[i];
        }
        double result=rs/(Math.sqrt(lamda)*v1.length);
        return result;
    }

    
    Matrix coordFactorielOfVariable(Matrix mReduced,Matrix mFactors,double ... eigenValues){
        int numColumns=eigenValues.length;
        double[][] matCoord=new double[mReduced.matrix[0].length][numColumns];
        int j=-1;
        for (double valu : eigenValues){
            j++;
            for (int i = 0; i <mReduced.matrix[0].length ; i++) {
                matCoord[i][j]=coordVect(mReduced.columnOfMatrix(i),mFactors.columnOfMatrix(j),valu);
            }
            
            
        }
        return new Matrix(matCoord);
    }
    Matrix contributionOfVariables(Matrix m,double ...eigenValu){
        double[][] coordFactorial = new double[m.matrix.length][m.matrix[0].length];

        int j=-1;
        for (double valu:eigenValu){
            j++;
            for (int i = 0; i <coordFactorial.length ; i++) {
                coordFactorial[i][j]=Math.pow(m.matrix[i][j],2)/valu;
            }
        }



        return new Matrix(coordFactorial);
    }

    Matrix qualityOfVariables(Matrix factors) {
        double[][] quality = new double[factors.matrix.length][factors.matrix[0].length];

        double val;
        for (int i = 0; i < quality.length; i++) {

            val = 0;
            for (int j = 0; j < quality[0].length; j++) {
                quality[i][j] = Math.pow(factors.matrix[i][j], 2) / someSquerVector(factors.matrix[i]);
            }


        }

        return new Matrix(quality);
    }

    double[] somOfTwoColumns(double v1[],double v2[]){

        double[]result=new double[v1.length];

        for (int i = 0; i <v1.length ; i++) {
            result[i]=v1[i]+v2[i];
        }


        return result;
    }



    double[][] setCol(double[][] m,int index,double[] col){
        Matrix m1=new Matrix();
        m1.matrix=m;
        m1.setColumnOfMatrix(index,col);
        return m1.matrix;

    }

    @Override
    public String toString() {
        String affich = "";
        for (int i = 0; i < this.lineLenght; i++) {
            for (int j = 0; j < this.columnLenght; j++) {
                affich += matrix[i][j] + " |";
            }
            affich += "\n";
        }
        return affich;
    }
    String pythonFormat(){
        double[][] matrix=this.matrix;
        String affich = "";
        for (int i = 0; i < this.lineLenght; i++) {
            for (int j = 0; j < this.columnLenght; j++) {
                affich += matrix[i][j] + " ";
            }
            if(i<this.lineLenght-1)
                affich += ";";
        }
        return affich;

    }


}
