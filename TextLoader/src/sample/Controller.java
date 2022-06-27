package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.io.*;


public class Controller {
    @FXML
    private BorderPane borderPane;
    @FXML
    private Accordion accordion;

    @FXML
    public void choseFile(){
        FileChooser fileChooser=new FileChooser();
        accordion.getPanes().clear();

        File file=fileChooser.showOpenDialog(borderPane.getTop().getScene().getWindow());

         if (file.length() > 1000000){
             System.out.println("Choose file less then 1MB.");
             return;
         }


        try (BufferedReader bufferedReader=new BufferedReader(new FileReader(file))){
            if (file!=null){
                String str;
                int count=1;
                StringBuilder stringBuilder=new StringBuilder();

                while ((str=bufferedReader.readLine()) != null){

                    if (str.charAt(0) != '#'){
                            stringBuilder.append(str);
                            stringBuilder.append("\n");
                    }else if (str.charAt(0) == '#') {

                        if (count != 1){
                            addTitlePane(stringBuilder.substring(1).toString());
                        }


                            stringBuilder.replace(0, stringBuilder.length(),str);
                        }
                    ++count;
                }
                addTitlePane(stringBuilder.substring(1).toString());

            }else {
                System.out.println("File not chosen.");
            }
        }catch (FileNotFoundException fnf){
            fnf.getStackTrace();
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    public TitledPane addTitlePane(String str){
        TitledPane tp=new TitledPane();
        TextArea textArea=new TextArea(str);
        tp.setContent(textArea);
        accordion.getPanes().add(tp);

        return tp;
    }


}
