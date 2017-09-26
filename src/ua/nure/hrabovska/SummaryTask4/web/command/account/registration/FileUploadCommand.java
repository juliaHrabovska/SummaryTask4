package ua.nure.hrabovska.SummaryTask4.web.command.account.registration;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ua.nure.hrabovska.SummaryTask4.database.dao.EnrolleeDAO;
import ua.nure.hrabovska.SummaryTask4.database.entity.Enrollee;
import ua.nure.hrabovska.SummaryTask4.exception.Message;
import ua.nure.hrabovska.SummaryTask4.web.Path;
import ua.nure.hrabovska.SummaryTask4.web.RequestProperty;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * File upload command
 *
 * @author Y. Hrabovska
 *
 */
public class FileUploadCommand extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(FileUploadCommand.class);

    private static final String UPLOAD_DIRECTORY = "D:/Учеба/PracticeEpam/SummaryTasks/SummaryTask4_1/SummaryTask4/web/images";

    private static final String ABSOLUTE_PATH = "images\\";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("Command start");

        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);

                LOG.debug("Get list of file item: " + multiparts);
                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        String file_name = item.getName();
                        LOG.debug("File name: " + file_name);

                        if (file_name.endsWith(".jpg") || file_name.endsWith(".bmp") || file_name.endsWith(".gif")) {
                            String type = file_name.substring(file_name.lastIndexOf('.'));
                            HttpSession session = request.getSession();
                            Enrollee enrollee = (Enrollee) session.getAttribute("enrollee");

                            String name = String.valueOf(enrollee.getId());
                            LOG.debug("Enrollee id is " + name);

                            file_name = name + type;
                            LOG.debug("New file name: " + file_name);

                            item.write(new File(UPLOAD_DIRECTORY + File.separator + file_name));

                            EnrolleeDAO enrolleeDAO = new EnrolleeDAO();
                            if (enrolleeDAO.insertCertificatePath((ABSOLUTE_PATH + file_name), enrollee.getId())) {

                                enrollee = enrolleeDAO.getById(enrollee.getId());
                                session.setAttribute("enrollee", enrollee);
                                LOG.trace("Set the session attribute: enrollee --> " + enrollee);

                                LOG.debug("Command completed successfully");
                                LOG.debug("Redirect: " + "showExamList");
                                response.sendRedirect("/controller?command=showExamList");
                            }

                        } else {
                            request.setAttribute(RequestProperty.ERROR, Message.NOT_VALID_TYPE_FILE);
                            LOG.debug("Command is completed with error");
                            LOG.debug("Forward: " + Path.UPLOAD_FILE_PAGE);
                            request.getRequestDispatcher(Path.UPLOAD_FILE_PAGE).forward(request, response);
                        }
                    }
                }
            } catch (Exception e) {
                request.setAttribute(RequestProperty.ERROR, Message.CANNOT_UPLOAD_FILE);
                LOG.debug("Command is completed with error");
                LOG.debug("Forward: " + Path.UPLOAD_FILE_PAGE);
                request.getRequestDispatcher(Path.UPLOAD_FILE_PAGE).forward(request, response);
            }
        }
    }

//    public PageData execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, DBException {
//
//        LOG.debug("Command start");
//
//        if (ServletFileUpload.isMultipartContent(request)) {
//            try {
//                List<FileItem> multiparts = new ServletFileUpload(
//                        new DiskFileItemFactory()).parseRequest(request);
//
//                for (FileItem item : multiparts) {
//                    if (!item.isFormField()) {
//                        String name = new File(item.getName()).getName();
//                        item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
//                    }
//                }
//
//                LOG.debug("Command completed successfully");
//                return new PageData(Path.PERSONAL_AREA_CLIENT, true);
//            } catch (Exception e) {
//                request.setAttribute(RequestProperty.ERROR, Message.CANNOT_UPLOAD_FILE);
//            }
//        }
//        LOG.debug("Command is completed with error");
//        return new PageData(Path.UPLOAD_FILE_PAGE, true);
//    }
}
