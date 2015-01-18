package cat.udl.eps.softarch.hello.util;


import javax.xml.xquery.*;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;


public class XQueryHelper {
    private static final Logger log = Logger.getLogger(XQueryHelper.class.getName());
    static final String meteocatURL = "http://static-m.meteo.cat/content/opendata/ctermini_comarcal.xml";

    public static int getRegionWeather(int region) throws XQException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        log.info("Retrieving region weather from XML");
        String xquery = "declare variable $doc := doc(\"http://static-m.meteo.cat/content/opendata/ctermini_comarcal.xml\");\n"
                + "for $c in $doc//prediccio[@idcomarca=\"" + region + "\"]/variable[@dia=\"1\"]\n"
                + "return data($c/@simbolmati)";
        XQPreparedExpression expr;
        XQConnection conn;
        URLConnection urlconn;
        urlconn = new URL(meteocatURL).openConnection();
        XQDataSource xqds = (XQDataSource) Class.forName("net.sf.saxon.xqj.SaxonXQDataSource").newInstance();
        conn = xqds.getConnection();
        expr = conn.prepareExpression(xquery);
        urlconn.setReadTimeout(50000);
        XQResultSequence rs = expr.executeQuery();
        if(rs.next()) {
            String regionWeatherNumber = rs.getItemAsString(null);
            return Integer.parseInt(regionWeatherNumber.substring(0, regionWeatherNumber.length() - 4));
        }else{
            return 0;
        }

    }
}