package parser;

import java.util.List;

public class GtfsSpec {
  List<Agency> agencies;
  List<Stop> stops;
  List<Route> routes;

  GtfsSpec(List<Agency> agencies, List<Stop> stops, List<Route> routes) {
    this.agencies = agencies;
    this.stops = stops;
    this.routes = routes;
  }
}
