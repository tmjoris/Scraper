import java.io.*;
import java.net.*;
import java.util.Random;

public class scraper {

    private static String readInputStreamToString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }

    

    public static void main(String[] args){

        //the file name generator where the source code would be stored you can remove this if you already have one in mind
       
            int leftLimit = 97; // ASCII code for letter 'a'
            int rightLimit = 122; // ASCII code for letter 'z'
            int targetStringLength = 7;
            Random random = new Random();
        
            String fileName = random.ints(leftLimit, rightLimit + 1)
              .limit(targetStringLength)
              .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
              .toString();
        
String pageFile = fileName+".txt";   //You may change the extension to .html but it will end up creating a local copy of the webpage instead of giving you the F12 entire source code 
        

        try{

                                 //you can replace the url with any other desired site
            URI proxy = new URI("https://www.geeksforgeeks.org/understanding-encapsulation-inheritance-polymorphism-abstraction-in-oops/");
            URL geeksForGeeks = proxy.toURL();
        
            HttpURLConnection connection = (HttpURLConnection)geeksForGeeks.openConnection();

            if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){

                InputStream stream = connection.getInputStream();

                String pageContent = readInputStreamToString(stream);
            
//It would now write the data to your file and the file would be located in the working directory that is displayed in the terminal of your IDE
                try{
                    FileWriter codeWriter = new FileWriter(""+pageFile);
                    codeWriter.write(pageContent);

                    codeWriter.close();

                } catch(IOException e){
                    e.printStackTrace();
                }

                System.out.println("The site's code has been saved to "+pageFile);

                stream.close();

            } else{
                System.out.println("Failed to fetch URL. Response code: "+connection.getResponseCode());
            }

        }catch(IOException | URISyntaxException e){
        e.printStackTrace();

        }  
    }
    
}
