package GameObjects;
import Geom.Point3D;
import Path.Path;
/**
 * packman is an object that extends GameObjects class
 * @author orh92
 *
 */
public class Packman extends GameObjects{
	private double orientation,SpeedWeight,latitude,lontitude,altitude,radius,id;		
	private Path path=new Path();
	public Packman(double latitude, double lontitude, double altitude,double orientation,double SpeedWeight) {
		super(latitude,lontitude,altitude,SpeedWeight);
		this.latitude=latitude;
		this.lontitude=lontitude;
		this.altitude=altitude;
		this.orientation=orientation;
		this.SpeedWeight=SpeedWeight;
	}
	public Packman(double id,double latitude,double lontitude,double altitude,double SpeedWeight,double radius) {
		super(id,latitude,lontitude,altitude,SpeedWeight);
		this.latitude=latitude;
		this.lontitude=lontitude;
		this.altitude=altitude;
		this.SpeedWeight=SpeedWeight;
		this.id=id;
		this.radius=radius;	
	}
	public void setOrientation(double orientation) {
		this.orientation = orientation;
	}
	public void setSpeedWeight(double SpeedWeight) {
		this.SpeedWeight = SpeedWeight;
	}
	public double getSpeedWeight() {
		return SpeedWeight;
	}
	public double getOrientation() {
		return orientation;
	}

	public double getLatitude() {
		return latitude;
	}
	public double getLontitude() {
		return lontitude;
	}
	public double getAltitude() {
		return altitude;
	}
	public void seAltitude(double altitude) {
		this.latitude=altitude;
	}
	public void setLatitude(double latitude) {
		this.latitude=latitude;
	}
	public void setLontitude(double lontitude) {
		this.lontitude=lontitude;
	}
	public void setRadius(double radius) {
		this.radius=radius;
	}
	public double getRadius() {
		return radius;
	}
	public void setId(double id) {
		this.id=id;
	}
	public double  getId() {
		return id;
	}
	public void addToPath( Point3D p) {
		path.addToPath(p);
	}
	public Path  getPath() {
		return path;
	}
}