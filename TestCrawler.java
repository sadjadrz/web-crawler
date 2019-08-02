import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.regex.Pattern;

public class TestCrawler extends WebCrawler{
    public static String path="apk_output.txt";


    private final static Pattern FILTERS = Pattern.compile(".*(\\.(apk|html))$");

    /**
     * Specify whether the given url should be crawled or not based on
     * the crawling logic. Here URLs with extensions css, js etc will not be visited
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url)  {
        String href = url.getURL();
        boolean result = FILTERS.matcher(href).matches();
        if(result && href.contains(".apk")){
            System.out.println("URL Should Visit::"+href);
            try{
                FileWriter fw=new FileWriter(TestCrawler.path,true);
                BufferedWriter out = new BufferedWriter(fw);
                out.write(href);
                out.newLine();
                out.close();
            }
            catch(Exception e){
                System.out.println(e);
            }


        }
//        else
//            System.out.println("URL Should not Visit");

        return result;
    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by the program.
     */
    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        System.out.println("URL: " + url);
        /*       if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String text = htmlParseData.getText(); //extract text from page
            String html = htmlParseData.getHtml(); //extract html from page
            Set<WebURL> links = htmlParseData.getOutgoingUrls();
            if(html.contains(".apk")){
                System.out.println("sssssss"+html);
            }

            System.out.println("---------------------------------------------------------");
            System.out.println("Page URL: " + url);
            System.out.println("Text length: " + text.length());
            System.out.println("Html length: " + html.length());
            System.out.println("Number of outgoing links: " + links.size());
            System.out.println("---------------------------------------------------------");
            */

            //if required write content to file
        }
    }
