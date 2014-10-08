package parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import au.com.bytecode.opencsv.CSVReader;

public class GtfsReader {
  public static GtfsSpec readGtfsFile(InputStream gtfsFile)
      throws NumberFormatException, MalformedURLException, IOException {
    ZipInputStream zip = new ZipInputStream(gtfsFile);
    ArrayList<Agency> agencies = new ArrayList<Agency>();
    ArrayList<Route> routes = new ArrayList<Route>();
    ArrayList<Stop> stops = new ArrayList<Stop>();
    for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
      if (entry.isDirectory()) {
        continue;
      }
      String filename = entry.getName();
      if (filename.equals("agency.txt")) {
        List<Map<String, String>> lines = readCsv(zip);
        System.out.printf("agency lines: %d\n", lines.size());
        for (Map<String, String> line : lines) {
          agencies.add(new Agency(line.get("agency_id"), line.get("agency_name"),
                       new URL(line.get("agency_url")),
                       TimeZone.getTimeZone(line.get("agency_timezone"))));
        }
      } else if (filename.equals("stops.txt")) {
        List<Map<String, String>> lines = readCsv(zip);
        for (Map<String, String> line : lines) {
          stops.add(new Stop(line.get("stop_id"), line.get("stop_name"),
                    Double.parseDouble(line.get("stop_lat")),
                    Double.parseDouble(line.get("stop_lon"))));
        }
      } else if (filename.equals("routes.txt")) {
        List<Map<String, String>> lines = readCsv(zip);
        for (Map<String, String> line : lines) {
          routes.add(new Route(line.get("route_id"), line.get("route_short_name"),
                     line.get("route_long_name"),
                     Integer.parseInt(line.get("route_type"))));
        }
      }
    }
    return new GtfsSpec(agencies, stops, routes);
  }

  private static List<Map<String, String>> readCsv(InputStream csv) throws IOException {
    CSVReader reader = new CSVReader(new InputStreamReader(csv));
    ArrayList<Map<String, String>> lines = new ArrayList<Map<String,String>>();
    String[] columnNames = reader.readNext();
    if (columnNames == null) {
      return lines;
    }
    String[] line;
    while((line = reader.readNext()) != null) {
      HashMap<String, String> map = new HashMap<String, String>();
      for (int ci = 0; ci < line.length; ++ci) {
        map.put(columnNames[ci], line[ci]);
      }
      lines.add(map);
    }
    return lines;
  }

  public static void main(String[] args)
      throws NumberFormatException, MalformedURLException, IOException {
    System.out.println("Reading GTFS file at " + args[0]);
    GtfsSpec gtfs = GtfsReader.readGtfsFile(new FileInputStream(args[0]));
    System.out.printf("Agencies: %d\n", gtfs.agencies.size());
    for (Agency a : gtfs.agencies) {
      System.out.printf("  Agency(%s, %s, %s, %s)\n", a.id, a.name, a.url, a.timezone);
    }
    System.out.printf("Stops: %d\n", gtfs.stops.size());
    System.out.printf("Routes: %d\n", gtfs.routes.size());
  }
}
