/**
 * Created by marcin.majka on 21/2/2017.
 */
public class Test {
    public static void main(String[] args){

        float velocity = 800;
        float time = 180;
        float timeH=time/3600;
        float distance =  velocity *timeH;
        System.out.println(distance);
        //float distance = 6500f;
        float earthRadius = 6371;
        int angle = 30;
        double radians = Math.toRadians(angle);
        double latitude = 52.19f ;
        double longitude= 17f;
        latitude = Math.toRadians(latitude);
        longitude = Math.toRadians(longitude);

        double latitudeNew  = Math.asin( Math.sin(latitude)*Math.cos(distance/earthRadius) +
                Math.cos(latitude)*Math.sin(distance/earthRadius)*Math.cos(radians) );
        System.out.println(Math.toDegrees(latitudeNew));

        double longitudeNew = longitude + Math.atan2(Math.sin(radians)*Math.sin(distance/earthRadius)*Math.cos(latitude),
                Math.cos(distance/earthRadius)-Math.sin(latitude)*Math.sin(latitudeNew));

       System.out.println(finalBearing(latitudeNew,longitudeNew,latitude,longitude));
    }

    protected static double finalBearing(double srcLat, double srcLon, double destLat, double destLon){
        double longDiff= destLon-srcLon;
        double y= Math.sin(longDiff)*Math.cos(destLat);
        double x=Math.cos(srcLat)*Math.sin(destLat)-Math.sin(srcLat)*Math.cos(destLat)*Math.cos(longDiff);
        double bearing =(Math.toDegrees(Math.atan2(y, x))+360)%360;
        return (bearing+180)%360;
    }
}
