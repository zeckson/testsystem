package ru.ifmo.qd.testing.server;

import com.google.gwt.user.client.ui.FileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: May 29, 2010
 * Time: 5:01:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class DownloadService extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stat = request.getParameter("stat");
        if (checkedStringEquals(stat, "subject", false)) {
            String stringId = request.getParameter("id");
            if (!(stringId == null || request.getParameter("id").equals(""))) {
                int id = 0;
                try {
                    id = Integer.parseInt(stringId);
                } catch (NumberFormatException ex) {
                    return;
                }

                writeFileDownloadHeader(response, "statistics(" + stringId + ").csv");
                ServletOutputStream outputStream = response.getOutputStream();
                new AdministrationServiceImpl().writeStatisticinCSV(id, outputStream);
                outputStream.flush();
                outputStream.close();
            }
        } else if (checkedStringEquals(stat, "test", false)) {
            writeFileDownloadHeader(response, "test_results.zip");

            //create temp file and write zip there
            File tempFile = File.createTempFile("test_results", ".zip");
            OutputStream zip = new FileOutputStream(tempFile);
            new AdministrationServiceImpl().writeCompressedTestResults(zip);
            zip.close();

            //Copy created file to output stream
            ServletOutputStream outputStream = response.getOutputStream();
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(tempFile));

            int data = in.read();
            while (data > -1){
                outputStream.write(data);
                data = in.read();
            }

            outputStream.flush();
            outputStream.close();

            //Clean zip file
            if(!(tempFile.delete())) tempFile.deleteOnExit();
        }
    }

    private void writeFileDownloadHeader(HttpServletResponse response, String fileName) {
        response.setContentType("application/octet-stream");
//                response.setContentLength((int) f.length());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
    }

    private boolean checkedStringEquals(String left, String right, boolean contains) {
        if (left == null) return false;
        if (right == null) return false;
        if (contains) return left.toLowerCase().contains(right.toLowerCase());
        return left.equalsIgnoreCase(right);
    }
}
