package ua.nure.hrabovska.SummaryTask4.web.command;

/**
 * Contains address of a page
 *
 * @author Y. Hrabovska
 *
 */
public class PageData {

    private String path;
    private boolean isForward;
    /**
     * Constructs new PageData object
     *
     * @param path
     *            path of the web page
     * @param isForward
     *            true if user must be forwarded to the page, false if must be
     *            redirected
     */
    public PageData(String path, boolean isForward) {
        this.path = path;
        this.isForward = isForward;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isForward() {
        return isForward;
    }

    public void setForward(boolean isForward) {
        this.isForward = isForward;
    }


}
