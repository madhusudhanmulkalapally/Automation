package ui.core;

import com.saucelabs.saucerest.BuildUtils;
import com.saucelabs.saucerest.SauceREST;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.pojava.datetime.DateTime;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import java.io.*;
import java.net.*;
//import org.junit.jupiter.api.io.TempDir;

import java.rmi.UnexpectedException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simple TestNG test which demonstrates being instantiated via a DataProvider in order to supply multiple browser combinations.
 *
 * @author Neil Manvar
 */
public class BaseTest  {
    private static final long HTTP_READ_TIMEOUT_SECONDS = TimeUnit.SECONDS.toMillis(10);
    private String server;
    private String edsServer;
    private static final Logger logger = Logger.getLogger(SauceREST.class.getName());
    private static final String DATE_FORMAT = "yyyyMMdd_HHmmSS";
    private static String extraUserAgent = "";
    protected String accessKey;
    private static final String BASE_URL;
    private static final String BASE_EDS_URL;
    private static final long HTTP_CONNECT_TIMEOUT_SECONDS = TimeUnit.SECONDS.toMillis(10);

    //public String buildTag = System.getenv("BUILD_TAG");
   // public String buildTag = System.getenv("BUILD_TAG");

    //public String username = System.getenv("oauth-pavanimulkalapally-83b21");
    public String username = "oauth-pavanimulkalapally-83b21";

    //public String accesskey = System.getenv("c8deacc4-90ef-4a20-8d2e-320a0c1917b2");
    public String accesskey = "c8deacc4-90ef-4a20-8d2e-320a0c1917b2";
    String sessionid=null;
    private SauceREST sauceREST;


    /**
     * ThreadLocal variable which contains the  {@link WebDriver} instance which is used to perform browser interactions with.
     */
    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    /**
     * ThreadLocal variable which contains the Sauce Job Id.
     */
    private ThreadLocal<String> sessionId = new ThreadLocal<String>();
//    @DataProvider(name = "hardCodedBrowsers", parallel = true)
//    public static Object[][] sauceBrowserDataProvider(Method testMethod) {
//        return new Object[][]{
//                new Object[]{"MicrosoftEdge", "18.17763", "Windows 10"},
//                new Object[]{"firefox", "latest", "Windows 10"},
//                new Object[]{"internet explorer", "11.0", "Windows 8.1"},
//                new Object[]{"safari", "12", "macOS 10.13"},
//                new Object[]{"chrome", "latest", "macOS 10.13"},
//                new Object[]{"firefox", "latest-1", "Windows 10"},
//        };
//    }

    /**
     * @return the {@link WebDriver} for the current thread
     */
    public WebDriver getWebDriver() {
        return webDriver.get();
    }

    /**
     *
     * @return the Sauce Job id for the current thread
     */
    public String getSessionId() {
        return sessionId.get();
    }

    protected void createDriver()
            throws MalformedURLException, UnexpectedException {
        System.out.println("sauce lab execution started==============================");
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("version", "latest");
        caps.setCapability("name", "saucelabtest");
        caps.setCapability("extendedDebugging", "true");
        //===================================================
//        MutableCapabilities caps = new MutableCapabilities();
//        caps.setCapability("sauce:appiumVersion", "1.20.2");
//        caps.setCapability("appium:deviceName", "Google Pixel 3a GoogleAPI Emulator");
//        caps.setCapability("appium:platformVersion", "11.0");
//        caps.setCapability("platformName","Android");
//        caps.setCapability("browserName", "Chrome");

        //================================================
//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability("username", "SAUCE_USERNAME");
//        caps.setCapability("accessKey", "SAUCE_ACCESS_KEY");
//        caps.setCapability("deviceName","Samsung.*Galaxy.*");
//        caps.setCapability("orientation", "portrait");
//        caps.setCapability("browserName", "chrome");
//        caps.setCapability("platformVersion","8.1");
//        caps.setCapability("platformName","Android");
        //caps.setCapability("app", "storage:filename=<file-name>");
        webDriver.set(new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + "@ondemand.eu-central-1.saucelabs.com:443/wd/hub"), caps));

        // set current sessionId
        sessionid = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
        sessionId.set(sessionid);
        System.out.println("sessionid:======="+sessionid);
        sauceREST = new SauceREST(username, accesskey);
    }
    @AfterMethod
    public void tearDown(ITestResult result) throws Exception {
        //createFolder();
        ((JavascriptExecutor) webDriver.get()).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
//        sauceREST = new SauceREST(username, accesskey);
//        sauceREST.downloadLog(sessionid, "/target/saucelab");
        this.downloadLog(sessionid, "F:\\SauceLog\\");
        System.out.println("test");

        webDriver.get().quit();
        //
//        sauceREST.
//        Map<String, Object> update = new HashMap<String, Object>();
//        sauceREST.updateJobInfo(sessionid, update);
        System.out.println("s");
    }

    protected void annotate(String text) {
        ((JavascriptExecutor) webDriver.get()).executeScript("sauce:context=" + text);
    }

