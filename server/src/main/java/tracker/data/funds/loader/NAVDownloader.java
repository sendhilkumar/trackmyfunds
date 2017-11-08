package tracker.data.funds.loader;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;

public class NAVDownloader {
    private Logger logger = LoggerFactory.getLogger(NAVDownloader.class.getName());

    public void downloadYear(String year) throws IOException {
        String from = "01-Jan-" + year;
        String to = "31-Dec-" + year;
        download(from, to);
    }

    public File download(String from, String to) throws IOException {
        URL url = new URL("http://portal.amfiindia.com/DownloadNAVHistoryReport_Po.aspx?tp=1&frmdt=" + from + "&todt=" + to);
        logger.info(url.toString());
        File file = new File(from + "_" + to + ".txt");
        FileUtils.copyURLToFile(url, file);
        return file;
    }

    public File downloadCurrent() throws IOException {
        URL url = new URL("https://www.amfiindia.com/spages/NAVOpen.txt");
        logger.info("Downloading " + url);
        String fileName = "NAVOpen_" + LocalDate.now().toString();
        File file = Files.createTempFile(fileName,"txt").toFile();
        FileUtils.copyURLToFile(url, file);
        logger.info("Downloaded " + file.toString());
        return file;
    }
}
