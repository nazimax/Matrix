import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by kissi on 19/10/17.
 */
public class Parser {
    Matrix eigenVectors(int n) {
        double[][] matrix =new double[n][n];

        String FILENAME = "./eigenVectors";
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

            int line=0;
            String sCurrentLine;
            String[] vect = new String[n];
            while ((sCurrentLine = br.readLine()) != null) {


                String replace = sCurrentLine.replace("[", "");
                String replace1 = replace.replace("]", "");
                ArrayList<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(" ")));
                ArrayList<Double> newList = new ArrayList<Double>();
                for (int i = 0; i < myList.size(); i++) {
                    if (!myList.get(i).isEmpty()){

                        newList.add(Double.parseDouble(myList.get(i)));

                    }

                }

                for (int k = 0; k < n ; k++) {
                  matrix[line][k]=newList.get(k);
                }
                line++;


            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    return new Matrix(matrix);
    }



    double[] eigenValues(int n){

        double[] val=new double[n];

        String FILENAME = "./eigenValues";
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

            String sCurrentLine=br.readLine();
            ArrayList<String> myList = new ArrayList<String>(Arrays.asList(sCurrentLine.split(";")));
            ArrayList<Double> newList = new ArrayList<Double>();

            for (int i = 0; i <myList.size() ; i++) {
                if (!myList.get(i).isEmpty()){

                    newList.add(Double.parseDouble(myList.get(i)));

                }
            }


            for (int k = 0; k < n ; k++) {
                val[k]=newList.get(k);
            }


        } catch (IOException e) {
        e.printStackTrace();
    }


return val;
}
}
