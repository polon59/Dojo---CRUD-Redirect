import com.sun.net.httpserver.HttpExchange;  
import com.sun.net.httpserver.HttpHandler;  

import java.io.*;  
import java.net.URLDecoder;  
import java.util.HashMap;  
import java.util.Map;  
import java.util.List;

public class Form implements HttpHandler {  

    StudentList studentList = new StudentList();
    List<Student> students = studentList.getStudentList();

    FileParser fileParser = new FileParser();

    @Override  
    public void handle(HttpExchange httpExchange) throws IOException {  


        String response = "";  
        String method = httpExchange.getRequestMethod();  


        // Send a form if it wasn't submitted yet.  
        if(method.equals("GET")){  
            response = fileParser.getFile("resources/file/test.txt");
        }  

        // If the form was submitted, retrieve it's content.  
        if(method.equals("POST")){  
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");  
            BufferedReader br = new BufferedReader(isr);  
            String formData = br.readLine();  
            StringBuilder sb = new StringBuilder();

            System.out.println(formData);  
            Map inputs = parseFormData(formData);  

            String name = inputs.get("firstname").toString();
            String surname = inputs.get("lastname").toString();
            String ageString = inputs.get("age").toString();
            int age = Integer.parseInt(ageString);
            Student newStudent = new Student(name, surname, age);
            students.add(newStudent);
            studentList.importList(students);
            sb.append(
            "<html><body>" +  
            "<table border="+"1"+">"+
            "<tr>"+
               " <th>no.</th>"+
                "<th>FIRST NAME</th>"+
                "<th>LAST NAME</th>"+
                "<th>AGE</th>"+
            "</tr>");


            for (int i = 0; i < students.size(); i++){
                sb.append(
                    "<tr>"+
                    " <td>1</td>"+
                    "<td>"+ students.get(i).getName() + "</td>"+
                    "<td>"+ students.get(i).getSurname() + "</td>"+
                    "<td>"+ students.get(i).getAge() + "</td>"+
                    "</tr>");
            }
            
            response = sb.toString();

            
        }  

        httpExchange.sendResponseHeaders(200, response.length());  
        OutputStream os = httpExchange.getResponseBody();  
        os.write(response.getBytes());  
        os.close();  
    }  

    /**  
     * Form data is sent as a urlencoded string. Thus we have to parse this string to get data that we want.  
     * See: https://en.wikipedia.org/wiki/POST_(HTTP)  
     */  
    private static Map<String, String> parseFormData(String formData) throws UnsupportedEncodingException {  
        Map<String, String> map = new HashMap<>();  
        String[] pairs = formData.split("&");  
        for(String pair : pairs){  
            String[] keyValue = pair.split("=");  
            // We have to decode the value because it's urlencoded. see: https://en.wikipedia.org/wiki/POST_(HTTP)#Use_for_submitting_web_forms  
            String value = new URLDecoder().decode(keyValue[1], "UTF-8");  
            map.put(keyValue[0], value);  
        }  
        return map;  
    }  
}  