//    @Deprecated boolean downloadLog1(String jobId, String location) {
//        //sauceREST.do
//        return downloadLog1(jobId, location);
//    }

    public void createFolder() {
        String dateTime = new DateTime().toString("dd-MM-yy HH:mm:ss");
        File f = new File("C:\\temp\\" + dateTime);
        f.mkdir();
    }
    protected URL buildURL(String endpoint) {
        try {
            return new URL(new URL(this.server), "/rest/" + endpoint);
        } catch (MalformedURLException e) {
            logger.log(Level.WARNING, "Error constructing Sauce URL", e);
            return null;
        }
    }
    public void downloadLog(String jobId, String location) {
        URL restEndpoint = this.buildURL("v1/" + username + "/jobs/" + jobId + "/assets/selenium-server.log");
        saveFile(jobId, location, restEndpoint);
    }
    static {
        if (System.getenv("SAUCE_REST_ENDPOINT") != null) {
            BASE_URL = System.getenv("SAUCE_REST_ENDPOINT");
        } else {
            BASE_URL = System.getProperty("saucerest-java.base_url", "https://saucelabs.com/");
        }
    }

    static {
        if (System.getenv("SAUCE_REST_EDS_ENDPOINT") != null) {
            BASE_EDS_URL = System.getenv("SAUCE_REST_EDS_ENDPOINT");
        } else {
            BASE_EDS_URL = System.getProperty("saucerest-java.base_eds_url", "https://eds.saucelabs.com/");
        }
    }
    public void SauceREST(String username, String accessKey) {
        this.username = username;
        this.accessKey = accessKey;
        this.server = BASE_URL;
        this.edsServer = BASE_EDS_URL;
    }
    private void saveFile(String jobId, String location, URL restEndpoint) {
        String jobAndAsset = restEndpoint.toString() + " for Job " + jobId;
        logger.log(Level.FINEST, "Attempting to save asset " + jobAndAsset + " to " + location);

        try {
            BufferedInputStream in = downloadFileData(jobId, restEndpoint);
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
            String saveName = jobId + format.format(new Date());
            if (restEndpoint.getPath().endsWith(".mp4")) {
                saveName = saveName + ".mp4";
            } else {
                saveName = saveName + ".log";
            }

            logger.log(Level.FINEST, "Saving " + jobAndAsset + " as " + saveName);
            FileOutputStream file = new FileOutputStream(new File(location, saveName));
            try (BufferedOutputStream out = new BufferedOutputStream(file)) {
                int i;
                while ((i = in.read()) != -1)
                {
                    out.write(i);
                }
                out.flush();
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, "Error downloading Sauce Results", e);
        }
    }
    public static String getExtraUserAgent() {
        return extraUserAgent;
    }
//    public static void setExtraUserAgent(String extraUserAgent) {
//        SauceREST. = extraUserAgent;
//    }
    protected String getUserAgent() {
        String userAgent = "SauceREST/" + BuildUtils.getCurrentVersion();
        if (!"".equals(getExtraUserAgent())) {
            userAgent = userAgent + " " + getExtraUserAgent();
        }
        logger.log(Level.FINEST, "userAgent is set to " + userAgent);
        return userAgent;
    }

    protected String encodeAuthentication() {
        String auth = username + ":" + accessKey;
        auth = "Basic " + Base64.encodeBase64String(auth.getBytes());
        return auth;
    }

    protected void addAuthenticationProperty(HttpURLConnection connection) {
        if (username != null && accessKey != null) {
            String auth = encodeAuthentication();
            logger.log(Level.FINE, "Encoded Authorization: " + auth);
            connection.setRequestProperty("Authorization", auth);
        }

    }

    private BufferedInputStream downloadFileData(String jobId, URL restEndpoint) throws IOException{
        logger.log(Level.FINE, "Downloading asset " + restEndpoint.toString() + " For Job " + jobId);
        logger.log(Level.FINEST, "Opening connection for Job " + jobId);
        HttpURLConnection connection = openConnection(restEndpoint);
        connection.setRequestProperty("User-Agent", this.getUserAgent());

        connection.setDoOutput(true);
        connection.setRequestMethod("GET");
        addAuthenticationProperty(connection);

        logger.log(Level.FINEST, "Obtaining input stream for request issued for Job " + jobId);
        InputStream stream = connection.getInputStream();
        BufferedInputStream in = new BufferedInputStream(stream);
        return in;
    }

    public HttpURLConnection openConnection(URL url) throws IOException {
        HttpURLConnection con;
        if ("true".equals(System.getenv("USE_PROXY"))) {
            logger.log(Level.SEVERE, "Using proxy: " + System.getenv("http.proxyHost")
                    + System.getenv("http.proxyPort"));

            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(System.getenv("http.proxyHost"),
                    Integer.parseInt(System.getenv("http.proxyPort"))));
            con = (HttpURLConnection) url.openConnection(proxy);
        } else {
            con = (HttpURLConnection) url.openConnection();
        }
        con.setReadTimeout((int) HTTP_READ_TIMEOUT_SECONDS);
        con.setConnectTimeout((int) HTTP_CONNECT_TIMEOUT_SECONDS);
        return con;
    }
}
