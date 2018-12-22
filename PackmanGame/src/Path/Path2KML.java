package Path;
import GameObjects.Packman;
import Geom.Point3D;
import Map.Map;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Iterator;
public class Path2KML {
	/**
	 * create kml file with the info of the paths of the packmans
	 * have option to see the packman moved by time stamps
	 * @param algo
	 * 
	 */
	public static void types2Kml(ShortestPathAlgo algo, String path) {
		try{
			Point3D point;
			StringBuilder sb = new StringBuilder();
			Iterator<Packman> iter = algo.getPackIter();		
			Iterator<Point3D> currentFruit;
			Iterator<Long> currentTime;
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			sb.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\"><IconStyle><Icon><href>https://upload.wikimedia.org/wikipedia/commons/archive/f/fa/20080619221414%21718smiley.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/blue-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>\n");
			/**
			 * while have packmans take the next one and his path
			 */
			while(iter.hasNext()) {
				Packman pack=iter.next();
				currentTime= pack.getPath().getTimeStamps().iterator();
				currentFruit =pack.getPath().getPathArray().iterator();
				/**
				 * take the current Path and add time stamps 
				 * and take the data into kml
				 */
				while(currentFruit.hasNext()) {
					Map map= new Map();
					point= currentFruit.next();
					point =map.Pixels2Polar(point);
					long thisMoment = currentTime.next();
					sb.append("<Placemark>\n");
					sb.append("<description>"+"the Packmans id: "+pack.getId()+"</description>\n");
					sb.append("<TimeStamp>\n");
					sb.append("<when>"+takeTimeStamp(thisMoment)+"</when>\n");
					sb.append("</TimeStamp>\n");
					sb.append("<styleUrl>#red</styleUrl>\r\n");
					sb.append("<Point>\n");
					sb.append("<coordinates>"+point.toString()+"</coordinates>\n");
					sb.append("</Point>\n");
					sb.append("</Placemark>\n");
				}
			}
			sb.append("</Folder>\n");
			sb.append("</Document>\r\n"+"</kml>"); 
			PrintWriter pr = new PrintWriter(new File(path+".kml"));
			pr.write(sb.toString());	
			pr.close();
			System.out.println("the Path/Paths has been saved!");
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	/**
	 * return string of time stamp
	 * @param currentTime
	 * @return
	 */
	private static String takeTimeStamp(Long currentTime) {
		String i = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentTime);
		String first = i.substring(0, 10);
		String sec = i.substring(11);
		return 	(first+"T"+sec+"Z");  
	}
}