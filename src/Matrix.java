import java.util.ArrayList;

/**
 * Created by kissi on 05/10/17.
 */
public class Matrix {



    private int lineLenght, columnLenght;
    private double[][] matrix;


    public Matrix(double[][] M) {

        matrix = M;
        if (testSize(M)) {
            lineLenght = M.length;
            columnLenght = M[0].length;
        }


    }
// get transposed of matrix
    private Matrix transposedMatrix(){

        double[][] transposed=new double[columnLenght][lineLenght];
        double []v=new double[lineLenght];
        for (int i = 0; i <columnLenght ; i++) {
            transposed[i]=this.columnOfMatrix(i);
        }
        
        return new Matrix(transposed);
    }
    // multiply a matrix by a scalar
    private Matrix productScalairMatrix(double r){

        double[][] mat=this.matrix;
        for (int i = 0; i <this.lineLenght ; i++) {
            for (int j = 0; j <this.columnLenght ; j++) {
                mat[i][j]=r*mat[i][j];
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
    
    Matrix identity(int n){
        double i[][]=new double[n][n];
        for (int j = 0; j < n; j++) {
            for (int k = 0; k <n ; k++) {

                if(j==k){
                    i[j][j]=1;
                }
                else{
                    i[j][k]=0;
                }
            }

        }

        return new Matrix(i);
    }
 // get a column i from a matric
    double[] columnOfMatrix(int index){
    ArrayList<Double>column=new ArrayList<Double>();
        for (int j = 0; j <this.lineLenght ; j++) {
            column.add(this.matrix[j][index]);
        }
        double T[]=new double[column.size()];
        for (int i = 0; i < column.size(); i++) {
        //System.out.print(column.get(i)+"\n");
            T[i]=column.get(i);
    }

        return T;


}


    Matrix productTwoMatrix(Matrix b) {
        double[][] m = new double[this.lineLenght][b.columnLenght];

        if (productIsPossible(this, b)) {

            for (int i = 0; i < this.lineLenght; i++) {
                for (int j = 0; j <b.columnLenght ; j++) {

                    m[i][j]=scalarProduct(this.matrix[i],b.columnOfMatrix(j));
                   // System.out.println(this.metrix[i]+"X"+b.columnOfMetrix(j));
                }

            }
        }

    return new Matrix(m);
    }

    Matrix correlationMatrix(){
        Matrix m1=this;
        double r= (double)1/m1.lineLenght;
        Matrix m2=m1.transposedMatrix();
        Matrix m3 = m2.productTwoMatrix(m1);


        return m3.productScalairMatrix(r);
    }


    @Override
    public String toString() {
        String affich="";
        for (int i = 0; i <this.lineLenght ; i++) {
            for (int j = 0; j <this.columnLenght ; j++) {
                affich+=matrix[i][j]+" |";
            }
            affich+="\n";
        }
        return affich;
    }
}
