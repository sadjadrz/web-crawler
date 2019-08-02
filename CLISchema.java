import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.PrivateKey;
import java.text.DecimalFormat;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

/**
 *
 */

/**
 * @author Mohsen
 *
 */
public class CLISchema {

    /**
     * The requested command for learning
     */
    private int MAX_CRAWL_DEPTH;
    private int NUMBER_OF_CRAWELRS;
    private int MaxPagesToFetch;
    private String CRAWL_STORAGE;
    private String URL;
    private String destinationPath;
    private String requestedCommand;

//    private String flowFileName;
//    private String portFileName;
//    private String graphType;
//    private String rankingAlgorithm;
//    private String outputType;
//    private String riskCompAlgorithm;
//    private int causalityTimeInterval;
//    private int timeWindowLength;
//    private int windowSlidingPercentage;
//    private int maxCacheSize;
//    private float cinParameter;
//    private boolean timingCausality;
//    private boolean correlationCausality;
//    private boolean countCausalitiesFlag;
//    private String countOutputFileName;
//    private boolean performanceAnalysis;
//    private boolean showNoIterations;
//    private boolean logFlag;
//    private String hitsArgument;
//    private boolean doTranspose;
//    private int priorKnowledgeLevel;
//    private float cutOffBeta;
    /**
     * Maximum number of repeat for the iterative algorithms
     */

    /**
     * constructor
     */
    public CLISchema() {
        MaxPagesToFetch = 100000;
        MAX_CRAWL_DEPTH = 10;
        URL = "https://androidkade.com";
        destinationPath = "apk_output.txt";
        CRAWL_STORAGE = "/data/crawl/root";
        NUMBER_OF_CRAWELRS = 2;
        requestedCommand = "";
    }

    private void runCrawler() throws Exception {
        /*
         * Instantiate crawl config
         */
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(CRAWL_STORAGE);
        config.setMaxDepthOfCrawling(MAX_CRAWL_DEPTH);
        config.setMaxPagesToFetch(MaxPagesToFetch);

        /*
         * Instantiate controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);


        /*
         * Add seed URLs
         */
        controller.addSeed(URL);
        TestCrawler.path = this.destinationPath;

        /*
         * Start the crawl.
         */
        controller.start(TestCrawler.class, NUMBER_OF_CRAWELRS);
    }
    /**
     * Receive a request as a command line and handle it
     *
     * @param args
     */
    public void handleRequest(String[] args) {
        int k;
        k = parsArguments(args);
        if (k != 0)
            return;
         try {
             runCrawler();
         } catch (Exception e) {

         }
        /* Here you run the main function of the program and pass the parameters */

    }

    /**
     * This method parse the input arguments as a configuration for the prgram
     *
     * @param args
     * @param
     */
    private int parsArguments(String[] args) {
        // parse arguments and handle the configuration
        requestedCommand = "";
        for (int j = 0; j < args.length; j++) {
            if (j == 0)
                requestedCommand += args[j];
            else
                requestedCommand += " " + args[j];
        }
        int i = 0;
        try {
            while (i < args.length) {
                if (args[i].equalsIgnoreCase("-h")
                        || args[i].equalsIgnoreCase("--help")) {
                    throw new Exception();
                } else if (args[i].equalsIgnoreCase("-u")
                        || args[i].equalsIgnoreCase("--url")) {
                    URL = args[i + 1];
                    i++;
                } else if (args[i].equalsIgnoreCase("-o")
                        || args[i].equalsIgnoreCase("--output")) {
                    destinationPath = args[i + 1];
                    i++;
                } else if (args[i].equalsIgnoreCase("-c")
                        || args[i].equalsIgnoreCase("--maxDepth")) {
                    MAX_CRAWL_DEPTH = Integer.parseInt(args[i + 1]);
                    i++;
                } else if (args[i].equalsIgnoreCase("-p")
                        || args[i].equalsIgnoreCase("--max_pages")) {
                    MaxPagesToFetch = Integer.parseInt(args[i + 1]);

                    i++;
                } else if (args[i].equalsIgnoreCase("-s")
                        || args[i].equalsIgnoreCase("--crawl_storage")) {
                    CRAWL_STORAGE = args[i + 1];
                    i++;
                } else if (args[i].equalsIgnoreCase("-n")
                        || args[i].equalsIgnoreCase("--crawlerNumber")) {
                    NUMBER_OF_CRAWELRS = Integer.parseInt(args[i + 1]);
                    i++;
                }
                i++;
            }

        } catch (Exception e) {
            writeHelpUsageInCLI();
            return 1;
        }
        return 0;
    }

    private void writeHelpUsageInCLI() {
        System.out.println("\nUsage: java -jar Apk_Crawler.jar"
                + "\n\t[{-h, --help}]"
                + "\n\t[{-u, --url} url to crawl]"
                + "\n\t[{-o, --output} destination path for example c://a.txt]"
                + "\n\t[{-d, --maxDepth} maximum depth of crawler"
                + "\n\t[{-p, --MaxPage} maximum page to crawl]"
                + "\n\t[{-s, --crawlStorage} path of crawl storage for example c:/temp"
                + "\n\t[{-n, --crawlerNumber} number of crawler for crawling"
        );
        System.out
                .println("\nA sample: -u https://androidkade.com -o c://sadjad.txt -p 1000 -s c://temp \n");

    }


}
