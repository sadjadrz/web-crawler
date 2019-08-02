    import edu.uci.ics.crawler4j.crawler.CrawlConfig;
    import edu.uci.ics.crawler4j.crawler.CrawlController;
    import edu.uci.ics.crawler4j.fetcher.PageFetcher;
    import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
    import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class MainApp
{

    public static void main(String[] args) throws Exception {
        CLISchema cli = new CLISchema();
        cli.handleRequest(args);

//
//        int MAX_CRAWL_DEPTH = 10;
//         int NUMBER_OF_CRAWELRS = 2;
//         String CRAWL_STORAGE = "/data/crawl/root";
//
//        /*
//         * Instantiate crawl config
//         */
//        CrawlConfig config = new CrawlConfig();
//        config.setCrawlStorageFolder(CRAWL_STORAGE);
//        config.setMaxDepthOfCrawling(MAX_CRAWL_DEPTH);
//        config.setMaxPagesToFetch(100000);
//
//        /*
//         * Instantiate controller for this crawl.
//         */
//        PageFetcher pageFetcher = new PageFetcher(config);
//        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
//        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
//        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
//
//
//        /*
//         * Add seed URLs
//         */
//        controller.addSeed("https://androidkade.com");
//
//        /*
//         * Start the crawl.
//         */
//        controller.start(TestCrawler.class, NUMBER_OF_CRAWELRS);
    }
}